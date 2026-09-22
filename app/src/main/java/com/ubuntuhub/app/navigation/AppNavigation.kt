package com.ubuntuhub.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.ubuntuhub.app.ui.screens.auth.ForgotPasswordScreen
import com.ubuntuhub.app.ui.screens.auth.LoginScreen
import com.ubuntuhub.app.ui.screens.auth.RegisterScreen

import com.ubuntuhub.app.ui.screens.home.HomeScreen

import com.ubuntuhub.app.ui.screens.community.CommunityPost
import com.ubuntuhub.app.ui.screens.community.CommunityScreen

import com.ubuntuhub.app.ui.screens.createpost.CreatePostScreen
import com.ubuntuhub.app.ui.screens.postdetails.PostDetailsScreen

import com.ubuntuhub.app.ui.screens.settings.SettingsScreen

import com.ubuntuhub.app.ui.screens.explore.ExploreScreen
import com.ubuntuhub.app.ui.screens.search.SearchScreen
import com.ubuntuhub.app.ui.screens.activity.ActivityScreen
import com.ubuntuhub.app.ui.screens.notifications.NotificationsScreen
import com.ubuntuhub.app.ui.screens.offline.OfflineScreen
import com.ubuntuhub.app.ui.screens.profile.ProfileScreen
import com.ubuntuhub.app.ui.screens.messages.MessagesScreen

