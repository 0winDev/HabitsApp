package com.example.authentication_domain.usecase

data class LoginUseCases(
    val loginUseCase: LoginWithEmailUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase
)
