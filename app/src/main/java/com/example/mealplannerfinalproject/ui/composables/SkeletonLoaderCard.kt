package com.example.mealplannerfinalproject.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SkeletonLoaderCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 16.dp)
            .height(250.dp)
            .shimmerEffect()
            .background(color = Color.Gray.copy(alpha = 0.3f), shape = RoundedCornerShape(8.dp))
    )
}

@Preview
@Composable
fun SkeletonLoaderCardPreview() {
    SkeletonLoaderCard()
}