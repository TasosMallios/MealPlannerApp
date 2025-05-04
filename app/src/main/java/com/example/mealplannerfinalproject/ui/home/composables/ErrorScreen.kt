package com.example.mealplannerfinalproject.ui.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.mealplannerfinalproject.R
import com.example.mealplannerfinalproject.ui.home.HomeEvent
import com.example.mealplannerfinalproject.ui.home.HomeState

@Composable
fun ErrorScreen(
    state: HomeState,
    onEvent: (HomeEvent) -> Unit = {},
) {
    Dialog(
        onDismissRequest = { state.errorMessage },
        DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(200.dp)
                .width(250.dp)
                .background(White, shape = RoundedCornerShape(8.dp))
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Something went wrong. Check your internet connection and try again",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(16.dp)
                )
                Button(
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.primary)),
                    onClick = {
                        onEvent(HomeEvent.TryAgainClicked)
                    }
                )
                {
                    Text(text = "Try again")
                }
            }
        }
    }
    LoadingScreen()
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    ErrorScreen(
        state = HomeState(
            errorMessage = "error"
        ),
    )
}