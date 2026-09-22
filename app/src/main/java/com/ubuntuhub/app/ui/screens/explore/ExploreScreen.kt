package com.ubuntuhub.app.ui.screens.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val UbuntuGreen = Color(0xFF205C3B)
private val UbuntuOrange = Color(0xFFF39A24)
private val UbuntuCream = Color(0xFFFAE8D4)
private val UbuntuWhite = Color(0xFFFFFCF8)
private val UbuntuText = Color(0xFF202124)
private val UbuntuSecondary = Color(0xFF52606D)
private val UbuntuBorder = Color(0xFFE5D7C8)
private val UbuntuSoftGreen = Color(0xFFE9EAD5)
private val UbuntuSoftOrange = Color(0xFFFFE8C7)

private data class ExplorePost(
    val name: String,
    val time: String,
    val location: String,
    val category: String,
    val title: String,
    val description: String,
    val likes: Int,
    val comments: Int,
    val shares: Int
)

@Composable
fun ExploreScreen(
    onPostClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onExploreClick: () -> Unit = {},
    onPostNavigationClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onFiltersClick: () -> Unit = {}
) {
    var searchText by remember {
        mutableStateOf("")
    }

    var selectedFilter by remember {
        mutableStateOf("All")
    }

    val posts = listOf(
        ExplorePost(
            name = "Thabo M.",
            time = "2 hours ago",
            location = "Johannesburg",
            category = "Requesting Help",
            title = "Need help with school supplies",
            description = "My sister starts school next month and we're struggling to afford stationery and books. Any help is appreciated.",
            likes = 24,
            comments = 12,
            shares = 5
        ),
        ExplorePost(
            name = "Lindiwe K.",
            time = "4 hours ago",
            location = "Soweto",
            category = "Offering Help",
            title = "Offering tutoring for Maths",
            description = "I'm a varsity student offering free Maths tutoring for high school learners. Let's build our future together!",
            likes = 32,
            comments = 8,
            shares = 3
        ),
        ExplorePost(
            name = "Community Care",
            time = "6 hours ago",
            location = "Cape Town",
            category = "Community Update",
            title = "Food Drive This Saturday",
            description = "Join us this Saturday as we distribute meals to families in need. Volunteers are welcome!",
            likes = 45,
            comments = 15,
            shares = 8
        ),
        ExplorePost(
            name = "Nandi P.",
            time = "1 day ago",
            location = "Pretoria",
            category = "Requesting Help",
            title = "Looking for a laptop for online classes",
            description = "I'm doing my first year online and need a laptop to keep up with my studies. Any assistance is appreciated.",
            likes = 19,
            comments = 7,
            shares = 4
        )
    )

    val filteredPosts = posts.filter { post ->

        val matchesFilter =
            selectedFilter == "All" || post.category == selectedFilter

        val matchesSearch =
            searchText.isBlank() ||
                    post.name.contains(searchText, ignoreCase = true) ||
                    post.title.contains(searchText, ignoreCase = true) ||
                    post.description.contains(searchText, ignoreCase = true) ||
                    post.location.contains(searchText, ignoreCase = true)

        matchesFilter && matchesSearch
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(UbuntuCream)
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) {

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentPadding = PaddingValues(
                top = 24.dp,
                bottom = 20.dp
            )
        ) {

            item {

                Text(
                    text = "Explore",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    color = UbuntuGreen,
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = "Discover help, people and opportunities.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    color = UbuntuSecondary,
                    fontSize = 15.sp
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Search posts, help or people...",
                            color = UbuntuSecondary,
                            fontSize = 15.sp
                        )
                    },
                    shape = RoundedCornerShape(16.dp)
                )

                Spacer(
                    modifier = Modifier.height(24.dp)
                )

                Text(
                    text = "Categories",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    color = UbuntuGreen,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    ExploreFilterButton(
                        text = "All",
                        selected = selectedFilter == "All",
                        modifier = Modifier.weight(0.6f)
                    ) {
                        selectedFilter = "All"
                    }

                    ExploreFilterButton(
                        text = "Requesting Help",
                        selected = selectedFilter == "Requesting Help",
                        modifier = Modifier.weight(1.5f)
                    ) {
                        selectedFilter = "Requesting Help"
                    }
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    ExploreFilterButton(
                        text = "Offering Help",
                        selected = selectedFilter == "Offering Help",
                        modifier = Modifier.weight(1f)
                    ) {
                        selectedFilter = "Offering Help"
                    }

                    ExploreFilterButton(
                        text = "Community Update",
                        selected = selectedFilter == "Community Update",
                        modifier = Modifier.weight(1.4f)
                    ) {
                        selectedFilter = "Community Update"
                    }
                }

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    ExploreFilterButton(
                        text = "Events",
                        selected = selectedFilter == "Events",
                        modifier = Modifier.weight(0.8f)
                    ) {
                        selectedFilter = "Events"
                    }

                    Spacer(
                        modifier = Modifier.weight(1.6f)
                    )
                }

                Spacer(
                    modifier = Modifier.height(28.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Recent Community Posts",
                        color = UbuntuGreen,
                        fontSize = 21.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "${filteredPosts.size} posts",
                        color = UbuntuSecondary,
                        fontSize = 13.sp
                    )
                }

                Spacer(
                    modifier = Modifier.height(14.dp)
                )
            }

            items(filteredPosts) { post ->

                ExplorePostCard(
                    post = post,
                    onClick = onPostClick
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )
            }

            if (filteredPosts.isEmpty()) {

                item {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = 32.dp,
                                vertical = 50.dp
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text(
                            text = "No posts found",
                            color = UbuntuGreen,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )

                        Text(
                            text = "Try another search or category.",
                            color = UbuntuSecondary,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }

        ExploreBottomNavigation(
            onHomeClick = onHomeClick,
            onExploreClick = onExploreClick,
            onPostClick = onPostNavigationClick,
            onMessagesClick = onMessagesClick,
            onProfileClick = onProfileClick
        )
    }
}

