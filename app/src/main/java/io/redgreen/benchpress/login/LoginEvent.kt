package io.redgreen.benchpress.login

sealed class LoginEvent

data class ChangePasswordEvent(val password: String) : LoginEvent()
data class ChangeEmailEvent(val email: String) : LoginEvent()
object LoginButtonClickEvent : LoginEvent()