package com.example.authentication_data.di

import com.example.authentication_data.matcher.EmailMatcherImpl
import com.example.authentication_data.repository.AuthentificationRepositoryImpl
import com.example.authentication_domain.matcher.EmailMatcher
import com.example.authentication_domain.repository.AuthentificationRepository
import com.example.authentication_domain.usecase.GetUserIdUseCase
import com.example.authentication_domain.usecase.LoginUseCases
import com.example.authentication_domain.usecase.LoginWithEmailUseCase
import com.example.authentication_domain.usecase.LogoutUseCase
import com.example.authentication_domain.usecase.SingupUseCases
import com.example.authentication_domain.usecase.SingupWithEmailUseCase
import com.example.authentication_domain.usecase.ValidateEmailUseCase
import com.example.authentication_domain.usecase.ValidatePasswordUseCase

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
//this is object bcuz we will use it to provide dependencies
object AuthentificationModule {
    @Provides
    @Singleton
    fun provideAuthentificationRepository(): AuthentificationRepository {
        return AuthentificationRepositoryImpl()
    }


    @Provides
    @Singleton
    fun provideEmailMatcher(): EmailMatcher {
        return EmailMatcherImpl()
    }

    @Provides
    @Singleton
    fun provideSignupUseCases(
        repository: AuthentificationRepository,
        emailMatcher: EmailMatcher


    ): SingupUseCases {
        return SingupUseCases(
            signupWithEmailUseCase = SingupWithEmailUseCase(repository),
            validateEmailUseCase = ValidateEmailUseCase(emailMatcher),
            validatePasswordUseCase = ValidatePasswordUseCase()

        )
    }
    @Provides
    @Singleton
    fun provideLoginUseCases(
        repository: AuthentificationRepository,
        emailMatcher: EmailMatcher
    ): LoginUseCases {
        return LoginUseCases(
            loginUseCase = LoginWithEmailUseCase(repository),
            validatePasswordUseCase = ValidatePasswordUseCase(),
            validateEmailUseCase = ValidateEmailUseCase(emailMatcher)
        )
    }

    @Provides
    @Singleton
    fun provideLogoutUseCase(repository: AuthentificationRepository): LogoutUseCase {
        return LogoutUseCase(repository)
    }


    @Provides
    @Singleton
    fun provideGetUserIdUseCase(
        repository: AuthentificationRepository
    ): GetUserIdUseCase {
    return GetUserIdUseCase(repository)
    }
}