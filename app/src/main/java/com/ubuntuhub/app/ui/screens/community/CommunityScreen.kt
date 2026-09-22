package com.ubuntuhub.app.ui.screens.community

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val UbuntuGreen = Color(0xFF205C3B)
private val UbuntuOrange = Color(0xFFF39A24)
private val UbuntuCream = Color(0xFFFAE8D4)
private val UbuntuWhite = Color(0xFFFFFCF8)
private val UbuntuText = Color(0xFF202124)
private val UbuntuSecondary = Color(0xFF6B7280)
private val UbuntuBorder = Color(0xFFE8DCCF)

data class CommunityPost(
    val author: String,
    val category: String,
    val title: String,
    val description: String,
    val time: String
)

@Composable
fun CommunityScreen(
    onBackClick: () -> Unit = {},
    onCreatePostClick: () -> Unit = {},
    onPostClick: (CommunityPost) -> Unit = {}
) {

    val posts = remember {
        mutableStateListOf(
            CommunityPost(
                author = "UbuntuHub Community",
                category = "Community Help",
                title = "Looking for volunteers",
                description = "We are looking for community members who can assist with a local clean-up initiative this weekend.",
                time = "Today"
            ),
            CommunityPost(
                author = "Community Member",
                category = "Help Offered",
                title = "Free computer assistance",
                description = "I can help community members with basic computer skills, CVs and online applications.",
                time = "Yesterday"
            ),
            CommunityPost(
                author = "UbuntuHub Community",
                category = "Notice",
                title = "Community meeting",
                description = "A community meeting will be held this Saturday. Everyone is welcome to attend and participate.",
                time = "2 days ago"
            )
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(UbuntuCream)
    ) {

        // =============================================================
        // HEADER
        // =============================================================

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
                text = "Back",
                modifier = Modifier
                    .clickable {
                        onBackClick()
                    }
                    .padding(
                        vertical = 8.dp
                    ),
                color = UbuntuGreen,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Community",
                    color = UbuntuGreen,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.size(55.dp)
            )
        }

        // =============================================================
        // INTRODUCTION
        // =============================================================

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 24.dp,
                    end = 24.dp
                )
        ) {

            Text(
                text = "Connect with your community",
                color = UbuntuText,
                fontSize = 21.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            Text(
                text = "Share opportunities, ask for help, offer assistance and stay connected with people in your community.",
                color = UbuntuSecondary,
                fontSize = 15.sp
            )

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Button(
                onClick = onCreatePostClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = UbuntuGreen
                )
            ) {
                Text(
                    text = "Create Community Post",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(18.dp)
            )

            Text(
                text = "Community Posts",
                color = UbuntuGreen,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )
        }

        // =============================================================
        // COMMUNITY POSTS
        // =============================================================

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 24.dp,
                    end = 24.dp
                ),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {

            items(posts) { post ->

                CommunityPostCard(
                    post = post,
                    onClick = {
                        onPostClick(post)
                    }
                )
            }

            item {
                Spacer(
                    modifier = Modifier.height(20.dp)
                )
            }
        }
    }
}

// =====================================================================
// COMMUNITY POST CARD
// =====================================================================

@Composable
private fun CommunityPostCard(
    post: CommunityPost,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = UbuntuWhite,
                shape = RoundedCornerShape(18.dp)
            )
            .border(
                width = 1.dp,
                color = UbuntuBorder,
                shape = RoundedCornerShape(18.dp)
            )
            .clickable {
                onClick()
            }
            .padding(18.dp)
    ) {

        // -------------------------------------------------------------
        // AUTHOR AND TIME
        // -------------------------------------------------------------

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = post.author,
                modifier = Modifier.weight(1f),
                color = UbuntuGreen,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = post.time,
                color = UbuntuSecondary,
                fontSize = 13.sp
            )
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        // -------------------------------------------------------------
        // CATEGORY
        // -------------------------------------------------------------

        Text(
            text = post.category,
            color = UbuntuOrange,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        // -------------------------------------------------------------
        // TITLE
        // -------------------------------------------------------------

        Text(
            text = post.title,
            color = UbuntuText,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        // -------------------------------------------------------------
        // DESCRIPTION
        // -------------------------------------------------------------

        Text(
            text = post.description,
            color = UbuntuSecondary,
            fontSize = 15.sp,
            lineHeight = 21.sp
        )

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        HorizontalDivider(
            color = UbuntuBorder,
            thickness = 1.dp
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        Text(
            text = "View post",
            color = UbuntuGreen,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}