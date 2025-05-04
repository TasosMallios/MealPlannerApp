package com.example.mealplannerfinalproject.ui.home

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.mealplannerfinalproject.ui.home.composables.ErrorScreen
import com.example.mealplannerfinalproject.ui.home.composables.JwtExpiredScreen
import com.example.mealplannerfinalproject.ui.home.composables.LoadingScreen
import com.example.mealplannerfinalproject.ui.home.composables.WeeklyMealsScreen
import com.example.mealplannerfinalproject.ui.model.MealDayUI
import com.example.mealplannerfinalproject.ui.model.MealUI

// Meal planner home screen
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToLoginScreen: () -> Unit
) {

    val state = viewModel.state.collectAsState()

    val context = LocalContext.current

    HomeContent(state.value) { event -> viewModel.onEvent(event) }

    LaunchedEffect(Unit) {
        viewModel.registerUIEvent.collect { event ->
            when (event) {
                is HomeUIEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }

                is HomeUIEvent.LoginClicked -> {
                    navigateToLoginScreen()
                }
            }
        }
    }
}

@Composable
fun HomeContent(
    state: HomeState = HomeState(),
    onEvent: (HomeEvent) -> Unit = {},
) {
    when {
        // Show loading screen if data has not been loaded
        // else show actual weekly meal data
        state.isLoading -> {
            LoadingScreen()
        }

        // Show expired JWT token screen
        state.jwtExpired -> {
            JwtExpiredScreen(
                state = state,
                onEvent = onEvent
            )
        }

        // Show error screen
        state.errorMessage != null -> {
            ErrorScreen(
                state = state,
                onEvent = onEvent
            )
        }

        // Show weekly meals
        state.isSuccess -> {
            WeeklyMealsScreen(
                state = state,
                onEvent = onEvent
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeContentPreview() {
    HomeContent(
        HomeState(
            week = "2025-18",
            days = mutableListOf(
                MealDayUI(
                    "2025-05-01",
                    mutableListOf(
                        MealUI(
                            id = 1,
                            name = "Avocado Toast",
                            description = "Whole-grain toast with avocado and eggs",
                            calories = 350,
                            liked = true
                        ),
                        MealUI(
                            id = 2,
                            name = "Grilled Chicken",
                            description = "Grilled chicken with fresh veggies",
                            calories = 500
                        )
                    )
                ),
                MealDayUI(
                    "2025-05-02", mutableListOf(
                        MealUI(
                            id = 2,
                            name = "Grilled Chicken",
                            description = "Grilled chicken with fresh veggies",
                            calories = 500
                        ),
                        MealUI(
                            id = 1,
                            name = "Avocado Toast",
                            description = "Whole-grain toast with avocado and eggs",
                            calories = 350
                        )
                    )
                )
            ),
            hasPrevious = true,
            hasNext = true,
            isSuccess = true,
        )
    )
}