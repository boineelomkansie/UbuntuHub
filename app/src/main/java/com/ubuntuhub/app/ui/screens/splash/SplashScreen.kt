package com.ubuntuhub.app.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ubuntuhub.app.R

private val SplashDarkGreen = Color(0xFF082B1B)
private val SplashGreen = Color(0xFF123F29)
private val SplashOrange = Color(0xFFF39A24)
private val SplashWhite = Color.White

@Composable
fun SplashScreen(
    onFinished: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        SplashDarkGreen,
                        SplashGreen,
                        SplashDarkGreen
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.ubuntuhub_logo),
                contentDescription = "UbuntuHub Logo",
                modifier = Modifier.size(190.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Ubuntu",
                    color = SplashWhite,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Hub",
                    color = SplashOrange,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "Our Community. Our Strength",
                color = SplashWhite,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Helping each other, everyday",
                color = SplashOrange,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(34.dp))

            CircularProgressIndicator(
                modifier = Modifier.size(48.dp),
                color = SplashWhite,
                trackColor = SplashOrange,
                strokeWidth = 5.dp
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Loading...",
                color = SplashWhite,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}