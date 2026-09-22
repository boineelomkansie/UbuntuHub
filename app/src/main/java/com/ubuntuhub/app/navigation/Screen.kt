package com.ubuntuhub.app.navigation

sealed class Screen(val route: String) {

    data object Splash : Screen("splash")

    data object Onboard : Screen("onboard")

    data object Language : Screen("language")

    data object Login : Screen("login")

    data object Register : Screen("register")

    data object ForgotPassword : Screen("forgot_password")

    data object Home : Screen("home")

    data object Explore : Screen("explore")

    data object Search : Screen("search")

    data object Activity : Screen("activity")

    data object Notifications : Screen("notifications")

    data object Offline : Screen("offline")

    data object Messages : Screen("messages")

    data object Profile : Screen("profile")

    data object Settings : Screen("settings")

    data object CreatePost : Screen("create_post")

    data object PostDetails : Screen("post_details")

    data object Community : Screen("community")

    data object Notices : Screen("notices")
}