@Composable
private fun ExploreFilterButton(
    text: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(46.dp)
            .background(
                color = if (selected) {
                    UbuntuGreen
                } else {
                    UbuntuWhite
                },
                shape = RoundedCornerShape(14.dp)
            )
            .border(
                width = 1.dp,
                color = if (selected) {
                    UbuntuGreen
                } else {
                    UbuntuBorder
                },
                shape = RoundedCornerShape(14.dp)
            )
            .clickable {
                onClick()
            },
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,
            color = if (selected) {
                Color.White
            } else {
                UbuntuText
            },
            fontSize = 13.sp,
            fontWeight = if (selected) {
                FontWeight.Bold
            } else {
                FontWeight.Medium
            },
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun ExplorePostCard(
    post: ExplorePost,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp)
            .background(
                color = UbuntuWhite,
                shape = RoundedCornerShape(20.dp)
            )
            .border(
                width = 1.dp,
                color = UbuntuBorder,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable {
                onClick()
            }
            .padding(18.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .background(
                        color = UbuntuSoftGreen,
                        shape = RoundedCornerShape(50)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = post.name
                        .split(" ")
                        .mapNotNull {
                            it.firstOrNull()?.toString()
                        }
                        .take(2)
                        .joinToString(""),
                    color = UbuntuGreen,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.width(12.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = post.name,
                    color = UbuntuGreen,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(2.dp)
                )

                Text(
                    text = "${post.time} • ${post.location}",
                    color = UbuntuSecondary,
                    fontSize = 13.sp
                )
            }

            Box(
                modifier = Modifier
                    .background(
                        color = if (post.category == "Offering Help") {
                            UbuntuSoftOrange
                        } else {
                            UbuntuSoftGreen
                        },
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(
                        horizontal = 9.dp,
                        vertical = 6.dp
                    )
            ) {

                Text(
                    text = post.category,
                    color = if (post.category == "Offering Help") {
                        UbuntuOrange
                    } else {
                        UbuntuGreen
                    },
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )
            }
        }

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        Text(
            text = post.title,
            color = UbuntuText,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(
            text = post.description,
            color = UbuntuSecondary,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        Text(
            text = post.location,
            color = UbuntuGreen,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(
            modifier = Modifier.height(12.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(UbuntuBorder)
        )

        Spacer(
            modifier = Modifier.height(10.dp)
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
                modifier = Modifier.width(16.dp)
            )

            Text(
                text = "Comments ${post.comments}",
                color = UbuntuSecondary,
                fontSize = 12.sp
            )

            Spacer(
                modifier = Modifier.width(16.dp)
            )

            Text(
                text = "Shares ${post.shares}",
                color = UbuntuSecondary,
                fontSize = 12.sp
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "View Post",
                color = UbuntuGreen,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun ExploreBottomNavigation(
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
                horizontal = 8.dp,
                vertical = 12.dp
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        ExploreNavigationItem(
            label = "Home",
            selected = false,
            onClick = onHomeClick
        )

        ExploreNavigationItem(
            label = "Explore",
            selected = true,
            onClick = onExploreClick
        )

        ExploreNavigationItem(
            label = "Post",
            selected = false,
            onClick = onPostClick
        )

        ExploreNavigationItem(
            label = "Messages",
            selected = false,
            onClick = onMessagesClick
        )

        ExploreNavigationItem(
            label = "Profile",
            selected = false,
            onClick = onProfileClick
        )
    }
}

@Composable
private fun ExploreNavigationItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = label,
        modifier = Modifier
            .clickable {
                onClick()
            }
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