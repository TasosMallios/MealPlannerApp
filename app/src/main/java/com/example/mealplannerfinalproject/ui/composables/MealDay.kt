package com.example.mealplannerfinalproject.ui.composables

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mealplannerfinalproject.ui.home.HomeEvent
import com.example.mealplannerfinalproject.ui.model.MealDayUI
import com.example.mealplannerfinalproject.ui.model.MealUI
import com.example.mealplannerfinalproject.utils.toFormattedDayDate
import com.example.mealplannerfinalproject.utils.toFormattedYearWeek

// Day - Date and meal card composable
@Composable
fun MealDay(
    mealDayUI: MealDayUI,
    week: String,
    date: String,
    onEvent: (HomeEvent) -> Unit = {},
) {
    if (!mealDayUI.meals.isNullOrEmpty()) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = tween(durationMillis = 1000, delayMillis = 300)
                )
        ) {
            // Meal date formatted
            Text(
                modifier = Modifier.padding(0.dp, 16.dp),
                text = mealDayUI.date.toFormattedDayDate(),
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )

            // Meals pager
            val pagerState = rememberPagerState(pageCount = { mealDayUI.meals?.size ?: 0 })

            val contentPadding = PaddingValues(
                start = if (pagerState.currentPage == 0) 0.dp else 32.dp,
                end = if (pagerState.currentPage == 0) 64.dp else 32.dp
            )

            val selectedMeal = mealDayUI.meals.find { it.selected == true }

            // Show selected meal card if exists
            // else show the list of day's meals
            if (selectedMeal != null) {
                SelectedMealCard(
                    week = week,
                    date = date,
                    selectedMeal = selectedMeal,
                    onEvent = onEvent,
                )
            } else {
                HorizontalPager(
                    state = pagerState,
                    contentPadding = contentPadding
                ) { page ->
                    val meal = mealDayUI.meals[page]

                    MealCard(
                        meal = meal,
                        pagerState = pagerState.currentPage,
                        week = mealDayUI.date.toFormattedYearWeek(),
                        date = mealDayUI.date.toString(),
                        onEvent = onEvent
                    )

                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealDayPreview() {
    MealDay(
        mealDayUI = MealDayUI(
            "2025-05-02", mutableListOf(
                MealUI(
                    id = 1,
                    name = "Avocado Toast",
                    description = "Whole-grain toast with avocado and eggs",
                    calories = 350,
                    liked = true,
                ),
                MealUI(
                    id = 2,
                    name = "Grilled Chicken",
                    description = "Grilled chicken with fresh veggies",
                    calories = 500
                )
            )
        ),
        week = "2025-18",
        date = "2025-05-02",
    )
}