package com.example.mealplannerfinalproject.ui.home

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealplannerfinalproject.data.NetworkErrorHandler
import com.example.mealplannerfinalproject.data.NetworkResult
import com.example.mealplannerfinalproject.data.local.SessionManager
import com.example.mealplannerfinalproject.domain.use_case.HomeUseCases
import com.example.mealplannerfinalproject.ui.mapper.toUI
import com.example.mealplannerfinalproject.utils.getNextWeek
import com.example.mealplannerfinalproject.utils.getPreviousWeek
import com.example.mealplannerfinalproject.utils.toFormattedYearWeek
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val homeUseCases: HomeUseCases
) : ViewModel() {

    private val _state: MutableStateFlow<HomeState> = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _homeUIEvent = MutableSharedFlow<HomeUIEvent>()
    val registerUIEvent = _homeUIEvent.asSharedFlow()

    private var currentWeek = getCurrentWeek()
    private val sessionManager = SessionManager(application.applicationContext)

    // Calculate current week from current date
    private fun getCurrentWeek(): String {
        val currentDate = LocalDate.now()
        val formattedDate = currentDate.format(DateTimeFormatter.ISO_DATE)
        return formattedDate.toFormattedYearWeek()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ToggleMealSelection -> {
                toggleMealSelection(
                    week = event.week,
                    date = event.date,
                    mealId = event.mealId,
                    selected = event.selected
                )
            }

            is HomeEvent.ToggleMealLike -> {
                toggleMealLike(mealId = event.mealId, liked = event.liked)
            }

            is HomeEvent.LoadNextWeek -> {
                currentWeek = getNextWeek(currentWeek)
                fetchWeeklyMeals(currentWeek)
            }

            is HomeEvent.LoadPreviousWeek -> {
                currentWeek = getPreviousWeek(currentWeek)
                fetchWeeklyMeals(currentWeek)
            }

            is HomeEvent.Logout -> {
                sessionManager.clearToken()
            }

            is HomeEvent.LoginClicked -> {
                viewModelScope.launch {
                    _homeUIEvent.emit(HomeUIEvent.LoginClicked)
                }
            }

            is HomeEvent.TryAgainClicked -> {
                currentWeek = getCurrentWeek()
                fetchWeeklyMeals(currentWeek)
            }
        }
    }

    init {
        // Fetch on init
        fetchWeeklyMeals(getCurrentWeek())
        // fetchWeeklyMeals("2025-15")
    }

    // Fetch weekly meals from the API
    fun fetchWeeklyMeals(week: String) = viewModelScope.launch {

        _state.update { it.copy(isLoading = true) }

        val mealResult = homeUseCases.getWeeklyMeals.invoke(week = week)

        _state.update { it.copy(jwtExpired = false) }
        _state.update { it.copy(isLoading = false) }
        _state.update { it.copy(errorMessage = null) }

        when (mealResult) {
            is NetworkResult.Success -> {
                val data = mealResult.data.toUI()
                _state.update {
                    it.copy(
                        week = data.week,
                        days = data.days,
                        hasNext = data.hasNext,
                        hasPrevious = data.hasPrevious,
                        isLoading = false,
                        jwtExpired = false,
                        isSuccess = true
                    )
                }
            }

            is NetworkResult.Error -> {
                if (mealResult.code == 401) {
                    _state.update { it.copy(jwtExpired = true) }
                } else {
                    val message = NetworkErrorHandler.getErrorMessage(
                        code = mealResult.code,
                        defaultMessage = mealResult.message
                    )
                    _state.update { it.copy(errorMessage = message) }
                    _homeUIEvent.emit(HomeUIEvent.ShowToast(message))
                }
            }
        }

    }

    // Select a meal
    fun toggleMealSelection(week: String, date: String, mealId: Int?, selected: Boolean) {
        val previousState = _state.value

        _state.update { HomeState.toggleMealSelection(it, mealId, date) }

        // API call
        viewModelScope.launch {
            try {
                homeUseCases.toggleMealSelection.invoke(
                    week = week,
                    date = date,
                    mealId = mealId,
                    selected = selected
                )
            } catch (e: Exception) {
                _state.value = previousState
            }
        }
    }

    // Handle liking a meal
    fun toggleMealLike(mealId: Int?, liked: Boolean) {
        val previousState = _state.value

        _state.update { HomeState.toggleMealLike(it, mealId) }

        viewModelScope.launch {
            try {
                // Make API call
                homeUseCases.toggleMealLike(mealId = mealId, liked = liked)
            } catch (e: Exception) {
                _state.value = previousState
            }
        }
    }
}