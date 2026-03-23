package com.example.habitsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.authentication_presentation.login.LoginScreen
import com.example.authentication_presentation.singup.SignupScreen
import com.example.home_presentation.detail.DetailScreen
import com.example.home_presentation.home.HomeScreen
import com.example.onboarding_presentation.OnboardingScreen
import com.example.settings_presentation.SettingsScreen


@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationRoute,
    logout: () -> Unit
) {

    NavHost(navController = navHostController, startDestination = startDestination.route) {
        composable(NavigationRoute.Onboarding.route) {

            OnboardingScreen(
                onFinish = {
                    // this will clear the back stack and navigate to login screen
                    navHostController.popBackStack()
                    navHostController.navigate(NavigationRoute.Login.route)
                }
            )

        }
        composable(NavigationRoute.Login.route) {
            LoginScreen(onLogin = {
                // this will clear the back stack and navigate to home screen
                navHostController.popBackStack()
                navHostController.navigate(NavigationRoute.Home.route)
            }, onSingUp = {
                navHostController.navigate(NavigationRoute.Signup.route)
            })
        }

        composable(NavigationRoute.Signup.route) {
            SignupScreen(onSingIn = {
                navHostController.navigate(NavigationRoute.Home.route){
                    popUpTo(navHostController.graph.id) {
                        inclusive=true
                    }
                }
            },
                onLogin = {

                })
        }
        composable(NavigationRoute.Home.route) {
            HomeScreen(onNewHabit = {

                navHostController.navigate(NavigationRoute.Detail.route)

            }, onSettings = {

                navHostController.navigate(NavigationRoute.Settings.route)

            }, onEditHabit = {
                //habitId parameter to navigate to detail
                navHostController.navigate(NavigationRoute.Detail.route + "?habitId=$it")
            })
        }

        composable(
            //We se for parameter habitId
            NavigationRoute.Detail.route + "?habitId={habitId}",
            arguments = listOf(
                navArgument("habitId") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            DetailScreen(onBack = {
                navHostController.popBackStack()
            }, onSave = {
                navHostController.popBackStack()
            })
        }

        composable(NavigationRoute.Settings.route) {
            SettingsScreen(
                onBack = {
                    navHostController.popBackStack()
                },
                onLogout = {
                    logout()
                    navHostController.navigate(NavigationRoute.Login.route) {
                        popUpTo(navHostController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }
    }

}