package com.ubuntuhub.app.ui.screens.notifications

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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val UbuntuGreen = Color(0xFF205C3B)
private val UbuntuOrange = Color(0xFFF39A24)
private val UbuntuCream = Color(0xFFFAE8D4)
private val UbuntuWhite = Color(0xFFFFFCF8)
private val UbuntuText = Color(0xFF202124)
private val UbuntuSecondary = Color(0xFF6B7280)
private val UbuntuBorder = Color(0xFFE8DCCF)

@Composable
fun NotificationsScreen(
    onBackClick: () -> Unit = {},
    onSettingsClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onExploreClick: () -> Unit = {},
    onPostClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    var selectedTab by remember {
        mutableStateOf("All")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(UbuntuCream)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            /*
             * Header
             */
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 20.dp,
                        bottom = 12.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "‹",
                    color = UbuntuGreen,
                    fontSize = 42.sp,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier
                        .size(44.dp)
                        .clickable {
                            onBackClick()
                        }
                )

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Notifications",
                        color = UbuntuGreen,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = "⚙",
                    color = UbuntuGreen,
                    fontSize = 28.sp,
                    modifier = Modifier
                        .size(44.dp)
                        .clickable {
                            onSettingsClick()
                        },
                    textAlign = TextAlign.Center
                )
            }

            /*
             * Notification tabs
             */
            NotificationTabs(
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                }
            )

            /*
             * Notification content
             */
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(
                        start = 38.dp,
                        end = 24.dp,
                        top = 24.dp,
                        bottom = 20.dp
                    )
            ) {

                Text(
                    text = "New",
                    color = UbuntuGreen,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(14.dp))

                NotificationItem(
                    name = "Lindiwe K.",
                    message = "liked your post",
                    secondaryText = "Need help with school supplies",
                    time = "2m ago",
                    badge = "♥",
                    badgeColor = Color(0xFFE84D65)
                )

                NotificationDivider()

                NotificationItem(
                    name = "Thabo M.",
                    message = "commented on your post",
                    secondaryText = "I can help with some of the supplies.",
                    time = "5m ago",
                    badge = "●",
                    badgeColor = Color(0xFF1976D2)
                )

                NotificationDivider()

                NotificationItem(
                    name = "Community Care",
                    message = "marked your post as Active",
                    secondaryText = "",
                    time = "10m ago",
                    badge = "✓",
                    badgeColor = Color(0xFF16A05D)
                )

                NotificationDivider()

                NotificationItem(
                    name = "Nandi P.",
                    message = "started following you",
                    secondaryText = "",
                    time = "15m ago",
                    badge = "+",
                    badgeColor = Color(0xFF16A05D)
                )

                Spacer(modifier = Modifier.height(22.dp))

                Text(
                    text = "Earlier",
                    color = UbuntuGreen,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(14.dp))

                NotificationItem(
                    name = "Event reminder: Community clean-up",
                    message = "",
                    secondaryText = "This Saturday at 8:00 AM",
                    time = "1h ago",
                    badge = "▣",
                    badgeColor = UbuntuOrange
                )

                NotificationDivider()

                NotificationItem(
                    name = "Sikho D.",
                    message = "shared your post",
                    secondaryText = "Check out this post that might help others in our community.",
                    time = "3h ago",
                    badge = "↗",
                    badgeColor = UbuntuGreen
                )

                NotificationDivider()

                NotificationItem(
                    name = "Karabo S.",
                    message = "liked your post",
                    secondaryText = "Offering tutoring for Maths and Science",
                    time = "2 days ago",
                    badge = "♥",
                    badgeColor = Color(0xFFE84D65)
                )

                NotificationDivider()

                NotificationItem(
                    name = "Weekly summary is ready",
                    message = "",
                    secondaryText = "See how your community made an impact this week.",
                    time = "2 days ago",
                    badge = "●",
                    badgeColor = UbuntuGreen
                )

                Spacer(modifier = Modifier.height(20.dp))
            }

            /*
             * Bottom navigation
             */
            NotificationBottomNavigation(
                onHomeClick = onHomeClick,
                onExploreClick = onExploreClick,
                onPostClick = onPostClick,
                onMessagesClick = onMessagesClick,
                onProfileClick = onProfileClick
            )
        }
    }
}

