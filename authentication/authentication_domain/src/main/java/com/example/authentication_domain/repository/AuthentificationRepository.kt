package com.example.authentication_domain.repository

interface AuthentificationRepository {
                    // we return a Result type to handle success and failure
    suspend fun login(email: String, password:String): Result<Unit>
    suspend fun signup(email: String, password: String): Result<Unit>

    fun getUserId(): String?

    suspend fun logout()
}