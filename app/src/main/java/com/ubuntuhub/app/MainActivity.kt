package com.ubuntuhub.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ubuntuhub.app.navigation.AppNavigation
import com.ubuntuhub.app.ui.theme.UbuntuHubTheme

/**
 * Main entry point of the UbuntuHub Android application.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Allows the application UI to use the full screen area.
        enableEdgeToEdge()

        setContent {
            // Applies the UbuntuHub visual theme.
            UbuntuHubTheme {

                // Loads the application's navigation graph.
                AppNavigation()
            }
        }
    }
}