package com.ubuntuhub.app.ui.screens.onboard

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val UbuntuGreen = Color(0xFF205C3B)
private val UbuntuOrange = Color(0xFFF39A24)
private val UbuntuCream = Color(0xFFFAE8D4)
private val UbuntuWhite = Color(0xFFFFFCF8)
private val UbuntuSecondary = Color(0xFF6B7280)
private val UbuntuBorder = Color(0xFFE8DCCF)

@Composable
fun OnboardScreen(
    onGetStartedClick: () -> Unit = {},
    onSkipClick: () -> Unit = {}
) {
    var selectedPage by remember {
        mutableIntStateOf(2)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(UbuntuCream)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 24.dp,
                    end = 24.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // SKIP
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 68.dp
                    ),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Skip",
                    color = UbuntuGreen,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        onSkipClick()
                    }
                )
            }

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            // LOGO PLACEHOLDER
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .background(
                        color = UbuntuGreen,
                        shape = androidx.compose.foundation.shape.CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "U",
                        color = UbuntuOrange,
                        fontSize = 58.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "H",
                        color = Color.White,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            // BRAND NAME
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Ubuntu",
                    color = UbuntuGreen,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Hub",
                    color = UbuntuOrange,
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "Our Community, Our Strength",
                color = UbuntuGreen,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            // DESCRIPTION
            Text(
                text = "UbuntuHub connects neighbours to help each other,\nshare resources, and build stronger communities.",
                color = Color.Black,
                fontSize = 13.sp,
                lineHeight = 18.sp,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(104.dp)
            )

            // FEATURE BUTTONS
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                OnboardFeatureButton(
                    text = "Get Help",
                    modifier = Modifier.weight(1f)
                )

                OnboardFeatureButton(
                    text = "Give Help",
                    modifier = Modifier.weight(1f)
                )

                OnboardFeatureButton(
                    text = "Build Community",
                    modifier = Modifier.weight(1f)
                )
            }

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            // PAGE INDICATORS
            Row(
                horizontalArrangement = Arrangement.spacedBy(7.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(6) { index ->

                    val isSelected = index == selectedPage

                    Box(
                        modifier = Modifier
                            .size(
                                if (isSelected) 8.dp else 7.dp
                            )
                            .background(
                                color = if (isSelected) {
                                    Color.Black
                                } else {
                                    Color(0xFFC9C1B8)
                                },
                                shape = androidx.compose.foundation.shape.CircleShape
                            )
                            .clickable {
                                selectedPage = index
                            }
                    )
                }
            }

            Spacer(
                modifier = Modifier.weight(1f)
            )

            // GET STARTED
            Button(
                onClick = onGetStartedClick,
                modifier = Modifier
                    .width(180.dp)
                    .height(48.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = UbuntuGreen
                )
            ) {
                Text(
                    text = "Get Started",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(
                modifier = Modifier.height(40.dp)
            )
        }

        // LOWER LANDSCAPE AREA
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(260.dp)
                .align(Alignment.BottomCenter)
                .background(
                    color = UbuntuGreen,
                    shape = RoundedCornerShape(
                        topStart = 140.dp,
                        topEnd = 140.dp
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = 25.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "UbuntuHub",
                    color = Color.Transparent,
                    fontSize = 1.sp
                )
            }
        }
    }
}

@Composable
private fun OnboardFeatureButton(
    text: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = {},
        modifier = modifier
            .height(48.dp),
        shape = RoundedCornerShape(9.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = UbuntuOrange
        ),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(
            horizontal = 8.dp
        )
    ) {
        Text(
            text = text,
            color = Color.White,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
    }
}