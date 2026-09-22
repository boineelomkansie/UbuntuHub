package com.ubuntuhub.app.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ubuntuhub.app.R
import com.google.firebase.auth.FirebaseAuth

private val UbuntuGreen = Color(0xFF205C3B)
private val UbuntuOrange = Color(0xFFF39A24)
private val UbuntuCream = Color(0xFFFFF4E5)

@Composable
fun LoginScreen(
    onRegisterClick: () -> Unit = {},
    onForgotPasswordClick: () -> Unit = {},
    onLoginSuccess: () -> Unit
) {

    // Firebase Authentication instance.
    val auth = remember {
        FirebaseAuth.getInstance()
    }

    var emailOrPhone by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var passwordVisible by remember {
        mutableStateOf(false)
    }

    var errorMessage by remember {
        mutableStateOf("")
    }

    var isLoggingIn by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(UbuntuCream)
    ) {

        // Bottom community landscape.
        Image(
            painter = painterResource(
                id = R.drawable.ubuntuhub_landscape
            ),
            contentDescription = "UbuntuHub community landscape",
            modifier = Modifier
                .fillMaxWidth()
                .height(830.dp)
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 24.dp,
                    bottom = 190.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // UbuntuHub logo.
            Image(
                painter = painterResource(
                    id = R.drawable.ubuntuhub_logo
                ),
                contentDescription = "UbuntuHub logo",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "Welcome Back",
                color = UbuntuGreen,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Login to continue helping your community.",
                color = Color.Black,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Email or phone label.
            Text(
                text = "Email or Phone",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = emailOrPhone,
                onValueChange = {
                    emailOrPhone = it
                    errorMessage = ""
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Enter your email or phone")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(22.dp))

            // Password label.
            Text(
                text = "Password",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    errorMessage = ""
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Enter your password")
                },
                trailingIcon = {
                    TextButton(
                        onClick = {
                            passwordVisible = !passwordVisible
                        }
                    ) {
                        Text(
                            text = if (passwordVisible) {
                                "Hide"
                            } else {
                                "Show"
                            },
                            color = UbuntuGreen
                        )
                    }
                },
                singleLine = true,
                visualTransformation = if (passwordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Forgot password.
            TextButton(
                onClick = onForgotPasswordClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 4.dp)
            ) {
                Text(
                    text = "Forgot Password?",
                    modifier = Modifier.fillMaxWidth(),
                    color = UbuntuGreen,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            // Login button.
            Button(
                onClick = {

                    val trimmedEmailOrPhone = emailOrPhone.trim()

                    when {

                        trimmedEmailOrPhone.isBlank() -> {
                            errorMessage =
                                "Please enter your email or phone."
                        }

                        password.isBlank() -> {
                            errorMessage =
                                "Please enter your password."
                        }

                        password.length < 6 -> {
                            errorMessage =
                                "Password must contain at least 6 characters."
                        }

                        !android.util.Patterns.EMAIL_ADDRESS
                            .matcher(trimmedEmailOrPhone)
                            .matches() -> {
                            errorMessage =
                                "Please enter the email address used to register."
                        }

                        else -> {

                            errorMessage = ""
                            isLoggingIn = true

                            auth.signInWithEmailAndPassword(
                                trimmedEmailOrPhone,
                                password
                            ).addOnCompleteListener { task ->

                                isLoggingIn = false

                                if (task.isSuccessful) {

                                    // Firebase login successful.
                                    onLoginSuccess()

                                } else {

                                    errorMessage =
                                        task.exception?.localizedMessage
                                            ?: "Login failed. Please check your email and password."
                                }
                            }
                        }
                    }
                },
                enabled = !isLoggingIn,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = UbuntuGreen
                )
            ) {
                Text(
                    text = if (isLoggingIn) {
                        "Logging In..."
                    } else {
                        "Login"
                    },
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Error message.
            if (errorMessage.isNotEmpty()) {

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = errorMessage,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFFD32F2F),
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Social login divider.
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFBDBDBD)
                )

                Text(
                    text = "or continue with",
                    modifier = Modifier.padding(horizontal = 12.dp),
                    fontSize = 14.sp,
                    color = Color.Black
                )

                HorizontalDivider(
                    modifier = Modifier.weight(1f),
                    color = Color(0xFFBDBDBD)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            // Google and Apple buttons.
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                OutlinedButton(
                    onClick = {
                        // Google authentication will be connected later.
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(54.dp),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text(
                        text = "Google",
                        fontWeight = FontWeight.Medium
                    )
                }

                OutlinedButton(
                    onClick = {
                        // Apple authentication will be connected later.
                    },
                    modifier = Modifier
                        .weight(1f)
                        .height(54.dp),
                    shape = RoundedCornerShape(14.dp)
                ) {
                    Text(
                        text = "Apple",
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Registration link.
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Don't have an account? ",
                    fontSize = 15.sp,
                    color = Color.Black
                )

                TextButton(
                    onClick = onRegisterClick
                ) {
                    Text(
                        text = "Sign Up",
                        color = UbuntuOrange,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}