package com.example.onboarding_presentation

import androidx.annotation.DrawableRes

data class OnboardingPageInformation(
    val title: String,
    val subtitle: String,
    @DrawableRes val image: Int
)
