package com.ubuntuhub.app.ui.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.ubuntuhub.app.data.PostDto
import com.ubuntuhub.app.data.RetrofitClient

private val UbuntuGreen = Color(0xFF164D35)
private val UbuntuGreenDark = Color(0xFF0E402B)
private val UbuntuOrange = Color(0xFFE89518)
private val UbuntuCream = Color(0xFFFAF1E6)
private val UbuntuBorder = Color(0xFFE1D4C5)
private val UbuntuText = Color(0xFF18201D)
private val UbuntuSecondary = Color(0xFF59615D)
private val UbuntuSoftGreen = Color(0xFFE7E9D9)
private val UbuntuSoftOrange = Color(0xFFFFEBCB)

data class HomeFeedPost(
    val author: String,
    val time: String,
    val location: String,
    val category: String,
    val title: String,
    val description: String,
    val likes: String,
    val comments: String,
    val categoryIsOrange: Boolean = false
)

@Composable
fun HomeScreen(
    onSettingsClick: () -> Unit = {},
    onCommunityClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onExploreClick: () -> Unit = {},
    onPostClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {

    val currentUser = remember {
        FirebaseAuth.getInstance().currentUser
    }

    val userName = remember(currentUser) {
        currentUser?.displayName
            ?.trim()
            ?.takeIf { it.isNotEmpty() }
            ?: currentUser?.email
                ?.substringBefore("@")
                ?.takeIf { it.isNotEmpty() }
            ?: "User"
    }

    val posts = remember {
        mutableStateOf<List<PostDto>>(emptyList())
    }

    val isLoading = remember {
        mutableStateOf(true)
    }

    val errorMessage = remember {
        mutableStateOf("")
    }

    /*
     * Load posts from the ASP.NET Core REST API.
     *
     * The API reads the posts from PostgreSQL.
     */
    LaunchedEffect(Unit) {

        try {
            isLoading.value = true
            errorMessage.value = ""

            posts.value = RetrofitClient.apiService.getPosts()

        } catch (e: retrofit2.HttpException) {

            errorMessage.value = when (e.code()) {
                404 -> "No community posts were found."
                500 -> "The server encountered an error. Please try again."
                else -> "Unable to load community posts. Please try again."
            }

        } catch (e: java.io.IOException) {

            errorMessage.value =
                "Unable to connect to the server. Please check your internet connection."

        } catch (e: Exception) {

            errorMessage.value =
                "Something went wrong while loading community posts."

        } finally {

            isLoading.value = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(UbuntuCream)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),

            contentPadding = PaddingValues(
                top = 36.dp,
                bottom = 125.dp
            )
        ) {

            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                ) {

                    Text(
                        text = "Hello, $userName",
                        color = UbuntuGreen,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1
                    )

                    Spacer(
                        modifier = Modifier.height(6.dp)
                    )

                    Text(
                        text = "Together, we create stronger communities.",
                        color = UbuntuText,
                        fontSize = 16.sp
                    )

                    Spacer(
                        modifier = Modifier.height(28.dp)
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(58.dp)
                            .border(
                                width = 1.5.dp,
                                color = UbuntuBorder,
                                shape = RoundedCornerShape(18.dp)
                            )
                            .padding(horizontal = 18.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {

                        Text(
                            text = "Search posts, help, or people...",
                            color = UbuntuSecondary,
                            fontSize = 15.sp
                        )
                    }

                    Spacer(
                        modifier = Modifier.height(24.dp)
                    )
                }
            }

            item {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    SimpleActionCard(
                        text = "Get Help",
                        modifier = Modifier.weight(1f),
                        onClick = {}
                    )

                    SimpleActionCard(
                        text = "Give Help",
                        modifier = Modifier.weight(1f),
                        onClick = {}
                    )

                    SimpleActionCard(
                        text = "Community",
                        modifier = Modifier.weight(1f),
                        onClick = onCommunityClick
                    )

                    SimpleActionCard(
                        text = "Events",
                        modifier = Modifier.weight(1f),
                        onClick = {}
                    )
                }

                Spacer(
                    modifier = Modifier.height(34.dp)
                )
            }

            item {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Community Feed",
                        color = UbuntuGreen,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "Most Recent",
                        color = UbuntuText,
                        fontSize = 14.sp
                    )
                }

                Spacer(
                    modifier = Modifier.height(16.dp)
                )
            }

            /*
             * Loading state.
             */
            if (isLoading.value) {

                item {

                    Text(
                        text = "Loading community posts...",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 24.dp,
                                vertical = 20.dp
                            ),
                        color = UbuntuSecondary,
                        fontSize = 15.sp
                    )
                }
            }

            /*
             * Error state.
             */
            if (!isLoading.value && errorMessage.value.isNotEmpty()) {

                item {

                    Text(
                        text = errorMessage.value,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 24.dp,
                                vertical = 20.dp
                            ),
                        color = Color(0xFFD32F2F),
                        fontSize = 15.sp
                    )
                }
            }

            /*
             * Real PostgreSQL/API posts.
             */
            if (!isLoading.value && errorMessage.value.isEmpty()) {

                items(posts.value) { post ->

                    HomeFeedCard(
                        post = HomeFeedPost(
                            author = "UbuntuHub Community",
                            time = "Live",
                            location = post.location,
                            category = post.category,
                            title = post.title,
                            description = post.description,
                            likes = "0",
                            comments = "0",
                            categoryIsOrange = post.category.equals(
                                "Donations",
                                ignoreCase = true
                            )
                        )
                    )
                }
            }
        }

        HomeBottomNavigationBar(
            modifier = Modifier.align(Alignment.BottomCenter),
            onHomeClick = onHomeClick,
            onExploreClick = onExploreClick,
            onPostClick = onPostClick,
            onMessagesClick = onMessagesClick,
            onProfileClick = onProfileClick
        )
    }
}

