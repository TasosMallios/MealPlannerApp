package com.example.mealplannerfinalproject.utils.navigation

// Navigation paths for the AppNav composable
sealed class NavigationPaths(
    val route: String
) {
    data object HomeScreen : NavigationPaths("HomeScreen")
    data object LoginScreen : NavigationPaths("LoginScreen")
    data object RegisterScreen : NavigationPaths("RegisterScreen")
}