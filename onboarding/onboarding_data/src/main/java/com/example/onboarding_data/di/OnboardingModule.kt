package com.example.onboarding_data.di

import android.content.Context
import android.content.SharedPreferences
import com.example.onboarding_data.repository.OnboardingRepositoryImpl
import com.example.onboarding_domain.repository.OnboardingRepository
import com.example.onboarding_domain.usecase.CompleteOnboardingUseCase
import com.example.onboarding_domain.usecase.HasSeenOnboardingUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object OnboardingModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("Habits_onboarding_preferences", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideOnboardingDependencies(sharedPreferences: SharedPreferences): OnboardingRepository {
        return OnboardingRepositoryImpl(sharedPreferences)
    }
    @Provides
    @Singleton
    fun provideHasSeenOnboardingUseCase(repository: OnboardingRepository): HasSeenOnboardingUseCase {
        return HasSeenOnboardingUseCase(repository)
    }
    @Provides
    @Singleton
    fun provideCompleteOnboardingUseCase(repository: OnboardingRepository): CompleteOnboardingUseCase {
        return CompleteOnboardingUseCase(repository)
    }

}