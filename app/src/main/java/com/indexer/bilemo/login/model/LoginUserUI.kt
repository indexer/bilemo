package com.indexer.bilemo.login.model

import java.util.regex.Pattern

class LoginUserUI(
    private val userName: String?,
    private val userPassword: String?,
    private val userCountry: String?,
    private val isRememberLoginState: Boolean?
) {
    val userSelectedCountry: String
        get() = userCountry.toString()

    val getUserPassword: String
        get() = userPassword.toString()

    val getUserName: String
        get() = userName.toString()

    val isCorrectUserName: Boolean
        get() = Pattern.matches("[a-zA-Z]+", userName.toString())

    val hasMinLengthForUserName: Boolean
        get() = userName?.length ?: 0 > 7

    val isPasswordLengthGreaterThanSeven: Boolean
        get() = userPassword?.length ?: 0 > 7

    val isNotContainUpperCase: Boolean
        get() = userPassword?.none { Character.isUpperCase(it) } == true

    val isNotContainLowerCase: Boolean
        get() = userPassword?.none { Character.isLowerCase(it) } ?: false

    val isNotContainDigitCase: Boolean
        get() = userPassword?.none { Character.isDigit(it) } == true

    val isNotContainSpecialCharCase: Boolean
        get() = userPassword?.none { !Character.isLetterOrDigit(it) } == true

    val isRememberLogin: Boolean
        get() = isRememberLoginState ?: false
}