@Composable
private fun NotificationTabs(
    selectedTab: String,
    onTabSelected: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 34.dp,
                end = 34.dp
            )
            .height(70.dp)
            .border(
                width = 1.dp,
                color = UbuntuBorder,
                shape = RoundedCornerShape(18.dp)
            )
            .clip(RoundedCornerShape(18.dp))
    ) {

        listOf(
            "All",
            "Mentions",
            "Messages"
        ).forEach { tab ->

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .background(
                        if (selectedTab == tab) {
                            UbuntuGreen
                        } else {
                            Color.Transparent
                        }
                    )
                    .clickable {
                        onTabSelected(tab)
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = tab,
                    color = if (selectedTab == tab) {
                        Color.White
                    } else {
                        UbuntuText
                    },
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun NotificationItem(
    name: String,
    message: String,
    secondaryText: String,
    time: String,
    badge: String,
    badgeColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.Top
    ) {

        Box(
            modifier = Modifier
                .size(88.dp),
            contentAlignment = Alignment.Center
        ) {

            Box(
                modifier = Modifier
                    .size(78.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE9E8CF)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        modifier = Modifier
                            .size(25.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF597848))
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Box(
                        modifier = Modifier
                            .width(42.dp)
                            .height(25.dp)
                            .clip(
                                RoundedCornerShape(
                                    topStart = 20.dp,
                                    topEnd = 20.dp
                                )
                            )
                            .background(Color(0xFF597848))
                    )
                }
            }

            Box(
                modifier = Modifier
                    .size(34.dp)
                    .align(Alignment.BottomEnd)
                    .clip(CircleShape)
                    .background(badgeColor),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = badge,
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.width(20.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    top = 4.dp,
                    end = 4.dp
                )
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {

                Text(
                    text = name,
                    color = UbuntuText,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "›",
                    color = UbuntuGreen,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Light
                )
            }

            if (message.isNotEmpty()) {
                Text(
                    text = message,
                    color = UbuntuText,
                    fontSize = 17.sp,
                    modifier = Modifier.padding(top = 2.dp)
                )
            }

            if (secondaryText.isNotEmpty()) {
                Text(
                    text = secondaryText,
                    color = UbuntuText,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            Text(
                text = time,
                color = UbuntuSecondary,
                fontSize = 15.sp,
                modifier = Modifier.padding(top = 6.dp)
            )
        }
    }
}

@Composable
private fun NotificationDivider() {
    Spacer(modifier = Modifier.height(6.dp))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(UbuntuBorder)
    )

    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun NotificationBottomNavigation(
    onHomeClick: () -> Unit,
    onExploreClick: () -> Unit,
    onPostClick: () -> Unit,
    onMessagesClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = UbuntuGreen,
                shape = RoundedCornerShape(
                    topStart = 28.dp,
                    topEnd = 28.dp
                )
            )
            .padding(
                horizontal = 12.dp,
                vertical = 12.dp
            )
            .navigationBarsPadding(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        NotificationNavigationItem(
            label = "Home",
            selected = false,
            onClick = onHomeClick
        )

        NotificationNavigationItem(
            label = "Explore",
            selected = false,
            onClick = onExploreClick
        )

        NotificationNavigationItem(
            label = "Post",
            selected = false,
            onClick = onPostClick
        )

        NotificationNavigationItem(
            label = "Messages",
            selected = false,
            onClick = onMessagesClick
        )

        NotificationNavigationItem(
            label = "Profile",
            selected = true,
            onClick = onProfileClick
        )
    }
}

@Composable
private fun NotificationNavigationItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 10.dp,
                vertical = 4.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            color = if (selected) {
                UbuntuOrange
            } else {
                Color.White
            },
            fontSize = 13.sp,
            fontWeight = if (selected) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            }
        )
    }
}