package com.example.onboarding_presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.onboarding_presentation.components.OnboardingPage

@Composable
fun OnboardingScreen(
    viewModel: OnboardingViewModel= hiltViewModel(),
    onFinish: () -> Unit = {},
    ) {
    LaunchedEffect(key1 = viewModel.hasSeenOnboarding) {
        if (viewModel.hasSeenOnboarding){
            onFinish()
        }
    }
    val pages = listOf(
        OnboardingPageInformation(
            title = "Welcome to HabitsApp",
            subtitle = "Track and improve your habits effortlessly.",
            image = R.drawable.onboarding1
        ),
        OnboardingPageInformation(
            title = "Create new Habits easily",
            subtitle = "Track and improve your habits effortlessly.",
            image = R.drawable.onboarding2
        ),
        OnboardingPageInformation(
            title = "Keep track of your progress",
            subtitle = "Track and improve your habits effortlessly.",
            image = R.drawable.onboarding3
        ),
        OnboardingPageInformation(
            title = "Join as supportive community",
            subtitle = "Track and improve your habits effortlessly.",
            image = R.drawable.onboarding4
        )
    )

    OnboardingPage(pages = pages, onFinish = {
        viewModel.completeOnboarding()
        onFinish()
    })

}