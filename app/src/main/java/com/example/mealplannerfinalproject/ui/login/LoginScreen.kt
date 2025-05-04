package com.example.mealplannerfinalproject.ui.login

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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mealplannerfinalproject.R

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToHomeScreen: () -> Unit,
    navigateToRegisterScreen: () -> Unit
) {
    val state = viewModel.state.collectAsState()

    val context = LocalContext.current

    LoginContent(state.value) { event -> viewModel.onEvent(event) }

    LaunchedEffect(Unit) {
        viewModel.loginUIEvent.collect { event ->
            when(event) {
                is LoginUIEvent.Success -> {
                    navigateToHomeScreen()
                }

                is LoginUIEvent.OnRegisterClicked -> {
                    navigateToRegisterScreen()
                }

                is LoginUIEvent.ShowToast -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun LoginContent(
    state: LoginState = LoginState(),
    onEvent: (LoginEvent) -> Unit = {}
) {

    Box(modifier = Modifier.fillMaxSize())
    {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
            ) {
                Icon(
                    modifier = Modifier.size(100.dp),
                    tint = colorResource(R.color.primary),
                    painter = painterResource(R.drawable.ic_login),
                    contentDescription = "Register Icon"
                )
                Text(
                    text = stringResource(R.string.login),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(32.dp))

                Column(
                    modifier = Modifier.width(300.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                )
                {
                    OutlinedTextField(
                        value = state.email,
                        onValueChange = { onEvent(LoginEvent.EmailChanged(it)) },
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
                        modifier = Modifier.fillMaxWidth()
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

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = state.password,
                        onValueChange = { onEvent(LoginEvent.PasswordChanged(it)) },
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
                        isError = state.passwordError != null,
                        modifier = Modifier.fillMaxWidth()
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
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            colorResource(R.color.primary)
                        ),
                        onClick = {
                            onEvent(LoginEvent.LoginClicked)
                        },
                        enabled = state.emailError == null && state.passwordError == null
                    ) {
                        if (state.isLoading) {
                            CircularProgressIndicator(
                                modifier = Modifier
                                    .size(20.dp),
                                color = colorResource(R.color.white),
                            )
                        } else {
                            Text(text = stringResource(id = R.string.login))
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Text(stringResource(R.string.new_member_message))
                Spacer(modifier = Modifier.height(8.dp))
                // Go to Register Button
                Button(
                    modifier = Modifier.width(150.dp),
                    colors = ButtonDefaults.buttonColors(colorResource(R.color.black)),
                    onClick = { onEvent(LoginEvent.RegisterClicked) }
                ) {
                    Text(stringResource(R.string.register))
                }

            }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginContentPreview() {
    LoginContent(
        LoginState(email = "test@email.com", password = "123456")
    )
}