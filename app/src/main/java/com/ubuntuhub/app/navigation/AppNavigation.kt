package com.ubuntuhub.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ubuntuhub.app.ui.screens.auth.LoginScreen
import com.ubuntuhub.app.ui.screens.auth.RegisterScreen
import com.ubuntuhub.app.ui.screens.home.HomeScreen
import com.ubuntuhub.app.ui.screens.auth.ForgotPasswordScreen

/**
 * Defines the navigation destinations used throughout UbuntuHub.
 */
sealed class Screen(val route: String) {

    data object Login : Screen("login")

    data object Register : Screen("register")

    data object ForgotPassword : Screen("forgot_password")

    data object Home : Screen("home")

    data object Search : Screen("search")

    data object Notices : Screen("notices")

    data object Profile : Screen("profile")

    data object Settings : Screen("settings")

    data object CreatePost : Screen("create_post")

    data object PostDetails : Screen("post_details")
}

/**
 * Controls navigation between the different screens in UbuntuHub.
 */
@Composable
fun AppNavigation() {

    // Creates and remembers the navigation controller.
    val navController = rememberNavController()

    // Defines the navigation graph for the application.
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {

        // Login screen
        composable(Screen.Login.route) {
            LoginScreen(
                onRegisterClick = {
                    navController.navigate(Screen.Register.route)
                },
                onForgotPasswordClick = {
                    navController.navigate(Screen.ForgotPassword.route)
                },
                onLoginSuccess = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Login.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Registration screen
        composable(Screen.Register.route) {
            RegisterScreen(
                onLoginClick = {
                    navController.navigate(Screen.Login.route)
                },
                onRegisterSuccess = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Register.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Screen.ForgotPassword.route) {
            ForgotPasswordScreen(
                onBackToLoginClick = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.ForgotPassword.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // Home screen
        composable(Screen.Home.route) {
            HomeScreen()
        }
    }
}