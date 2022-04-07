package com.example.logincompose.presentation.login

import androidx.annotation.StringRes

//vamos a estar defidindo cada uno de los diferentes estados de nuestra interfaz de usuario
data class LoginState(
    val email: String = "",
    val password: String = "",
    val suscesLogin: Boolean = false,
    val displayProgressBar: Boolean = false,
    @StringRes val errorMessage: Int? = null

)
