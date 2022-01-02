package io.redgreen.benchpress.hellostranger

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer
import io.redgreen.benchpress.architecture.BaseActivity
import kotlinx.android.synthetic.main.hello_stranger_activity.*
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.redgreen.benchpress.R
import io.redgreen.benchpress.architecture.textWatcher.TextWatcher


class HelloStrangerActivity :
    BaseActivity<HelloStrangerModel, HelloStrangerEvent, HelloStrangerEffect>(),
    HelloStrangerInteractor {
    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, HelloStrangerActivity::class.java))
        }
    }

    override fun layoutResId(): Int {
        return R.layout.hello_stranger_activity
    }

    override fun setup() {
        nameEditText.addTextChangedListener(object : TextWatcher() {
            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (s != null) {
                    if (s.isEmpty()) {
                        eventSource.notifyEvent(EmptyNameEvent)
                    } else {
                        eventSource.notifyEvent(ChangeNameEvent(s.toString()))
                    }
                }
            }
        })
    }

    override fun initialModel(): HelloStrangerModel {
        return HelloStrangerModel.EMPTY
    }

    override fun updateFunction(
        model: HelloStrangerModel,
        event: HelloStrangerEvent
    ): Next<HelloStrangerModel, HelloStrangerEffect> {
        return HelloStrangerLogic.update(model, event)
    }

    @SuppressLint("SetTextI18n")
    override fun render(model: HelloStrangerModel) {
        if (model.name.isEmpty()) {
            greetingTextView.text = getString(R.string.greet)
        } else if (model.name.isEmpty().not()) {
            greetingTextView.text = "Hello, ${model.name}"
        }
    }

    override fun effectHandler(): ObservableTransformer<HelloStrangerEffect, HelloStrangerEvent> {
        return HelloStrangerEffectHandler().createEffectHandler(
            interact = this@HelloStrangerActivity,
            scheduler = AndroidSchedulers.mainThread()
        )
    }

    override fun showError() {
        Toast.makeText(this, "Hello Stranger", Toast.LENGTH_SHORT).show()
    }

}

