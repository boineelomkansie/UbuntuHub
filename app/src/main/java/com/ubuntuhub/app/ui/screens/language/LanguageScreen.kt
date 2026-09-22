package com.ubuntuhub.app.ui.screens.language

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ubuntuhub.app.R

private val UbuntuGreen = Color(0xFF205C3B)
private val UbuntuOrange = Color(0xFFF39A24)
private val UbuntuCream = Color(0xFFFAE8D4)
private val UbuntuWhite = Color(0xFFFFFCF8)
private val UbuntuBorder = Color(0xFFE3D5C4)
private val UbuntuText = Color(0xFF202124)

@Composable
fun LanguageScreen(
    onContinueClick: (String) -> Unit = {}
) {
    val languages = listOf(
        "English",
        "isiZulu",
        "isiXhosa",
        "Afrikaans",
        "Sesotho",
        "Other"
    )

    var selectedLanguage by remember {
        mutableStateOf("English")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(UbuntuCream)
    ) {

        /*
         * Lower landscape section.
         * Uses the existing UbuntuHub landscape asset.
         */
        Image(
            painter = painterResource(id = R.drawable.ubuntuhub_landscape),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(270.dp),
            contentScale = ContentScale.Crop
        )

        /*
         * Dark green background behind the lower part of the screen.
         */
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .height(150.dp)
                .background(UbuntuGreen.copy(alpha = 0.96f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 34.dp,
                    end = 34.dp,
                    top = 32.dp,
                    bottom = 24.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(18.dp))

            /*
             * UbuntuHub logo
             */
            Image(
                painter = painterResource(id = R.drawable.ubuntuhub_logo),
                contentDescription = "UbuntuHub logo",
                modifier = Modifier.size(110.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Choose Your Language",
                color = UbuntuGreen,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Select your preferred language to use UbuntuHub",
                color = UbuntuText,
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(20.dp))

            /*
             * Language options
             */
            languages.forEach { language ->

                LanguageOption(
                    language = language,
                    selected = selectedLanguage == language,
                    onClick = {
                        selectedLanguage = language
                    }
                )

                Spacer(modifier = Modifier.height(4.dp))
            }

            Spacer(modifier = Modifier.weight(1f))

            /*
             * Continue button
             */
            Button(
                onClick = {
                    onContinueClick(selectedLanguage)
                },
                modifier = Modifier
                    .width(178.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(9.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = UbuntuGreen
                )
            ) {
                Text(
                    text = "Continue",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal
                )
            }

            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Composable
private fun LanguageOption(
    language: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                if (selected) {
                    UbuntuGreen.copy(alpha = 0.08f)
                } else {
                    Color.Transparent
                }
            )
            .border(
                width = 1.dp,
                color = if (selected) {
                    UbuntuGreen
                } else {
                    UbuntuBorder
                },
                shape = RoundedCornerShape(10.dp)
            )
            .clickable { onClick() }
            .padding(
                start = 18.dp,
                end = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            text = language,
            color = UbuntuGreen,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        if (selected) {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .clip(CircleShape)
                    .background(UbuntuGreen),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "✓",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .border(
                        width = 1.dp,
                        color = UbuntuBorder,
                        shape = CircleShape
                    )
            )
        }
    }
}