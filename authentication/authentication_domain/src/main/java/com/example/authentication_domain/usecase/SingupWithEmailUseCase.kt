package com.example.authentication_domain.usecase

import com.example.authentication_domain.repository.AuthentificationRepository

class SingupWithEmailUseCase(private val repository: AuthentificationRepository) {
    suspend operator fun invoke(email: String, password: String): Result<Unit> {
        return repository.signup(email, password)
    }
}