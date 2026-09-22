package com.ubuntuhub.app.ui.screens.offline

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
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
private val UbuntuLightGreen = Color(0xFFEAF1DF)
private val UbuntuLightOrange = Color(0xFFFFE6BF)

@Composable
fun OfflineScreen(
    onBackClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onExploreClick: () -> Unit = {},
    onPostClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    var syncMessage by remember {
        mutableStateOf("Last attempted: Just now")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(UbuntuCream)
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            // HEADER
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 10.dp,
                        bottom = 8.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = onBackClick,
                    modifier = Modifier.width(70.dp)
                ) {
                    Text(
                        text = "Back",
                        color = UbuntuGreen,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Offline / Sync Status",
                        color = UbuntuGreen,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(
                    modifier = Modifier.width(70.dp)
                )
            }

            // MAIN CONTENT
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        bottom = 20.dp
                    )
            ) {

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                // OFFLINE STATUS CARD
                OfflineStatusCard()

                Spacer(
                    modifier = Modifier.height(32.dp)
                )

                SectionTitle(
                    text = "SYNC OVERVIEW"
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                SyncOverviewCard()

                Spacer(
                    modifier = Modifier.height(30.dp)
                )

                SectionTitle(
                    text = "PENDING ITEMS"
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                PendingItemsCard()

                Spacer(
                    modifier = Modifier.height(30.dp)
                )

                // SYNC BUTTON
                Button(
                    onClick = {
                        syncMessage = "Last attempted: Just now"
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(64.dp),
                    shape = RoundedCornerShape(32.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = UbuntuGreen
                    )
                ) {
                    Text(
                        text = "Sync Now",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = syncMessage,
                    modifier = Modifier.fillMaxWidth(),
                    color = UbuntuSecondary,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                // OFFLINE INFORMATION CARD
                OfflineInformationCard()

                Spacer(
                    modifier = Modifier.height(20.dp)
                )
            }

            // BOTTOM NAVIGATION
            BottomNavigationBar(
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
private fun OfflineStatusCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = UbuntuWhite,
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 1.dp,
                color = UbuntuBorder,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(28.dp)
    ) {
        Text(
            text = "You're Offline",
            color = UbuntuGreen,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "No internet connection",
            color = UbuntuText,
            fontSize = 20.sp
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(
            text = "Some features may be limited",
            color = UbuntuSecondary,
            fontSize = 17.sp
        )
    }
}

@Composable
private fun SectionTitle(
    text: String
) {
    Text(
        text = text,
        color = UbuntuSecondary,
        fontSize = 21.sp,
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun SyncOverviewCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = UbuntuWhite,
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 1.dp,
                color = UbuntuBorder,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(
                horizontal = 20.dp,
                vertical = 8.dp
            )
    ) {

        SyncOverviewRow(
            title = "Last successful sync",
            subtitle = "Yesterday, 8:45 PM",
            status = "Completed",
            statusColor = UbuntuLightGreen,
            statusTextColor = UbuntuGreen
        )

        HorizontalDivider(
            color = UbuntuBorder
        )

        SyncOverviewRow(
            title = "Changes to sync",
            subtitle = "3 posts, 2 messages",
            status = "Pending",
            statusColor = UbuntuLightOrange,
            statusTextColor = UbuntuOrange
        )

        HorizontalDivider(
            color = UbuntuBorder
        )

        SyncOverviewRow(
            title = "Data available offline",
            subtitle = "Up to date",
            status = "Up to date",
            statusColor = UbuntuLightGreen,
            statusTextColor = UbuntuGreen
        )
    }
}

@Composable
private fun SyncOverviewRow(
    title: String,
    subtitle: String,
    status: String,
    statusColor: Color,
    statusTextColor: Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 20.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = UbuntuText,
                fontSize = 19.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = subtitle,
                color = UbuntuSecondary,
                fontSize = 16.sp
            )
        }

        Box(
            modifier = Modifier
                .background(
                    color = statusColor,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(
                    horizontal = 16.dp,
                    vertical = 9.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = status,
                color = statusTextColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun PendingItemsCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = UbuntuWhite,
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 1.dp,
                color = UbuntuBorder,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(
                horizontal = 20.dp,
                vertical = 4.dp
            )
    ) {

        PendingItemRow(
            title = "Posts",
            subtitle = "3 pending"
        )

        HorizontalDivider(
            color = UbuntuBorder
        )

        PendingItemRow(
            title = "Messages",
            subtitle = "2 pending"
        )

        HorizontalDivider(
            color = UbuntuBorder
        )

        PendingItemRow(
            title = "Comments",
            subtitle = "1 pending"
        )
    }
}

@Composable
private fun PendingItemRow(
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(96.dp)
            .padding(
                horizontal = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = title,
                color = UbuntuText,
                fontSize = 19.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = subtitle,
                color = UbuntuSecondary,
                fontSize = 16.sp
            )
        }

        Text(
            text = "View",
            color = UbuntuGreen,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun OfflineInformationCard() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = UbuntuWhite,
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 1.dp,
                color = UbuntuBorder,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(24.dp)
    ) {
        Text(
            text = "You're currently offline",
            color = UbuntuText,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Your activity will be saved and synced automatically when you're back online.",
            color = UbuntuSecondary,
            fontSize = 17.sp,
            lineHeight = 25.sp
        )
    }
}

@Composable
private fun BottomNavigationBar(
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
                start = 8.dp,
                end = 8.dp,
                top = 10.dp,
                bottom = 10.dp
            )
            .windowInsetsPadding(
                WindowInsets.navigationBars
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        BottomNavigationItem(
            label = "Home",
            selected = false,
            onClick = onHomeClick
        )

        BottomNavigationItem(
            label = "Explore",
            selected = false,
            onClick = onExploreClick
        )

        BottomNavigationItem(
            label = "Post",
            selected = false,
            onClick = onPostClick
        )

        BottomNavigationItem(
            label = "Messages",
            selected = false,
            onClick = onMessagesClick
        )

        BottomNavigationItem(
            label = "Profile",
            selected = true,
            onClick = onProfileClick
        )
    }
}

@Composable
private fun BottomNavigationItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val contentColor =
        if (selected) UbuntuOrange else Color.White

    Column(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 10.dp,
                vertical = 8.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            color = contentColor,
            fontSize = 13.sp,
            fontWeight = if (selected) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            }
        )
    }
}