import com.ubuntuhub.app.ui.screens.splash.SplashScreen
import com.ubuntuhub.app.ui.screens.onboard.OnboardScreen
import com.ubuntuhub.app.ui.screens.language.LanguageScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
                // startDestination = "api_test"
    ) {

        // ---------------------------------------------------------
        // SPLASH
        // ---------------------------------------------------------

        composable(Screen.Splash.route) {
            SplashScreen()
        }

        // ---------------------------------------------------------
        // ONBOARDING
        // ---------------------------------------------------------

        composable(Screen.Onboard.route) {
            OnboardScreen()
        }

        // ---------------------------------------------------------
        // LANGUAGE
        // ---------------------------------------------------------

        composable(Screen.Language.route) {
            LanguageScreen()
        }

        // ---------------------------------------------------------
        // LOGIN
        // ---------------------------------------------------------

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

        // ---------------------------------------------------------
        // REGISTER
        // ---------------------------------------------------------

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

        // ---------------------------------------------------------
        // FORGOT PASSWORD
        // ---------------------------------------------------------

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

        // ---------------------------------------------------------
        // HOME
        // ---------------------------------------------------------

        composable(Screen.Home.route) {

            HomeScreen(

                onSettingsClick = {
                    navController.navigate(Screen.Settings.route)
                },

                onCommunityClick = {
                    navController.navigate(Screen.Community.route)
                },

                onHomeClick = {
                    // Already on Home
                },

                onExploreClick = {
                    navController.navigate(Screen.Explore.route)
                },

                onPostClick = {
                    navController.navigate(Screen.CreatePost.route)
                },

                onMessagesClick = {
                    navController.navigate(Screen.Messages.route)
                },

                onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }

        // ---------------------------------------------------------
        // EXPLORE
        // ---------------------------------------------------------

        composable(Screen.Explore.route) {

            ExploreScreen(

                onHomeClick = {
                    navController.navigate(Screen.Home.route)
                },

                onExploreClick = {
                    // Already on Explore
                },

                onPostClick = {
                    navController.navigate(Screen.CreatePost.route)
                },

                onMessagesClick = {
                    navController.navigate(Screen.Messages.route)
                },

                onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }

        // ---------------------------------------------------------
        // CREATE POST
        // ---------------------------------------------------------

        composable(Screen.CreatePost.route) {

            CreatePostScreen(

                onBackClick = {
                    navController.popBackStack()
                },

                onPostCreated = {
                    navController.popBackStack()
                },

                onHomeClick = {
                    navController.navigate(Screen.Home.route)
                },

                onExploreClick = {
                    navController.navigate(Screen.Explore.route)
                },

                onPostClick = {
                    // Already on Create Post
                },

                onMessagesClick = {
                    navController.navigate(Screen.Messages.route)
                },

                onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }

        // ---------------------------------------------------------
        // MESSAGES
        // ---------------------------------------------------------

        composable(Screen.Messages.route) {

            MessagesScreen(

                onHomeClick = {
                    navController.navigate(Screen.Home.route)
                },

                onExploreClick = {
                    navController.navigate(Screen.Explore.route)
                },

                onPostClick = {
                    navController.navigate(Screen.CreatePost.route)
                },

                onMessagesClick = {
                    // Already on Messages
                },

                onProfileClick = {
                    navController.navigate(Screen.Profile.route)
                }
            )
        }

        // ---------------------------------------------------------
        // PROFILE
        // ---------------------------------------------------------

        composable(Screen.Profile.route) {

            ProfileScreen(

                onSettingsClick = {
                    navController.navigate(Screen.Settings.route)
                },

                onMyPostsClick = {
                    navController.navigate(Screen.Activity.route)
                },

                onSavedPostsClick = {
                    navController.navigate(Screen.Activity.route)
                },

                onPeopleHelpedClick = {
                    navController.navigate(Screen.Activity.route)
                },

                onBadgesClick = {
                    navController.navigate(Screen.Activity.route)
                },

                onActivitySummaryClick = {
                    navController.navigate(Screen.Activity.route)
                },

                onNotificationsClick = {
                    navController.navigate(Screen.Notifications.route)
                },

                onHelpSupportClick = {
                    // Reserved for later
                },

                onLogoutClick = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                },

                onHomeClick = {
                    navController.navigate(Screen.Home.route)
                },

                onExploreClick = {
                    navController.navigate(Screen.Explore.route)
                },

                onPostClick = {
                    navController.navigate(Screen.CreatePost.route)
                },

                onMessagesClick = {
                    navController.navigate(Screen.Messages.route)
                }
            )
        }

        // ---------------------------------------------------------
        // SEARCH
        // ---------------------------------------------------------

        composable(Screen.Search.route) {
            SearchScreen()
        }

        // ---------------------------------------------------------
        // ACTIVITY
        // ---------------------------------------------------------

        composable(Screen.Activity.route) {
            ActivityScreen()
        }

        // ---------------------------------------------------------
        // NOTIFICATIONS
        // ---------------------------------------------------------

        composable(Screen.Notifications.route) {
            NotificationsScreen()
        }

        // ---------------------------------------------------------
        // OFFLINE
        // ---------------------------------------------------------

        composable(Screen.Offline.route) {
            OfflineScreen()
        }

        // ---------------------------------------------------------
        // SETTINGS
        // ---------------------------------------------------------

        composable(Screen.Settings.route) {

            SettingsScreen(

                onBackClick = {
                    navController.popBackStack()
                },

                onLogoutClick = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        // ---------------------------------------------------------
        // COMMUNITY
        // ---------------------------------------------------------

        composable(Screen.Community.route) {

            CommunityScreen(

                onBackClick = {
                    navController.popBackStack()
                },

                onCreatePostClick = {
                    navController.navigate(Screen.CreatePost.route)
                },

                onPostClick = { post ->

                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("postAuthor", post.author)

                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("postCategory", post.category)

                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("postTitle", post.title)

                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("postDescription", post.description)

                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("postTime", post.time)

                    navController.navigate(Screen.PostDetails.route)
                }
            )
        }

        // ---------------------------------------------------------
        // POST DETAILS
        // ---------------------------------------------------------

        composable(Screen.PostDetails.route) {

            val savedStateHandle =
                navController.previousBackStackEntry?.savedStateHandle

            val selectedPost = CommunityPost(

                author = savedStateHandle?.get<String>("postAuthor")
                    ?: "Community Member",

                category = savedStateHandle?.get<String>("postCategory")
                    ?: "Community Update",

                title = savedStateHandle?.get<String>("postTitle")
                    ?: "Community Post",

                description = savedStateHandle?.get<String>("postDescription")
                    ?: "",

                time = savedStateHandle?.get<String>("postTime")
                    ?: "Just now"
            )

            PostDetailsScreen(

                post = selectedPost,

                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        // ---------------------------------------------------------
        // NOTICES
        // ---------------------------------------------------------

        composable(Screen.Notices.route) {
            // Notices screen will be connected later.
        }
    }
}