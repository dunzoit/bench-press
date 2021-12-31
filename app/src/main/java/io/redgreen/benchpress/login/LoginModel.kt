package io.redgreen.benchpress.login

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginModel(
    val email: String,
    val password: String,
    val canLogin: Boolean,
    val validEmail: Boolean,
    val validPassword: Boolean
) : Parcelable {

    companion object {
        val START = LoginModel("", "", canLogin = false, validEmail = false, validPassword = false)
    }

    fun validEmail(flag: Boolean, email: String): LoginModel =
        copy(canLogin = false, validEmail = flag, email = email)

    fun validPassword(flag: Boolean, password: String): LoginModel =
        copy(canLogin = false, validPassword = flag, password = password)

}