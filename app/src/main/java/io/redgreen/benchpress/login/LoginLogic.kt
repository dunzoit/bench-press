package io.redgreen.benchpress.login

import android.util.Patterns
import com.spotify.mobius.Next
import com.spotify.mobius.Update

object LoginLogic : Update<LoginModel, LoginEvent, LoginEffect> {
    override fun update(model: LoginModel, event: LoginEvent): Next<LoginModel, LoginEffect> {
        return when (event) {
            is ChangePasswordEvent -> {
                if (isValidEmail(model.email) && event.password.length > 8) {
                    Next.next(
                        model.copy(
                            password = event.password,
                            canLogin = true,
                            validPassword = true,
                            validEmail = true
                        )
                    )
                } else if (event.password.length > 8) {
                    Next.next(model.validPassword(true, event.password))
                } else {
                    Next.next(model.validPassword(false, event.password))
                }
            }
            is ChangeEmailEvent -> {
                if (isValidEmail(event.email) && model.password.length > 8) {
                    Next.next(
                        model.copy(
                            email = event.email,
                            canLogin = true,
                            validPassword = true,
                            validEmail = true
                        )
                    )
                } else if (isValidEmail(event.email)) {
                    Next.next(model.validEmail(true, event.email))
                } else {
                    Next.next(model.validEmail(false, event.email))
                }
            }
            is LoginButtonClickEvent -> {
                Next.next(model, setOf<LoginEffect>(LoginButtonClickEffect))
            }
        }
    }

    private fun isValidEmail(email: String): Boolean {
        val pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

}