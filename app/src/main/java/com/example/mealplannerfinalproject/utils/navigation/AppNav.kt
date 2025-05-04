package com.example.mealplannerfinalproject.utils.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mealplannerfinalproject.data.local.SessionManager
import com.example.mealplannerfinalproject.ui.home.HomeScreen
import com.example.mealplannerfinalproject.ui.login.LoginScreen
import com.example.mealplannerfinalproject.ui.register.RegisterScreen

@Composable
fun AppNav(sessionManager: SessionManager) {
    val navController = rememberNavController()

    // Set LoginScreen composable as the starting destination
    NavHost(
        navController = navController,
        startDestination = if (sessionManager.getToken().isNullOrEmpty()) {
            NavigationPaths.LoginScreen.route
        } else NavigationPaths.HomeScreen.route,
    ) {

        // Add LoginScreen composable route
        composable(
            route = NavigationPaths.LoginScreen.route
        ) {
            LoginScreen(
                // Navigate to HomeScreen
                // and pop LoginScreen from back stack
                navigateToHomeScreen = {
                    navController.navigate(NavigationPaths.HomeScreen.route) {
                        popUpTo(NavigationPaths.LoginScreen.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                navigateToRegisterScreen = {
                    navController.navigate(NavigationPaths.RegisterScreen.route) {
                        launchSingleTop = true
                    }
                },
            )
        }

        // Add RegisterScreen composable route
        composable(
            route = NavigationPaths.RegisterScreen.route,
            enterTransition = {
                slideInVertically(
                    initialOffsetY = { it }, // Slide from bottom
                    animationSpec = tween(200)
                )
            },
            exitTransition = {
                slideOutVertically(
                    targetOffsetY = { it }, // Slide to bottom
                    animationSpec = tween(200)
                )
            },
            popEnterTransition = {
                slideInVertically(
                    initialOffsetY = { it },
                    animationSpec = tween(200)
                )
            },
            popExitTransition = {
                slideOutVertically(
                    targetOffsetY = { it },
                    animationSpec = tween(200)
                )
            }
        ) {
            RegisterScreen(
                // Navigate to LoginScreen
                // and pop RegisterScreen from back stack
                navigateToLoginScreen = {
                    navController.navigate(NavigationPaths.LoginScreen.route) {
                        popUpTo(NavigationPaths.RegisterScreen.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        // Add HomeScreen composable route
        // and pop LoginScreen from back stack
        composable(
            route = NavigationPaths.HomeScreen.route
        ) {
            HomeScreen(
                navigateToLoginScreen = {
                    navController.navigate(NavigationPaths.LoginScreen.route) {
                        popUpTo(NavigationPaths.HomeScreen.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }

            )
        }
    }
}
