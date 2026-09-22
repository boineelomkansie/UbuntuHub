package com.ubuntuhub.app.ui.screens.activity

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val UbuntuGreen = Color(0xFF205C3B)
private val UbuntuOrange = Color(0xFFF39A24)
private val UbuntuCream = Color(0xFFFAE8D4)
private val UbuntuWhite = Color(0xFFFFFCF8)
private val UbuntuText = Color(0xFF202124)
private val UbuntuSecondary = Color(0xFF5F6368)
private val UbuntuBorder = Color(0xFFE7D9C9)
private val ActiveBackground = Color(0xFFEAF0D8)
private val ClosedBackground = Color(0xFFEDE9E4)

data class ActivityPost(
    val category: String,
    val postedTime: String,
    val title: String,
    val description: String,
    val location: String,
    val likes: Int,
    val comments: Int,
    val shares: Int,
    val status: String
)

@Composable
fun ActivityScreen(
    onPostClick: (ActivityPost) -> Unit = {},
    onHomeClick: () -> Unit = {},
    onExploreClick: () -> Unit = {},
    onCreatePostClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onFilterClick: () -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf("My Posts") }

    val posts = listOf(
        ActivityPost(
            category = "Requesting Help",
            postedTime = "Posted 2 hours ago",
            title = "Need help with school supplies",
            description = "My sister starts school next month and we're struggling to afford stationery and books. Any help is appreciated.",
            location = "Johannesburg, Gauteng",
            likes = 24,
            comments = 12,
            shares = 5,
            status = "Active"
        ),
        ActivityPost(
            category = "Event",
            postedTime = "Posted 3 days ago",
            title = "Community clean-up this Saturday",
            description = "We're meeting at the park in Protea Glen to clean up our neighbourhood. Everyone is welcome to join!",
            location = "Soweto, Gauteng",
            likes = 18,
            comments = 6,
            shares = 3,
            status = "Active"
        ),
        ActivityPost(
            category = "Community Update",
            postedTime = "Posted 1 week ago",
            title = "Thanks for the food donations!",
            description = "Thank you to everyone who contributed to the food drive. We managed to help over 30 families.",
            location = "",
            likes = 32,
            comments = 8,
            shares = 2,
            status = "Closed"
        ),
        ActivityPost(
            category = "Offering Help",
            postedTime = "Posted 2 weeks ago",
            title = "Offering tutoring for Maths and Science",
            description = "I'm available on weekends to help high school students with Maths and Science. Let me know if you need help!",
            location = "",
            likes = 15,
            comments = 4,
            shares = 1,
            status = "Closed"
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(UbuntuCream)
    ) {

        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            // Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        top = 24.dp,
                        bottom = 12.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Spacer(
                    modifier = Modifier.width(40.dp)
                )

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "My Posts",
                        color = UbuntuGreen,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = "Filter",
                    modifier = Modifier
                        .clickable { onFilterClick() }
                        .padding(8.dp),
                    color = UbuntuGreen,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Tabs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 34.dp,
                        end = 34.dp,
                        top = 8.dp,
                        bottom = 22.dp
                    )
                    .border(
                        width = 1.dp,
                        color = UbuntuBorder,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clip(RoundedCornerShape(16.dp))
            ) {

                ActivityTab(
                    title = "My Posts",
                    selected = selectedTab == "My Posts",
                    modifier = Modifier.weight(1f)
                ) {
                    selectedTab = "My Posts"
                }

                ActivityTab(
                    title = "Saved Posts",
                    selected = selectedTab == "Saved Posts",
                    modifier = Modifier.weight(1f)
                ) {
                    selectedTab = "Saved Posts"
                }
            }

            if (selectedTab == "My Posts") {

                Text(
                    text = "You've created 6 posts",
                    modifier = Modifier.padding(
                        start = 36.dp,
                        end = 36.dp,
                        bottom = 18.dp
                    ),
                    color = UbuntuText,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(
                            start = 34.dp,
                            end = 34.dp
                        ),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {

                    items(posts) { post ->

                        ActivityPostCard(
                            post = post,
                            onClick = {
                                onPostClick(post)
                            }
                        )
                    }

                    item {
                        Spacer(
                            modifier = Modifier.height(110.dp)
                        )
                    }
                }

            } else {

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(
                            start = 34.dp,
                            end = 34.dp
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    Text(
                        text = "Saved Posts",
                        color = UbuntuGreen,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(
                        text = "Posts you've saved will appear here.",
                        color = UbuntuSecondary,
                        fontSize = 16.sp
                    )
                }
            }

            ActivityBottomNavigation(
                onHomeClick = onHomeClick,
                onExploreClick = onExploreClick,
                onCreatePostClick = onCreatePostClick,
                onMessagesClick = onMessagesClick,
                onProfileClick = onProfileClick
            )
        }
    }
}

