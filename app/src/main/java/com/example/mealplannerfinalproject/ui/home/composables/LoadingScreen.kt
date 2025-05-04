package com.example.mealplannerfinalproject.ui.home.composables

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mealplannerfinalproject.ui.composables.SkeletonLoaderCard
import com.example.mealplannerfinalproject.ui.composables.shimmerEffect

@Composable
fun LoadingScreen() {
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
            horizontalArrangement = Arrangement.Center
        )
        {
                Box(
                    modifier = Modifier.size(120.dp, 32.dp)
                )
                {
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(width = 120.dp, height = 16.dp)
                            .shimmerEffect()

                    )
                }
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
                    .shimmerEffect()
                    .padding(16.dp)
                    .size(12.dp),
            )
            Box(
                modifier = Modifier
                    .weight(2f)
                    .padding(8.dp, 16.dp)
                    .clip(RoundedCornerShape(50))
                    .shimmerEffect()
                    .padding(16.dp)
                    .size(12.dp)
            )
            Box(
                modifier = Modifier
                    .padding(0.dp, 8.dp)
                    .clip(RoundedCornerShape(50))
                    .shimmerEffect()
                    .padding(16.dp)
                    .size(12.dp),
            )
        }
        LazyColumn(modifier = Modifier.fillMaxSize(), userScrollEnabled = false) {
            items(3) {
                Box(
                    modifier = Modifier
                        .padding(0.dp, 16.dp, 0.dp, 0.dp)
                        .size(width = 250.dp, height = 32.dp)
                        .clip(RoundedCornerShape(50))
                        .shimmerEffect()
                )
                SkeletonLoaderCard()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingScreenPreview() {
    LoadingScreen()
}