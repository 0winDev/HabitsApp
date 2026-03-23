package com.example.authentication_data.repository

import com.example.authentication_domain.repository.AuthentificationRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await


//We implement the AuthentificationRepository interface
class AuthentificationRepositoryImpl : AuthentificationRepository {
    override suspend fun login(
        email: String,
        password: String
    ): Result<Unit> {
        return try {
            //in case of success we return Result.success
            Firebase.auth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun signup(email: String, password: String): Result<Unit> {
        return try {
            Firebase.auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override fun getUserId(): String? {
        //if this return null we are not loged
        return Firebase.auth.currentUser?.uid
    }


    override suspend fun logout() {
        Firebase.auth.signOut()
    }
}