@Composable
private fun ActivityTab(
    title: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(54.dp)
            .clickable { onClick() }
            .background(
                color = if (selected) UbuntuGreen else Color.Transparent
            ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = title,
            color = if (selected) Color.White else UbuntuSecondary,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun ActivityPostCard(
    post: ActivityPost,
    onClick: () -> Unit
) {

    val statusBackground =
        if (post.status == "Active") {
            ActiveBackground
        } else {
            ClosedBackground
        }

    val categoryColor =
        when (post.category) {
            "Offering Help" -> UbuntuOrange
            "Event" -> UbuntuOrange
            else -> UbuntuGreen
        }

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
            .clickable { onClick() }
            .padding(24.dp)
    ) {

        // Top row
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(
                        color = if (post.category == "Event") {
                            UbuntuOrange
                        } else {
                            UbuntuGreen
                        },
                        shape = RoundedCornerShape(32.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = when (post.category) {
                        "Requesting Help" -> "HELP"
                        "Offering Help" -> "HELP"
                        "Community Update" -> "COMM"
                        "Event" -> "EVENT"
                        else -> "POST"
                    },
                    color = Color.White,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = post.category,
                    color = categoryColor,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = post.postedTime,
                    color = UbuntuSecondary,
                    fontSize = 15.sp
                )
            }

            Box(
                modifier = Modifier
                    .background(
                        color = statusBackground,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(
                        horizontal = 14.dp,
                        vertical = 8.dp
                    )
            ) {

                Text(
                    text = post.status,
                    color = if (post.status == "Active") {
                        UbuntuGreen
                    } else {
                        UbuntuText
                    },
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(
            modifier = Modifier.height(22.dp)
        )

        Text(
            text = post.title,
            color = UbuntuText,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = post.description,
            color = UbuntuText,
            fontSize = 16.sp,
            lineHeight = 24.sp
        )

        if (post.location.isNotEmpty()) {

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = post.location,
                color = UbuntuGreen,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(
            modifier = Modifier.height(18.dp)
        )

        HorizontalDivider(
            color = UbuntuBorder,
            thickness = 1.dp
        )

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Likes ${post.likes}",
                color = UbuntuText,
                fontSize = 15.sp
            )

            Spacer(
                modifier = Modifier.width(20.dp)
            )

            Text(
                text = "Comments ${post.comments}",
                color = UbuntuText,
                fontSize = 15.sp
            )

            Spacer(
                modifier = Modifier.width(20.dp)
            )

            Text(
                text = "Shares ${post.shares}",
                color = UbuntuText,
                fontSize = 15.sp
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "View post",
                color = UbuntuGreen,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun ActivityBottomNavigation(
    onHomeClick: () -> Unit,
    onExploreClick: () -> Unit,
    onCreatePostClick: () -> Unit,
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
                top = 12.dp,
                bottom = 12.dp
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        ActivityNavigationItem(
            label = "Home",
            selected = false,
            onClick = onHomeClick
        )

        ActivityNavigationItem(
            label = "Explore",
            selected = false,
            onClick = onExploreClick
        )

        ActivityNavigationItem(
            label = "Post",
            selected = false,
            onClick = onCreatePostClick
        )

        ActivityNavigationItem(
            label = "Messages",
            selected = false,
            onClick = onMessagesClick
        )

        ActivityNavigationItem(
            label = "Profile",
            selected = true,
            onClick = onProfileClick
        )
    }
}

@Composable
private fun ActivityNavigationItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Text(
        text = label,
        modifier = Modifier
            .clickable { onClick() }
            .padding(
                horizontal = 12.dp,
                vertical = 10.dp
            ),
        color = if (selected) UbuntuOrange else Color.White,
        fontSize = 13.sp,
        fontWeight = if (selected) {
            FontWeight.Bold
        } else {
            FontWeight.Normal
        }
    )
}