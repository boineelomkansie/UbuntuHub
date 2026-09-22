package com.ubuntuhub.app.ui.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.ubuntuhub.app.R

private val UbuntuGreen = Color(0xFF205C3B)
private val UbuntuOrange = Color(0xFFF39A24)
private val UbuntuCream = Color(0xFFFFF4E5)
private val UbuntuError = Color(0xFFD32F2F)

@Composable
fun ForgotPasswordScreen(
    onBackToLoginClick: () -> Unit = {}
) {

    val auth = remember {
        FirebaseAuth.getInstance()
    }

    var email by remember {
        mutableStateOf("")
    }

    var errorMessage by remember {
        mutableStateOf("")
    }

    var successMessage by remember {
        mutableStateOf("")
    }

    var isSending by remember {
        mutableStateOf(false)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(UbuntuCream)
    ) {

        // UbuntuHub community landscape.
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
                text = "Forgot Password?",
                color = UbuntuGreen,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Enter your email and we'll send you a password reset link.",
                color = Color.Black,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = "Email",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = email,
                onValueChange = {
                    email = it
                    errorMessage = ""
                    successMessage = ""
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Enter your email")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                ),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(22.dp))

            Button(
                onClick = {

                    val trimmedEmail = email.trim()

                    when {

                        trimmedEmail.isBlank() -> {
                            errorMessage =
                                "Please enter your email."
                            successMessage = ""
                        }

                        !android.util.Patterns.EMAIL_ADDRESS
                            .matcher(trimmedEmail)
                            .matches() -> {
                            errorMessage =
                                "Please enter a valid email address."
                            successMessage = ""
                        }

                        else -> {

                            errorMessage = ""
                            successMessage = ""
                            isSending = true

                            auth.sendPasswordResetEmail(
                                trimmedEmail
                            ).addOnCompleteListener { task ->

                                isSending = false

                                if (task.isSuccessful) {

                                    successMessage =
                                        "Password reset email sent. Check your inbox."

                                } else {

                                    errorMessage =
                                        task.exception?.localizedMessage
                                            ?: "Unable to send password reset email."
                                }
                            }
                        }
                    }
                },
                enabled = !isSending,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = UbuntuGreen
                )
            ) {
                Text(
                    text = if (isSending) {
                        "Sending..."
                    } else {
                        "Send Reset Link"
                    },
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            if (errorMessage.isNotEmpty()) {

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = errorMessage,
                    modifier = Modifier.fillMaxWidth(),
                    color = UbuntuError,
                    fontSize = 14.sp
                )
            }

            if (successMessage.isNotEmpty()) {

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = successMessage,
                    modifier = Modifier.fillMaxWidth(),
                    color = UbuntuGreen,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            TextButton(
                onClick = onBackToLoginClick
            ) {
                Text(
                    text = "Back to Login",
                    color = UbuntuOrange,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}