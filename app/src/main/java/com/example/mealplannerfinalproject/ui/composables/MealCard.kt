package com.example.mealplannerfinalproject.ui.composables

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mealplannerfinalproject.R
import com.example.mealplannerfinalproject.ui.home.HomeEvent
import com.example.mealplannerfinalproject.ui.model.MealUI

// Meal card composable
@Composable
fun MealCard(
    meal: MealUI,
    pagerState: Int,
    week: String,
    date: String,
    onEvent: (HomeEvent) -> Unit = {},
) {

    val startPadding by animateDpAsState(
        targetValue = if (pagerState == 0) 0.dp else 8.dp,
        animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing)
    )

    Card(
        modifier = Modifier
            .padding(startPadding, 8.dp, 8.dp, 8.dp)
            .width(300.dp)
            .height(300.dp)
            .clickable { },
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.primary)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = meal.name.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.width(200.dp)
                )
                LikeButton(
                    liked = meal.liked == true,
                    onLikeToggle = { isLiked ->
                        onEvent(HomeEvent.ToggleMealLike(meal.id, isLiked))
                    }
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(Color.White)
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(R.string.type),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(8.dp, 0.dp)
                        .align(Alignment.Center)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = meal.description.toString(),
                modifier = Modifier
                    .width(250.dp)
                    .padding(0.dp, 8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(id = R.string.kcal) + " ${meal.calories}",
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            PrimaryButton(
                week = week,
                text = stringResource(id = R.string.select),
                backGroundColor = Color.Transparent,
                isSelected = meal.selected == true,
                onClick = {
                    onEvent(
                        HomeEvent.ToggleMealSelection(
                            week,
                            date,
                            mealId = meal.id,
                            selected = meal.selected != true
                        )
                    )
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealCardPreview() {
    MealCard(
        meal = MealUI(
            name = "Avocado Toast",
            description = "Whole-grain toast with avocado and eggs",
            calories = 350,
            selected = false,
            liked = false
        ),
        pagerState = 0,
        week = "2025-18",
        date = "2025-05-02",
    )
}