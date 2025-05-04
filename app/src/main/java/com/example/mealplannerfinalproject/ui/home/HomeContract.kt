package com.example.mealplannerfinalproject.ui.home

import com.example.mealplannerfinalproject.ui.model.MealDayUI

// States of UI
data class HomeState(
    val week: String? = null,
    val days: List<MealDayUI> = emptyList(),
    val hasNext: Boolean = false,
    val hasPrevious: Boolean = false,
    val jwtExpired: Boolean = false,
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isSuccess: Boolean = false
) {
    companion object {
        fun toggleMealSelection(state: HomeState, mealId: Int?, date: String): HomeState {
            return state.copy(
                days = state.days.map { mealDay ->
                    if (mealDay.date == date) {
                        mealDay.copy(
                            meals = mealDay.meals?.map { meal ->
                                if (meal.id == mealId) {
                                    meal.copy(selected = !meal.selected!!)
                                } else {
                                    meal
                                }
                            }
                        )
                    } else {
                        mealDay
                    }
                }
            )
        }

        fun toggleMealLike(state: HomeState, mealId: Int?): HomeState {
            return state.copy(
                days = state.days.map { mealDay ->
                    mealDay.copy(
                        meals = mealDay.meals?.map { meal ->
                            if (meal.id == mealId) {
                                meal.copy(liked = !meal.liked!!)
                            } else {
                                meal
                            }
                        }
                    )
                }
            )
        }

    }
}

// UI event
sealed class HomeUIEvent {
    data object LoginClicked : HomeUIEvent()
    data class ShowToast(val message: String) : HomeUIEvent()
}

// User actions
sealed class HomeEvent{
    data class ToggleMealSelection(val week: String, val date: String, val mealId: Int?, val selected: Boolean) :
        HomeEvent()
    data class ToggleMealLike(val mealId: Int?, val liked: Boolean) : HomeEvent()
    data object LoadNextWeek : HomeEvent()
    data object LoadPreviousWeek : HomeEvent()
    data object LoginClicked: HomeEvent()
    data object TryAgainClicked: HomeEvent()
    data object Logout : HomeEvent()
}