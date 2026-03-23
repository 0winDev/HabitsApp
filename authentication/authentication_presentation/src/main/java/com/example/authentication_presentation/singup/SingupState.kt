package com.example.authentication_presentation.singup

data class SingupState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val isSignedIn: Boolean = false,
    val isLoading: Boolean = false,
    val logIn: Boolean = false
)
