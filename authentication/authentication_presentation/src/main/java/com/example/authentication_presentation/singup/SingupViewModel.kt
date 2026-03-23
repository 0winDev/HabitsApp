package com.example.authentication_presentation.singup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authentication_domain.usecase.SingupUseCases
import com.example.authentication_presentation.util.PasswordErrorParser
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch


@HiltViewModel
class SignupViewModel @Inject constructor(
    private val singupUseCases: SingupUseCases
) : ViewModel() {

    var state by mutableStateOf(SingupState())
        private set
    fun onEvent(event: SignupEvent) {
        when (event) {
            is SignupEvent.EmailChange -> {
                state = state.copy(
                    email = event.email
                )
            }
            is SignupEvent.PasswordChange -> {
                state = state.copy(
                    password = event.password
                )
            }
            SignupEvent.LogIn -> {
                state = state.copy(
                    logIn = true
                )
            }
            SignupEvent.SignUp -> {
                signUp()

            }
        }
    }
    private fun signUp() {

        state= state.copy(
            emailError = null,
            passwordError = null
        )
        if (!singupUseCases.validateEmailUseCase(state.email)) {
            state = state.copy(
                emailError = "Email is not valid"
            )
        }
        val passwordResult = singupUseCases.validatePasswordUseCase(state.password)
        state = state.copy(
            passwordError = PasswordErrorParser.parseError(passwordResult)
        )

        if (state.emailError == null && state.passwordError == null) {
            state = state.copy(
                isLoading = true
            )
            viewModelScope.launch {
                singupUseCases.signupWithEmailUseCase(state.email, state.password).onSuccess {
                    state= state.copy(
                        isSignedIn = true
                    )
                }.onFailure {
                    state= state.copy(
                        emailError = it.message
                    )
                }
                state = state.copy(
                    isLoading = false
                )
            }


        }

    }
}