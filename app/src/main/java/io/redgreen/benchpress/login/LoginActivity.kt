package io.redgreen.benchpress.login

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.BaseActivity
import kotlinx.android.synthetic.main.hello_stranger_activity.*
import kotlinx.android.synthetic.main.login_activity.*
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class LoginActivity : BaseActivity<LoginModel, LoginEvent, LoginEffect>(), LoginInteractor {

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java))
        }

    }

    override fun layoutResId(): Int {
        return R.layout.login_activity
    }

    override fun setup() {
        setupPasswordTextChange()
        setupEmailTextChange()
        setupLoginClick()
    }

    private fun setupLoginClick() {
        loginButton.setOnClickListener {
            eventSource.notifyEvent(LoginButtonClickEvent)
        }
    }

    private fun setupPasswordTextChange() {
        passwordEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.isEmpty().not()) {
                    eventSource.notifyEvent(ChangePasswordEvent(s.toString()))
                }
            }
        })
    }

    private fun setupEmailTextChange() {
        emailEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {

            }

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.isEmpty().not()) {
                    eventSource.notifyEvent(ChangeEmailEvent(s.toString()))
                }
            }
        })
    }

    override fun initialModel(): LoginModel {
        return LoginModel.START
    }

    override fun updateFunction(
        model: LoginModel,
        event: LoginEvent
    ): Next<LoginModel, LoginEffect> {
        return LoginLogic.update(model, event)
    }

    override fun render(model: LoginModel) {
        if (model.canLogin) {
            enableLoginButton()
        } else {
            disableLoginButton();
        }

        if (model.validEmail.not()) {
            emailTextInputLayout.error = getString(R.string.valid_email)
        } else {
            emailTextInputLayout.error = ""
        }

        if (model.validPassword.not()) {
            passwordTextInputLayout.error = getString(R.string.valid_password)
        } else {
            passwordTextInputLayout.error = ""
        }
    }

    private fun enableLoginButton() {
        loginButton.isClickable = true
        loginButton.isEnabled = true
        loginButton.setBackgroundColor(Color.GREEN)
    }

    private fun disableLoginButton() {
        loginButton.isClickable = false
        loginButton.isEnabled = false
        loginButton.setBackgroundColor(Color.parseColor("lightgrey"))
    }

    override fun effectHandler(): ObservableTransformer<LoginEffect, LoginEvent> {
        return LoginEffectHandler().createEffectHandler(
            this@LoginActivity,
            AndroidSchedulers.mainThread()
        )
    }

    override fun openLoader() {
        progressBar3.visibility = View.VISIBLE
        Executors.newSingleThreadScheduledExecutor().schedule({
            progressBar3.visibility = View.GONE
        }, 5, TimeUnit.SECONDS)
    }

}
