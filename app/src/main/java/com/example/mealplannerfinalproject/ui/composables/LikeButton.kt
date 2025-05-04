package com.example.mealplannerfinalproject.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mealplannerfinalproject.R

// Toggle like/unlike on meal cards
@Composable
fun LikeButton(
    liked: Boolean,
    onLikeToggle: (Boolean) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(0.dp, 8.dp)
            .clip(RoundedCornerShape(50))
            .background(colorResource(id = R.color.white))
            .clickable {
                onLikeToggle(!liked)
            }
            .padding(8.dp)
    ) {
        Icon(
            imageVector = if (liked) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            tint = if (liked) Color.Red else Color.DarkGray,
            modifier = Modifier
                .align(Alignment.Center),
            contentDescription = stringResource(id = android.R.string.ok),
        )
    }
}

@Preview
@Composable
fun LikeButtonPreview() {
    LikeButton(
        liked = true,
        onLikeToggle = {}
    )
}