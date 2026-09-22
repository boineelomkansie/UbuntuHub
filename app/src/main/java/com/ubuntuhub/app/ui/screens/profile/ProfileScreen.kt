package com.ubuntuhub.app.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

private val UbuntuGreen = Color(0xFF205C3B)
private val UbuntuOrange = Color(0xFFF39A24)
private val UbuntuCream = Color(0xFFFAE8D4)
private val UbuntuWhite = Color(0xFFFFFCF8)
private val UbuntuText = Color(0xFF202124)
private val UbuntuSecondary = Color(0xFF68737D)
private val UbuntuBorder = Color(0xFFE8DCCF)
private val UbuntuAvatar = Color(0xFFE8ECD8)

@Composable
fun ProfileScreen(
    onSettingsClick: () -> Unit = {},
    onMyPostsClick: () -> Unit = {},
    onSavedPostsClick: () -> Unit = {},
    onPeopleHelpedClick: () -> Unit = {},
    onBadgesClick: () -> Unit = {},
    onActivitySummaryClick: () -> Unit = {},
    onNotificationsClick: () -> Unit = {},
    onHelpSupportClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onExploreClick: () -> Unit = {},
    onPostClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {}
) {

    val currentUser = remember {
        FirebaseAuth.getInstance().currentUser
    }

    val userName = currentUser?.displayName
        ?.trim()
        ?.takeIf { it.isNotEmpty() }
        ?: currentUser?.email
            ?.substringBefore("@")
            ?.takeIf { it.isNotEmpty() }
        ?: "User"

    val userInitial = userName
        .trim()
        .firstOrNull()
        ?.uppercaseChar()
        ?.toString()
        ?: "U"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(UbuntuCream)
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
                        top = 18.dp,
                        bottom = 12.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Profile",
                    modifier = Modifier.weight(1f),
                    color = UbuntuGreen,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "Settings",
                    modifier = Modifier
                        .clickable { onSettingsClick() }
                        .padding(
                            horizontal = 4.dp,
                            vertical = 8.dp
                        ),
                    color = UbuntuGreen,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // SCROLLABLE CONTENT
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(
                        start = 20.dp,
                        end = 20.dp,
                        bottom = 20.dp
                    )
            ) {

                Spacer(modifier = Modifier.height(8.dp))

                // PROFILE SUMMARY
                ProfileSummaryCard(
                    userName = userName,
                    userInitial = userInitial
                )

                Spacer(modifier = Modifier.height(16.dp))

                // STATISTICS
                StatisticsCard()

                Spacer(modifier = Modifier.height(16.dp))

                // COMMUNITY ACTIVITY
                SectionCard(
                    title = "Community Activity"
                ) {

                    ProfileOptionRow(
                        title = "My Posts",
                        description = "View your posts and activity",
                        actionText = "View",
                        onClick = onMyPostsClick
                    )

                    ProfileDivider()

                    ProfileOptionRow(
                        title = "Saved Posts",
                        description = "Posts you've saved",
                        actionText = "View",
                        onClick = onSavedPostsClick
                    )

                    ProfileDivider()

                    ProfileOptionRow(
                        title = "People Helped",
                        description = "See the people you've helped",
                        actionText = "View",
                        onClick = onPeopleHelpedClick
                    )

                    ProfileDivider()

                    ProfileOptionRow(
                        title = "Badges",
                        description = "Your achievements",
                        actionText = "View",
                        onClick = onBadgesClick
                    )

                    ProfileDivider()

                    ProfileOptionRow(
                        title = "Activity Summary",
                        description = "Your community impact",
                        actionText = "View",
                        onClick = onActivitySummaryClick
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // PROFILE INFORMATION
                SectionCard(
                    title = "About You"
                ) {

                    InformationRow(
                        title = "Location",
                        value = "Johannesburg, Gauteng"
                    )

                    ProfileDivider()

                    InformationRow(
                        title = "Joined",
                        value = "March 2025"
                    )

                    ProfileDivider()

                    InformationRow(
                        title = "Interests",
                        value = "Education • Environment • Community Support"
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // ACCOUNT
                SectionCard(
                    title = "Account"
                ) {

                    ProfileSimpleRow(
                        title = "Notifications",
                        actionText = "Open",
                        onClick = onNotificationsClick
                    )

                    ProfileDivider()

                    ProfileSimpleRow(
                        title = "Help & Support",
                        actionText = "Open",
                        onClick = onHelpSupportClick
                    )

                    ProfileDivider()

                    ProfileSimpleRow(
                        title = "Log Out",
                        actionText = "Log out",
                        onClick = onLogoutClick
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                Text(
                    text = "UbuntuHub",
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = UbuntuOrange,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            // BOTTOM NAVIGATION
            BottomNavigationBar(
                onHomeClick = onHomeClick,
                onExploreClick = onExploreClick,
                onPostClick = onPostClick,
                onMessagesClick = onMessagesClick
            )
        }
    }
}

@Composable
private fun ProfileSummaryCard(
    userName: String,
    userInitial: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = UbuntuWhite,
                shape = RoundedCornerShape(24.dp)
            )
            .border(
                width = 1.dp,
                color = UbuntuBorder,
                shape = RoundedCornerShape(24.dp)
            )
            .padding(22.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(92.dp)
                .background(
                    color = UbuntuAvatar,
                    shape = CircleShape
                )
                .border(
                    width = 3.dp,
                    color = UbuntuGreen,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = userInitial,
                color = UbuntuGreen,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.width(18.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = userName,
                color = UbuntuText,
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Johannesburg, Gauteng",
                color = UbuntuSecondary,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(9.dp))

            Text(
                text = "Building a stronger community, one act of kindness at a time.",
                color = UbuntuText,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }
    }
}

@Composable
private fun StatisticsCard() {
    Row(
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
                vertical = 18.dp,
                horizontal = 8.dp
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        StatisticItem(
            value = "18",
            label = "Posts"
        )

        StatisticDivider()

        StatisticItem(
            value = "124",
            label = "Followers"
        )

        StatisticDivider()

        StatisticItem(
            value = "96",
            label = "Following"
        )

        StatisticDivider()

        StatisticItem(
            value = "45",
            label = "Helped"
        )
    }
}

@Composable
private fun StatisticItem(
    value: String,
    label: String
) {
    Column(
        modifier = Modifier.width(72.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = value,
            color = UbuntuText,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = label,
            color = UbuntuSecondary,
            fontSize = 12.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun StatisticDivider() {
    Box(
        modifier = Modifier
            .width(1.dp)
            .height(38.dp)
            .background(UbuntuBorder)
    )
}

@Composable
private fun SectionCard(
    title: String,
    content: @Composable ColumnScope.() -> Unit
) {
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
                horizontal = 18.dp,
                vertical = 10.dp
            )
    ) {

        Text(
            text = title,
            modifier = Modifier.padding(
                top = 8.dp,
                bottom = 6.dp
            ),
            color = UbuntuGreen,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        content()
    }
}

@Composable
private fun ProfileOptionRow(
    title: String,
    description: String,
    actionText: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = title,
                color = UbuntuText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = description,
                color = UbuntuSecondary,
                fontSize = 13.sp
            )
        }

        Text(
            text = actionText,
            color = UbuntuGreen,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ProfileSimpleRow(
    title: String,
    actionText: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            color = UbuntuText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = actionText,
            color = UbuntuGreen,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun InformationRow(
    title: String,
    value: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp)
    ) {

        Text(
            text = title,
            color = UbuntuGreen,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = value,
            color = UbuntuSecondary,
            fontSize = 14.sp,
            lineHeight = 19.sp
        )
    }
}

@Composable
private fun ProfileDivider() {
    HorizontalDivider(
        color = UbuntuBorder,
        thickness = 1.dp
    )
}

@Composable
private fun BottomNavigationBar(
    onHomeClick: () -> Unit,
    onExploreClick: () -> Unit,
    onPostClick: () -> Unit,
    onMessagesClick: () -> Unit
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
                horizontal = 8.dp,
                vertical = 10.dp
            )
            .navigationBarsPadding(),
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
            onClick = {}
        )
    }
}

@Composable
private fun BottomNavigationItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = label,
        modifier = Modifier
            .clickable { onClick() }
            .padding(
                horizontal = 9.dp,
                vertical = 8.dp
            ),
        color = if (selected) {
            UbuntuOrange
        } else {
            Color.White
        },
        fontSize = 14.sp,
        fontWeight = if (selected) {
            FontWeight.Bold
        } else {
            FontWeight.Normal
        }
    )
}