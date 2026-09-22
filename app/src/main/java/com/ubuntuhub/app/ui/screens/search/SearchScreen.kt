package com.ubuntuhub.app.ui.screens.search

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
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
fun SearchScreen(
    onBackClick: () -> Unit = {},
    onFiltersClick: () -> Unit = {},
    onPostClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onExploreClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("All") }

    val posts = listOf(
        SearchPost(
            name = "Thabo M.",
            time = "2 hours ago",
            location = "Johannesburg",
            category = "Requesting Help",
            title = "Need help with school supplies",
            description = "My sister starts school next month and we're struggling to afford stationery and books. Any help is appreciated.",
            likes = "24",
            comments = "12"
        ),
        SearchPost(
            name = "Lindiwe K.",
            time = "4 hours ago",
            location = "Soweto",
            category = "Offering Help",
            title = "Offering tutoring for Maths",
            description = "I'm a varsity student offering free Maths tutoring for high school learners. Let's build our future together!",
            likes = "32",
            comments = "8"
        ),
        SearchPost(
            name = "Community Care",
            time = "6 hours ago",
            location = "Cape Town",
            category = "Community Update",
            title = "Food Drive This Saturday",
            description = "Join us this Saturday as we distribute meals to families in need. Volunteers are welcome!",
            likes = "45",
            comments = "15"
        ),
        SearchPost(
            name = "Nandi P.",
            time = "1 day ago",
            location = "Pretoria",
            category = "Requesting Help",
            title = "Looking for a laptop for online classes",
            description = "I'm doing my first year online and need a laptop to keep up with my studies. Any assistance is appreciated.",
            likes = "18",
            comments = "6"
        )
    )

    val filteredPosts = posts.filter { post ->
        val matchesFilter =
            selectedFilter == "All" || post.category == selectedFilter

        val query = searchText.trim()

        val matchesSearch =
            query.isEmpty() ||
                    post.name.contains(query, ignoreCase = true) ||
                    post.location.contains(query, ignoreCase = true) ||
                    post.category.contains(query, ignoreCase = true) ||
                    post.title.contains(query, ignoreCase = true) ||
                    post.description.contains(query, ignoreCase = true)

        matchesFilter && matchesSearch
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
                    modifier = Modifier.size(60.dp)
                ) {
                    Text(
                        text = "Back",
                        color = UbuntuGreen,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Search",
                        color = UbuntuGreen,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                TextButton(
                    onClick = onFiltersClick,
                    modifier = Modifier.size(70.dp)
                ) {
                    Text(
                        text = "Filters",
                        color = UbuntuGreen,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
                    .padding(
                        start = 24.dp,
                        end = 24.dp,
                        bottom = 24.dp
                    )
            ) {

                Spacer(modifier = Modifier.height(10.dp))

                // SEARCH BAR
                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Search posts, help, or people...",
                            color = UbuntuSecondary,
                            fontSize = 16.sp
                        )
                    },
                    shape = RoundedCornerShape(18.dp)
                )

                Spacer(modifier = Modifier.height(28.dp))

                // QUICK FILTERS
                Text(
                    text = "Quick Filters",
                    color = UbuntuText,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterButton(
                        text = "All",
                        selected = selectedFilter == "All",
                        onClick = {
                            selectedFilter = "All"
                        }
                    )

                    FilterButton(
                        text = "Requesting Help",
                        selected = selectedFilter == "Requesting Help",
                        onClick = {
                            selectedFilter = "Requesting Help"
                        }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FilterButton(
                        text = "Offering Help",
                        selected = selectedFilter == "Offering Help",
                        onClick = {
                            selectedFilter = "Offering Help"
                        }
                    )

                    FilterButton(
                        text = "Community Update",
                        selected = selectedFilter == "Community Update",
                        onClick = {
                            selectedFilter = "Community Update"
                        }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                FilterButton(
                    text = "Events",
                    selected = selectedFilter == "Events",
                    onClick = {
                        selectedFilter = "Events"
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                // ADVANCED FILTERS
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Advanced Filters",
                        color = UbuntuText,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    TextButton(
                        onClick = {
                            selectedFilter = "All"
                            searchText = ""
                        }
                    ) {
                        Text(
                            text = "Clear All",
                            color = UbuntuOrange,
                            fontSize = 15.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                AdvancedFilterCard(
                    title = "Location",
                    value = "All Locations"
                )

                AdvancedFilterCard(
                    title = "Category",
                    value = "All Categories"
                )

                AdvancedFilterCard(
                    title = "Date Posted",
                    value = "Anytime"
                )

                AdvancedFilterCard(
                    title = "Sort By",
                    value = "Most Recent"
                )

                Spacer(modifier = Modifier.height(24.dp))

                // RESULTS HEADER
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${filteredPosts.size} results found",
                        color = UbuntuText,
                        fontSize = 16.sp,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "Most Recent",
                        color = UbuntuText,
                        fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // RESULTS
                if (filteredPosts.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 50.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No posts found.",
                            color = UbuntuSecondary,
                            fontSize = 17.sp
                        )
                    }
                } else {
                    filteredPosts.forEach { post ->
                        SearchPostCard(
                            post = post,
                            onClick = {
                                onPostClick()
                            }
                        )

                        Spacer(modifier = Modifier.height(14.dp))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }

            // BOTTOM NAVIGATION
            SearchBottomNavigationBar(
                onHomeClick = onHomeClick,
                onExploreClick = onExploreClick,
                onPostClick = onPostClick,
                onMessagesClick = onMessagesClick,
                onProfileClick = onProfileClick
            )
        }
    }
}

private data class SearchPost(
    val name: String,
    val time: String,
    val location: String,
    val category: String,
    val title: String,
    val description: String,
    val likes: String,
    val comments: String
)

@Composable
private fun FilterButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = text,
        modifier = Modifier
            .clickable { onClick() }
            .background(
                color = if (selected) UbuntuGreen else UbuntuWhite,
                shape = RoundedCornerShape(18.dp)
            )
            .border(
                width = 1.dp,
                color = if (selected) UbuntuGreen else UbuntuBorder,
                shape = RoundedCornerShape(18.dp)
            )
            .padding(
                horizontal = 16.dp,
                vertical = 10.dp
            ),
        color = if (selected) Color.White else UbuntuText,
        fontSize = 14.sp,
        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
    )
}

@Composable
private fun AdvancedFilterCard(
    title: String,
    value: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = UbuntuWhite,
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 1.dp,
                color = UbuntuBorder,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(
                horizontal = 18.dp,
                vertical = 14.dp
            )
    ) {
        Text(
            text = title,
            color = UbuntuText,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = value,
            color = UbuntuSecondary,
            fontSize = 14.sp
        )
    }

    Spacer(modifier = Modifier.height(6.dp))
}

@Composable
private fun SearchPostCard(
    post: SearchPost,
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
            .clickable { onClick() }
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
                    text = post.name,
                    color = UbuntuGreen,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "${post.time} • ${post.location}",
                    color = UbuntuSecondary,
                    fontSize = 14.sp
                )
            }

            Text(
                text = post.category,
                modifier = Modifier
                    .background(
                        color = if (post.category == "Offering Help") {
                            Color(0xFFFFE8C9)
                        } else {
                            Color(0xFFE9ECD8)
                        },
                        shape = RoundedCornerShape(14.dp)
                    )
                    .padding(
                        horizontal = 10.dp,
                        vertical = 6.dp
                    ),
                color = if (post.category == "Offering Help") {
                    UbuntuOrange
                } else {
                    UbuntuGreen
                },
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Spacer(modifier = Modifier.height(14.dp))

        Text(
            text = post.title,
            color = UbuntuText,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = post.description,
            color = UbuntuText,
            fontSize = 15.sp,
            lineHeight = 21.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "♡  ${post.likes}",
                color = UbuntuGreen,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.width(24.dp))

            Text(
                text = "○  ${post.comments}",
                color = UbuntuGreen,
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = "View Post",
                color = UbuntuGreen,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun SearchBottomNavigationBar(
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
        SearchBottomNavigationItem(
            label = "Home",
            selected = false,
            onClick = onHomeClick
        )

        SearchBottomNavigationItem(
            label = "Explore",
            selected = true,
            onClick = onExploreClick
        )

        SearchBottomNavigationItem(
            label = "Post",
            selected = false,
            onClick = onPostClick
        )

        SearchBottomNavigationItem(
            label = "Messages",
            selected = false,
            onClick = onMessagesClick
        )

        SearchBottomNavigationItem(
            label = "Profile",
            selected = false,
            onClick = onProfileClick
        )
    }
}

@Composable
private fun SearchBottomNavigationItem(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = label,
        modifier = Modifier
            .clickable { onClick() }
            .padding(
                horizontal = 10.dp,
                vertical = 8.dp
            ),
        color = if (selected) UbuntuOrange else Color.White,
        fontSize = 14.sp,
        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
    )
}