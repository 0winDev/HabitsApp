package com.example.onboarding_data.repository

import android.content.SharedPreferences
import com.example.onboarding_domain.repository.OnboardingRepository

// This call implements the OnboardingRepository interface using SharedPreferences to store onboarding status
class OnboardingRepositoryImpl(
private val sharedPreferences : SharedPreferences
) : OnboardingRepository {

    companion object {
        private const val HAS_SEEN_ONBOARDING = "has_seen_onboarding"

    }

    override  fun hasSeenOnboarding(): Boolean {
        // Retrieve the onboarding status from SharedPreferences
        return sharedPreferences.getBoolean(HAS_SEEN_ONBOARDING, false)
    }

    override  fun completeOnboarding() {
        // Mark onboarding as completed in SharedPreferences
        sharedPreferences.edit().putBoolean(HAS_SEEN_ONBOARDING, true).apply()
    }
}