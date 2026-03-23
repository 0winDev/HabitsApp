package com.example.authentication_domain.usecase

data class SingupUseCases(
    val signupWithEmailUseCase: SingupWithEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase,
    val validateEmailUseCase: ValidateEmailUseCase
)


