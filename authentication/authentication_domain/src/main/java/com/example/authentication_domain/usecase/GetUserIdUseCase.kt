package com.example.authentication_domain.usecase

import com.example.authentication_domain.repository.AuthentificationRepository

class GetUserIdUseCase(private val repository: AuthentificationRepository) {
    operator fun invoke(): String?{
        return repository.getUserId()
    }
}