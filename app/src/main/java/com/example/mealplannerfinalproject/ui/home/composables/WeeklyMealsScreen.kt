package com.example.mealplannerfinalproject.ui.home.composables

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mealplannerfinalproject.R
import com.example.mealplannerfinalproject.ui.composables.MealDay
import com.example.mealplannerfinalproject.ui.home.HomeEvent
import com.example.mealplannerfinalproject.ui.home.HomeState
import com.example.mealplannerfinalproject.ui.model.MealDayUI
import com.example.mealplannerfinalproject.ui.model.MealUI
import com.example.mealplannerfinalproject.utils.toFormattedDayMonth
import com.example.mealplannerfinalproject.utils.toFormattedYearWeek

@Composable
fun WeeklyMealsScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit = {}
) {
    val context = LocalContext.current

    val firstDate = state.days.first().date.toFormattedDayMonth()
    val lastDate = state.days.last().date.toFormattedDayMonth()
    val dateRange = "$firstDate - $lastDate"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .alpha(0f),
                painter = painterResource(id = R.drawable.ic_logout_24),
                contentDescription = "logout",
            )
            Text(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                text = stringResource(id = R.string.week_selection),
                textAlign = TextAlign.Center
            )
            Icon(
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        onEvent(HomeEvent.Logout)
                        (context as Activity).finish()
                    },
                painter = painterResource(id = R.drawable.ic_logout_24),
                contentDescription = "logout",
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Box(
                modifier = Modifier
                    .padding(0.dp, 8.dp)
                    .clip(RoundedCornerShape(50))
                    .border(1.dp, Color.Black, RoundedCornerShape(50.dp))
                    .background(
                        if (!state.hasPrevious) {
                            colorResource(id = R.color.disabled_btn).copy(alpha = 0.5f)
                        } else {
                            colorResource(id = R.color.primary)
                        }
                    )
                    .clickable {
                        if (state.hasPrevious) {
                            onEvent(HomeEvent.LoadPreviousWeek)
                        }
                    }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_baseline_arrow_back_ios_new_24
                    ),
                    modifier = Modifier
                        .size(12.dp),
                    contentDescription = "Arrow Back",
                )
            }
            Box(
                modifier = Modifier
                    .weight(2f)
                    .padding(8.dp, 16.dp)
                    .clip(RoundedCornerShape(50))
                    .border(1.dp, Color.Black, RoundedCornerShape(50.dp))
                    .background(colorResource(id = R.color.primary))
                    .clickable {
                    }
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .align(Alignment.Center),
                    text = dateRange,
                    fontWeight = FontWeight.Bold
                )
            }
            Box(
                modifier = Modifier
                    .padding(0.dp, 8.dp)
                    .clip(RoundedCornerShape(50))
                    .border(1.dp, Color.Black, RoundedCornerShape(50.dp))
                    .background(
                        if (!state.hasNext) {
                            colorResource(id = R.color.disabled_btn).copy(alpha = 0.5f)
                        } else {
                            colorResource(id = R.color.primary)
                        }
                    )
                    .clickable {
                        if (state.hasNext) {
                            onEvent(HomeEvent.LoadNextWeek)
                        }
                    }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(12.dp),
                    painter = painterResource(
                        id = R.drawable.ic_baseline_arrow_forward_ios_24
                    ),
                    contentDescription = "Arrow Forward",
                )
            }
        }
        // Display each days meal plan
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
        ) {
            state.days.map { mealDayUi ->
                // Meal day composable for each day
                item {
                    MealDay(
                        mealDayUI = mealDayUi,
                        week = mealDayUi.date.toFormattedYearWeek(),
                        date = mealDayUi.date.toString(),
                        onEvent = onEvent
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeeklyMealsScreenPreview() {
    WeeklyMealsScreen(
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
                            liked = true,
                            selected = true
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
            hasPrevious = false,
            hasNext = false,
            isSuccess = true,
        )
    )
}