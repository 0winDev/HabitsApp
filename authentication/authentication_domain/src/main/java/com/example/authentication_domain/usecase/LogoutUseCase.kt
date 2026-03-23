package com.example.authentication_domain.usecase

import com.example.authentication_domain.repository.AuthentificationRepository


class LogoutUseCase(private val repository: AuthentificationRepository) {
    suspend operator fun invoke() {
        return repository.logout()
    }

}