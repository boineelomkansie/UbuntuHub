package com.ubuntuhub.app.ui.screens.postdetails

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ubuntuhub.app.ui.screens.community.CommunityPost

private val UbuntuGreen = Color(0xFF205C3B)
private val UbuntuOrange = Color(0xFFF39A24)
private val UbuntuCream = Color(0xFFFAE8D4)
private val UbuntuWhite = Color(0xFFFFFCF8)
private val UbuntuText = Color(0xFF202124)
private val UbuntuSecondary = Color(0xFF6B7280)
private val UbuntuBorder = Color(0xFFE8DCCF)

data class PostComment(
    val author: String,
    val time: String,
    val text: String,
    val likes: Int
)

@Composable
fun PostDetailsScreen(
    post: CommunityPost? = null,
    onBackClick: () -> Unit = {}
) {

    val displayedPost = post ?: CommunityPost(
        author = "Thabo M.",
        category = "Requesting Help",
        title = "Need help with school supplies",
        description = "My sister starts school next month and we're struggling to afford stationery and books. Any help is appreciated.",
        time = "2 hours ago"
    )

    var likeCount by remember {
        mutableStateOf(24)
    }

    var commentText by remember {
        mutableStateOf("")
    }

    var isLiked by remember {
        mutableStateOf(false)
    }

    var isBookmarked by remember {
        mutableStateOf(false)
    }

    var commentSort by remember {
        mutableStateOf("Most Recent")
    }

    val comments = remember {
        mutableStateListOf(
            PostComment(
                author = "Lindiwe K.",
                time = "1 hour ago",
                text = "I have some extra notebooks and pens. I can drop them off this weekend.",
                likes = 6
            ),
            PostComment(
                author = "Thabo M.",
                time = "45 minutes ago",
                text = "Thank you so much, Lindiwe! That would mean a lot.",
                likes = 2
            ),
            PostComment(
                author = "Sipho D.",
                time = "2 hours ago",
                text = "I'd like to contribute towards buying the books.",
                likes = 4
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
                    .padding(vertical = 8.dp),
                color = UbuntuGreen,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Box(
                modifier = Modifier.weight(1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Post Details",
                    color = UbuntuGreen,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Text(
                text = "More",
                color = UbuntuGreen,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // =============================================================
        // POST CONTENT
        // =============================================================

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(
                start = 24.dp,
                end = 24.dp,
                bottom = 24.dp
            ),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {

            // ---------------------------------------------------------
            // AUTHOR
            // ---------------------------------------------------------

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    ProfilePlaceholder(
                        initial = displayedPost.author
                            .firstOrNull()
                            ?.uppercase()
                            ?: "U"
                    )

                    Spacer(
                        modifier = Modifier.width(14.dp)
                    )

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {

                        Text(
                            text = displayedPost.author,
                            color = UbuntuText,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(3.dp)
                        )

                        Text(
                            text = "${displayedPost.time} • Johannesburg",
                            color = UbuntuSecondary,
                            fontSize = 14.sp
                        )
                    }

                    CategoryLabel(
                        text = displayedPost.category
                    )
                }

                Spacer(
                    modifier = Modifier.height(18.dp)
                )
            }

            // ---------------------------------------------------------
            // TITLE
            // ---------------------------------------------------------

            item {

                Text(
                    text = displayedPost.title,
                    color = UbuntuGreen,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = displayedPost.description,
                    color = UbuntuText,
                    fontSize = 17.sp,
                    lineHeight = 24.sp
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )
            }

            // ---------------------------------------------------------
            // LOCATION
            // ---------------------------------------------------------

            item {

                Text(
                    text = "Johannesburg, Gauteng",
                    color = UbuntuSecondary,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                HorizontalDivider(
                    color = UbuntuBorder,
                    thickness = 1.dp
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )
            }

            // ---------------------------------------------------------
            // IMAGE PLACEHOLDER
            // ---------------------------------------------------------

            item {

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(230.dp)
                        .background(
                            color = Color(0xFFE5D7C5),
                            shape = RoundedCornerShape(18.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = UbuntuBorder,
                            shape = RoundedCornerShape(18.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "Post Image",
                            color = UbuntuGreen,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(6.dp)
                        )

                        Text(
                            text = "Image will be loaded from the online database",
                            color = UbuntuSecondary,
                            fontSize = 13.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(14.dp)
                )
            }

            // ---------------------------------------------------------
            // POST ACTIONS
            // ---------------------------------------------------------

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    ActionButton(
                        text = if (isLiked) {
                            "Liked $likeCount"
                        } else {
                            "Like $likeCount"
                        },
                        selected = isLiked,
                        onClick = {

                            isLiked = !isLiked

                            likeCount = if (isLiked) {
                                likeCount + 1
                            } else {
                                likeCount - 1
                            }
                        }
                    )

                    Spacer(
                        modifier = Modifier.width(18.dp)
                    )

                    ActionButton(
                        text = "Comments ${comments.size}",
                        selected = false,
                        onClick = {}
                    )

                    Spacer(
                        modifier = Modifier.width(18.dp)
                    )

                    ActionButton(
                        text = "Share 5",
                        selected = false,
                        onClick = {}
                    )

                    Spacer(
                        modifier = Modifier.weight(1f)
                    )

                    ActionButton(
                        text = if (isBookmarked) {
                            "Saved"
                        } else {
                            "Save"
                        },
                        selected = isBookmarked,
                        onClick = {
                            isBookmarked = !isBookmarked
                        }
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
                    modifier = Modifier.height(18.dp)
                )
            }

            // ---------------------------------------------------------
            // ABOUT THIS POST
            // ---------------------------------------------------------

            item {

                Text(
                    text = "About this post",
                    color = UbuntuGreen,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                InformationRow(
                    title = "Category",
                    value = displayedPost.category
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                InformationRow(
                    title = "Posted",
                    value = "Today, 8:15 AM"
                )

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                HorizontalDivider(
                    color = UbuntuBorder,
                    thickness = 1.dp
                )

                Spacer(
                    modifier = Modifier.height(18.dp)
                )
            }

            // ---------------------------------------------------------
            // COMMENTS HEADER
            // ---------------------------------------------------------

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Comments (${comments.size})",
                        modifier = Modifier.weight(1f),
                        color = UbuntuGreen,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = commentSort,
                        modifier = Modifier
                            .clickable {
                                commentSort =
                                    if (commentSort == "Most Recent") {
                                        "Oldest"
                                    } else {
                                        "Most Recent"
                                    }
                            }
                            .padding(8.dp),
                        color = UbuntuText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Spacer(
                    modifier = Modifier.height(14.dp)
                )
            }

            // ---------------------------------------------------------
            // COMMENTS
            // ---------------------------------------------------------

            items(comments) { comment ->

                CommentItem(
                    comment = comment
                )

                Spacer(
                    modifier = Modifier.height(18.dp)
                )
            }

            // ---------------------------------------------------------
            // COMMENT INPUT
            // ---------------------------------------------------------

            item {

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    OutlinedTextField(
                        value = commentText,
                        onValueChange = {
                            commentText = it
                        },
                        modifier = Modifier.weight(1f),
                        placeholder = {
                            Text(
                                text = "Write a comment...",
                                color = UbuntuSecondary
                            )
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
                        )
                    )

                    Spacer(
                        modifier = Modifier.width(10.dp)
                    )

                    Button(
                        onClick = {

                            if (commentText.isNotBlank()) {

                                comments.add(
                                    PostComment(
                                        author = "You",
                                        time = "Just now",
                                        text = commentText.trim(),
                                        likes = 0
                                    )
                                )

                                commentText = ""
                            }
                        },
                        modifier = Modifier
                            .width(90.dp)
                            .height(52.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = UbuntuGreen
                        )
                    ) {

                        Text(
                            text = "Send",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(
                    modifier = Modifier.height(24.dp)
                )
            }

            // ---------------------------------------------------------
            // BOTTOM NAVIGATION
            // ---------------------------------------------------------

            item {

                BottomNavigationBar()
            }
        }
    }
}

// =====================================================================
// PROFILE PLACEHOLDER
// =====================================================================

@Composable
private fun ProfilePlaceholder(
    initial: String
) {

    Box(
        modifier = Modifier
            .size(58.dp)
            .background(
                color = Color(0xFFE1E4C8),
                shape = RoundedCornerShape(50.dp)
            ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = initial,
            color = UbuntuGreen,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// =====================================================================
// CATEGORY LABEL
// =====================================================================

@Composable
private fun CategoryLabel(
    text: String
) {

    Box(
        modifier = Modifier
            .background(
                color = Color(0xFFE5E5CC),
                shape = RoundedCornerShape(18.dp)
            )
            .padding(
                horizontal = 14.dp,
                vertical = 9.dp
            )
    ) {

        Text(
            text = text,
            color = UbuntuText,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

// =====================================================================
// ACTION BUTTON
// =====================================================================

@Composable
private fun ActionButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Text(
        text = text,
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(
                vertical = 8.dp
            ),
        color = if (selected) {
            UbuntuGreen
        } else {
            UbuntuText
        },
        fontSize = 13.sp,
        fontWeight = if (selected) {
            FontWeight.Bold
        } else {
            FontWeight.Normal
        }
    )
}

// =====================================================================
// INFORMATION ROW
// =====================================================================

@Composable
private fun InformationRow(
    title: String,
    value: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = Color(0xFFE1E4C8),
                    shape = RoundedCornerShape(50.dp)
                )
        )

        Spacer(
            modifier = Modifier.width(14.dp)
        )

        Column {

            Text(
                text = title,
                color = UbuntuText,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(2.dp)
            )

            Text(
                text = value,
                color = UbuntuSecondary,
                fontSize = 15.sp
            )
        }
    }
}

// =====================================================================
// COMMENT ITEM
// =====================================================================

@Composable
private fun CommentItem(
    comment: PostComment
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {

        ProfilePlaceholder(
            initial = comment.author
                .firstOrNull()
                ?.uppercase()
                ?: "U"
        )

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = comment.author,
                    modifier = Modifier.weight(1f),
                    color = UbuntuText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = comment.time,
                    color = UbuntuSecondary,
                    fontSize = 12.sp
                )
            }

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = comment.text,
                color = UbuntuText,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )

            Spacer(
                modifier = Modifier.height(7.dp)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {

                Text(
                    text = "Like ${comment.likes}",
                    color = UbuntuSecondary,
                    fontSize = 12.sp
                )

                Text(
                    text = "Reply",
                    color = UbuntuGreen,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

// =====================================================================
// BOTTOM NAVIGATION
// =====================================================================

@Composable
private fun BottomNavigationBar() {

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
                vertical = 16.dp
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        BottomNavigationItem(
            label = "Home",
            selected = false
        )

        BottomNavigationItem(
            label = "Explore",
            selected = false
        )

        BottomNavigationItem(
            label = "Post",
            selected = true
        )

        BottomNavigationItem(
            label = "Messages",
            selected = false
        )

        BottomNavigationItem(
            label = "Profile",
            selected = false
        )
    }
}

// =====================================================================
// BOTTOM NAVIGATION ITEM
// =====================================================================

@Composable
private fun BottomNavigationItem(
    label: String,
    selected: Boolean
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