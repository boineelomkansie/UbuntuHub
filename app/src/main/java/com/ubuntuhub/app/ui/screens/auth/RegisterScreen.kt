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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ubuntuhub.app.R
import androidx.compose.foundation.text.KeyboardOptions
import com.google.firebase.auth.FirebaseAuth
import com.ubuntuhub.app.data.RetrofitClient
import com.ubuntuhub.app.data.UserSyncRequest
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

private val UbuntuGreen = Color(0xFF205C3B)
private val UbuntuOrange = Color(0xFFF39A24)
private val UbuntuCream = Color(0xFFFAE8D4)
private val UbuntuError = Color(0xFFD32F2F)

@Composable
fun RegisterScreen(
    onLoginClick: () -> Unit = {},
    onRegisterSuccess: () -> Unit
) {

    val auth = remember {
        FirebaseAuth.getInstance()
    }

    val coroutineScope = rememberCoroutineScope()

    // Form fields
    var fullName by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var phone by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    // Password visibility
    var passwordVisible by remember {
        mutableStateOf(false)
    }

    var confirmPasswordVisible by remember {
        mutableStateOf(false)
    }

    // Validation message
    var errorMessage by remember {
        mutableStateOf("")
    }

    var isRegistering by remember {
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

            // Page title.
            Text(
                text = "Create Account",
                color = UbuntuGreen,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Join your community and start helping.",
                color = Color.Black,
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Full name.
            Text(
                text = "Full Name",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = fullName,
                onValueChange = {
                    fullName = it
                    errorMessage = ""
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Enter your full name")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                ),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Email.
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

            Spacer(modifier = Modifier.height(18.dp))

            // Phone number.
            Text(
                text = "Phone Number",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = phone,
                onValueChange = {
                    phone = it
                    errorMessage = ""
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Enter your phone number")
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                ),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(18.dp))

            // Password.
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
                    Text("Create a password")
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

            Spacer(modifier = Modifier.height(18.dp))

            // Confirm password.
            Text(
                text = "Confirm Password",
                modifier = Modifier.fillMaxWidth(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                    errorMessage = ""
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text("Confirm your password")
                },
                trailingIcon = {
                    TextButton(
                        onClick = {
                            confirmPasswordVisible = !confirmPasswordVisible
                        }
                    ) {
                        Text(
                            text = if (confirmPasswordVisible) {
                                "Hide"
                            } else {
                                "Show"
                            },
                            color = UbuntuGreen
                        )
                    }
                },
                singleLine = true,
                visualTransformation = if (confirmPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(modifier = Modifier.height(22.dp))

            // Create account button.
            Button(
                onClick = {

                    /*
                     * Client-side validation.
                     * Firebase Authentication is used after
                     * all validation checks have passed.
                     */

                    val trimmedName = fullName.trim()
                    val trimmedEmail = email.trim()
                    val trimmedPhone = phone.trim()

                    when {

                        trimmedName.isBlank() -> {
                            errorMessage =
                                "Please enter your full name."
                        }

                        trimmedName.length < 2 -> {
                            errorMessage =
                                "Please enter a valid full name."
                        }

                        trimmedEmail.isBlank() -> {
                            errorMessage =
                                "Please enter your email."
                        }

                        !android.util.Patterns.EMAIL_ADDRESS
                            .matcher(trimmedEmail)
                            .matches() -> {
                            errorMessage =
                                "Please enter a valid email address."
                        }

                        trimmedPhone.isBlank() -> {
                            errorMessage =
                                "Please enter your phone number."
                        }

                        trimmedPhone.length < 10 -> {
                            errorMessage =
                                "Please enter a valid phone number."
                        }

                        password.isBlank() -> {
                            errorMessage =
                                "Please create a password."
                        }

                        password.length < 6 -> {
                            errorMessage =
                                "Password must contain at least 6 characters."
                        }

                        confirmPassword.isBlank() -> {
                            errorMessage =
                                "Please confirm your password."
                        }

                        password != confirmPassword -> {
                            errorMessage =
                                "Passwords do not match."
                        }

                        else -> {

                            errorMessage = ""
                            isRegistering = true

                            auth.createUserWithEmailAndPassword(
                                trimmedEmail,
                                password
                            ).addOnCompleteListener { task ->

                                isRegistering = false

                                if (task.isSuccessful) {

                                    // Firebase account created successfully.
                                    val firebaseUser = auth.currentUser

                                    if (firebaseUser == null) {
                                        isRegistering = false
                                        errorMessage = "Registration succeeded, but the user could not be loaded."
                                        return@addOnCompleteListener
                                    }

                                    // Save the user's full name as their Firebase display name.
                                    val profileUpdates = com.google.firebase.auth.userProfileChangeRequest {
                                        displayName = trimmedName
                                    }

                                    firebaseUser.updateProfile(profileUpdates)
                                        .addOnCompleteListener { profileTask ->

                                            if (!profileTask.isSuccessful) {
                                                isRegistering = false
                                                errorMessage =
                                                    profileTask.exception?.localizedMessage
                                                        ?: "Account created, but profile setup failed."
                                                return@addOnCompleteListener
                                            }

                                            // Sync the Firebase user with the UbuntuHub REST API.
                                            coroutineScope.launch {

                                                try {
                                                    RetrofitClient.apiService.syncUser(
                                                        UserSyncRequest(
                                                            firebaseUid = firebaseUser.uid,
                                                            username = trimmedName,
                                                            email = trimmedEmail
                                                        )
                                                    )

                                                    isRegistering = false

                                                    // Registration and API sync completed successfully.
                                                    onRegisterSuccess()

                                                } catch (e: Exception) {

                                                    isRegistering = false

                                                    errorMessage =
                                                        "Account created, but user data could not be synced. Please try again."
                                                }
                                            }
                                        }

                                } else {

                                    errorMessage =
                                        task.exception?.localizedMessage
                                            ?: "Registration failed. Please try again."
                                }
                            }
                        }
                    }
                },
                enabled = !isRegistering,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = UbuntuGreen
                )
            ) {
                Text(
                    text = if (isRegistering) {
                        "Creating Account..."
                    } else {
                        "Create Account"
                    },
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Validation error.
            if (errorMessage.isNotEmpty()) {

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = errorMessage,
                    modifier = Modifier.fillMaxWidth(),
                    color = UbuntuError,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Login link.
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Already have an account? ",
                    fontSize = 15.sp,
                    color = Color.Black
                )

                TextButton(
                    onClick = onLoginClick
                ) {
                    Text(
                        text = "Login",
                        color = UbuntuOrange,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}