package com.example.mealplannerfinalproject.ui.register

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mealplannerfinalproject.R

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    navigateToLoginScreen: () -> Unit,
) {

    val state = viewModel.state.collectAsState()

    val context = LocalContext.current

    RegisterContent(state.value) { event -> viewModel.onEvent(event) }

    LaunchedEffect(Unit) {
        viewModel.registerUIEvent.collect { event ->
            when (event) {
                is RegisterUIEvent.Success -> {
                    navigateToLoginScreen()
                }

                is RegisterUIEvent.OnLoginClicked -> {
                    navigateToLoginScreen()
                }

                is RegisterUIEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun RegisterContent(
    state: RegisterState = RegisterState(),
    onEvent: (RegisterEvent) -> Unit = {}
) {

    Box(modifier = Modifier.fillMaxSize())
    {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Icon(
                modifier = Modifier.size(64.dp),
                tint = colorResource(R.color.primary),
                painter = painterResource(R.drawable.ic_register),
                contentDescription = "Register Icon"
            )

            Text(
                text = stringResource(R.string.register),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))

            Column(
                modifier = Modifier.width(300.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = state.firstName,
                    onValueChange = { onEvent(RegisterEvent.NameChanged(it)) },
                    label = { Text(stringResource(R.string.first_name)) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        focusedIndicatorColor = colorResource(id = R.color.primary),
                        errorIndicatorColor = Color.Red,
                        cursorColor = colorResource(id = R.color.primary),
                        focusedLabelColor = colorResource(id = R.color.primary),
                        errorLabelColor = Color.Red
                    ),
                    singleLine = true,
                    isError = state.firstNameError != null,
                    modifier = Modifier.fillMaxWidth()
                )
                // Error message
                Spacer(modifier = Modifier.height(8.dp))

                AnimatedVisibility(visible = state.firstNameError != null) {
                    Text(
                        text = state.firstNameError.orEmpty(),
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = state.lastName,
                    onValueChange = { onEvent(RegisterEvent.SurnameChanged(it)) },
                    label = { Text(stringResource(R.string.last_name)) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        focusedIndicatorColor = colorResource(id = R.color.primary),
                        errorIndicatorColor = Color.Red,
                        cursorColor = colorResource(id = R.color.primary),
                        focusedLabelColor = colorResource(id = R.color.primary),
                        errorLabelColor = Color.Red
                    ),
                    singleLine = true,
                    isError = state.lastNameError != null,
                    modifier = Modifier.fillMaxWidth()
                )

                // Error message
                Spacer(modifier = Modifier.height(8.dp))

                AnimatedVisibility(visible = state.lastNameError != null) {
                    Text(
                        text = state.lastNameError.orEmpty(),
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = state.email,
                    onValueChange = { onEvent(RegisterEvent.EmailChanged(it)) },
                    label = { Text(stringResource(R.string.email)) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        focusedIndicatorColor = colorResource(id = R.color.primary),
                        errorIndicatorColor = MaterialTheme.colorScheme.error,
                        cursorColor = colorResource(id = R.color.primary),
                        focusedLabelColor = colorResource(id = R.color.primary),
                        errorLabelColor = MaterialTheme.colorScheme.error
                    ),
                    singleLine = true,
                    isError = state.emailError != null,
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )

                // Error message
                Spacer(modifier = Modifier.height(8.dp))

                AnimatedVisibility(visible = state.emailError != null) {
                    Text(
                        text = state.emailError.orEmpty(),
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = state.password,
                    onValueChange = { onEvent(RegisterEvent.PasswordChanged(it)) },
                    label = { Text(stringResource(R.string.password)) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        focusedIndicatorColor = colorResource(id = R.color.primary),
                        errorIndicatorColor = MaterialTheme.colorScheme.error,
                        cursorColor = colorResource(id = R.color.primary),
                        focusedLabelColor = colorResource(id = R.color.primary),
                        errorLabelColor = MaterialTheme.colorScheme.error
                    ),
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    isError = state.passwordError != null,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )

                // Error message
                Spacer(modifier = Modifier.height(8.dp))

                AnimatedVisibility(visible = state.passwordError != null) {
                    Text(
                        text = state.passwordError.orEmpty(),
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        onEvent(RegisterEvent.RegisterClicked)
                    },
                    colors = ButtonDefaults.buttonColors(
                        colorResource(R.color.primary)
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    enabled = (state.emailError == null
                            && state.passwordError == null
                            && state.firstNameError == null
                            && state.lastNameError == null)
                ) {
                    if (state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(20.dp),
                            color = colorResource(R.color.white),
                        )
                    } else {
                        Text(text = stringResource(id = R.string.register))
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(stringResource(R.string.already_member_message))
                    // Button to go back to the login screen
                    Button(
                        modifier = Modifier.width(150.dp),
                        colors = ButtonDefaults.buttonColors(colorResource(R.color.black)),
                        onClick = {
                            onEvent(RegisterEvent.LoginClicked)
                        }
                    ) {
                        Text(stringResource(R.string.login))
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterContentPreview() {
    RegisterContent()
}