@Composable
private fun SimpleActionCard(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Box(
        modifier = modifier
            .height(70.dp)
            .border(
                width = 1.5.dp,
                color = UbuntuBorder,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,
            color = UbuntuGreen,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1
        )
    }
}

@Composable
private fun HomeFeedCard(
    post: HomeFeedPost
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 24.dp,
                end = 24.dp,
                bottom = 14.dp
            ),
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.5.dp,
                    color = UbuntuBorder,
                    shape = RoundedCornerShape(18.dp)
                )
                .padding(18.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = post.author,
                        color = UbuntuGreen,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(
                        text = "${post.time} • ${post.location}",
                        color = UbuntuSecondary,
                        fontSize = 13.sp,
                        maxLines = 1
                    )
                }

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Box(
                    modifier = Modifier
                        .background(
                            color = if (post.categoryIsOrange) {
                                UbuntuSoftOrange
                            } else {
                                UbuntuSoftGreen
                            },
                            shape = RoundedCornerShape(14.dp)
                        )
                        .padding(
                            horizontal = 10.dp,
                            vertical = 6.dp
                        )
                ) {

                    Text(
                        text = post.category,
                        color = if (post.categoryIsOrange) {
                            Color(0xFFC57700)
                        } else {
                            UbuntuGreen
                        },
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Medium,
                        maxLines = 1
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Text(
                text = post.title,
                color = UbuntuGreen,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(7.dp)
            )

            Text(
                text = post.description,
                color = UbuntuText,
                fontSize = 14.sp,
                lineHeight = 21.sp
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Likes ${post.likes}",
                    color = UbuntuSecondary,
                    fontSize = 12.sp
                )

                Spacer(
                    modifier = Modifier.width(18.dp)
                )

                Text(
                    text = "Comments ${post.comments}",
                    color = UbuntuSecondary,
                    fontSize = 12.sp
                )

                Spacer(
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = "View Post",
                    color = UbuntuGreen,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun HomeBottomNavigationBar(
    modifier: Modifier = Modifier,
    onHomeClick: () -> Unit,
    onExploreClick: () -> Unit,
    onPostClick: () -> Unit,
    onMessagesClick: () -> Unit,
    onProfileClick: () -> Unit
) {

    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(UbuntuGreenDark)
            .navigationBarsPadding()
            .padding(
                horizontal = 12.dp,
                vertical = 14.dp
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        HomeNavItem(
            text = "Home",
            selected = true,
            onClick = onHomeClick
        )

        HomeNavItem(
            text = "Explore",
            selected = false,
            onClick = onExploreClick
        )

        HomeNavItem(
            text = "Post",
            selected = false,
            onClick = onPostClick
        )

        HomeNavItem(
            text = "Messages",
            selected = false,
            onClick = onMessagesClick
        )

        HomeNavItem(
            text = "Profile",
            selected = false,
            onClick = onProfileClick
        )
    }
}

@Composable
private fun HomeNavItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Box(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 8.dp,
                vertical = 6.dp
            ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,
            color = if (selected) {
                UbuntuOrange
            } else {
                Color.White
            },
            fontSize = 13.sp,
            fontWeight = if (selected) {
                FontWeight.Bold
            } else {
                FontWeight.Medium
            },
            maxLines = 1
        )
    }
}