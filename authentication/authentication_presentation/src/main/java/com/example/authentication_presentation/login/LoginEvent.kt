package com.example.authentication_presentation.login

//this sealed class will represent all the events
// that can happen in the login screen
sealed interface LoginEvent{
    data class EmailChange(val email: String): LoginEvent
    data class PasswordChange(val password: String): LoginEvent
    object Login: LoginEvent
    object SingUp: LoginEvent

}