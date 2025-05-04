package com.example.mealplannerfinalproject.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mealplannerfinalproject.R
import com.example.mealplannerfinalproject.utils.isLaterThanCurrentWeek

// Custom button implementation
@Composable
fun PrimaryButton(
    week: String?,
    text: String,
    backGroundColor: Color,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(2.dp, Color.Black, RoundedCornerShape(50.dp))
            .clip(RoundedCornerShape(50.dp))
            .background(
                if (week.isLaterThanCurrentWeek() == true) {
                    backGroundColor
                } else colorResource(R.color.disabled_btn).copy(alpha = 0.4f)
            )
            .clickable {
                if (week.isLaterThanCurrentWeek() == true) {
                    onClick()
                }
            }
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {

            if (isSelected) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_delete_outline_24),
                    contentDescription = "remove",
                    modifier = Modifier.size(24.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))
            }

            Text(
                modifier = Modifier
                    .align(Alignment.CenterVertically),
                text = text,
                color = Color.Black,
                style = TextStyle(fontSize = 16.sp)
            )

        }

    }
}

@Preview(showBackground = false)
@Composable
fun PrimaryButtonPreview() {
    PrimaryButton(
        week = "2025-18",
        text = "Select",
        backGroundColor = Color.Gray,
        isSelected = true,
        onClick = {}
    )
}
