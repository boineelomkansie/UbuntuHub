package com.ubuntuhub.app.ui.screens.search

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
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val UbuntuGreen = Color(0xFF205C3B)
private val UbuntuOrange = Color(0xFFF39A24)
private val UbuntuCream = Color(0xFFFAE8D4)
private val UbuntuWhite = Color(0xFFFFFCF8)
private val UbuntuText = Color(0xFF202124)
private val UbuntuSecondary = Color(0xFF506070)
private val UbuntuBorder = Color(0xFFE8DCCF)

@Composable
fun SearchFilterScreen(
    onBackClick: () -> Unit = {},
    onFiltersClick: () -> Unit = {},
    onViewPostClick: (String) -> Unit = {},
    onHomeClick: () -> Unit = {},
    onExploreClick: () -> Unit = {},
    onPostClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
) {
    var searchText by remember { mutableStateOf("") }
    var selectedFilter by remember { mutableStateOf("All") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(UbuntuCream)
    ) {

        // HEADER
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
            TextButton(
                onClick = onBackClick,
                modifier = Modifier.width(60.dp)
            ) {
                Text(
                    text = "Back",
                    color = UbuntuGreen,
                    fontSize = 14.sp,
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

            Spacer(
                modifier = Modifier.width(60.dp)
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 24.dp,
                    end = 24.dp
                )
        ) {

            // SEARCH AREA
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier.weight(1f),
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

                Spacer(
                    modifier = Modifier.width(12.dp)
                )

                OutlinedButton(
                    onClick = onFiltersClick,
                    modifier = Modifier.height(56.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = "Filters",
                        color = UbuntuText,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            // QUICK FILTERS
            Text(
                text = "Quick Filters",
                color = UbuntuText,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                QuickFilterButton(
                    text = "All",
                    selected = selectedFilter == "All",
                    onClick = {
                        selectedFilter = "All"
                    }
                )

                QuickFilterButton(
                    text = "Requesting Help",
                    selected = selectedFilter == "Requesting Help",
                    onClick = {
                        selectedFilter = "Requesting Help"
                    }
                )
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                QuickFilterButton(
                    text = "Offering Help",
                    selected = selectedFilter == "Offering Help",
                    onClick = {
                        selectedFilter = "Offering Help"
                    }
                )

                QuickFilterButton(
                    text = "Community Update",
                    selected = selectedFilter == "Community Update",
                    onClick = {
                        selectedFilter = "Community Update"
                    }
                )
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            QuickFilterButton(
                text = "Events",
                selected = selectedFilter == "Events",
                onClick = {
                    selectedFilter = "Events"
                }
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // ADVANCED FILTERS HEADER
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Advanced Filters",
                    modifier = Modifier.weight(1f),
                    color = UbuntuText,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )

                TextButton(
                    onClick = {
                        selectedFilter = "All"
                    }
                ) {
                    Text(
                        text = "Clear All",
                        color = UbuntuOrange,
                        fontSize = 15.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            // ADVANCED FILTER CARD
            FilterCard {

                FilterRow(
                    title = "Location",
                    value = "All Locations"
                )

                FilterDivider()

                FilterRow(
                    title = "Category",
                    value = "All Categories"
                )

                FilterDivider()

                FilterRow(
                    title = "Date Posted",
                    value = "Anytime"
                )

                FilterDivider()

                FilterRow(
                    title = "Sort By",
                    value = "Most Recent"
                )
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // RESULTS HEADER
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "24 results found",
                    modifier = Modifier.weight(1f),
                    color = UbuntuText,
                    fontSize = 16.sp
                )

                Text(
                    text = "Most Recent",
                    color = UbuntuText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // RESULT 1
            SearchResultCard(
                name = "Thabo M.",
                time = "2 hours ago",
                location = "Johannesburg",
                category = "Requesting Help",
                title = "Need help with school supplies",
                description = "My sister starts school next month and we're struggling to afford stationery and books. Any help is appreciated.",
                likes = "24",
                comments = "12",
                onViewPostClick = {
                    onViewPostClick("school_supplies")
                }
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // RESULT 2
            SearchResultCard(
                name = "Lindiwe K.",
                time = "4 hours ago",
                location = "Soweto",
                category = "Offering Help",
                title = "Offering tutoring for Maths",
                description = "I'm a varsity student offering free Maths tutoring for high school learners. Let's build our future together!",
                likes = "32",
                comments = "8",
                onViewPostClick = {
                    onViewPostClick("maths_tutoring")
                }
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            // RESULT 3
            SearchResultCard(
                name = "Community Care",
                time = "6 hours ago",
                location = "Cape Town",
                category = "Community Update",
                title = "Food Drive This Saturday",
                description = "Join us this Saturday as we distribute meals to families in need. Volunteers are welcome!",
                likes = "45",
                comments = "15",
                onViewPostClick = {
                    onViewPostClick("food_drive")
                }
            )

            Spacer(
                modifier = Modifier.height(24.dp)
            )
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

@Composable
private fun QuickFilterButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    if (selected) {
        Button(
            onClick = onClick,
            modifier = Modifier.height(50.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = UbuntuGreen
            )
        ) {
            Text(
                text = text,
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
        }
    } else {
        OutlinedButton(
            onClick = onClick,
            modifier = Modifier.height(50.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = text,
                color = UbuntuText,
                fontSize = 14.sp
            )
        }
    }
}

@Composable
private fun FilterCard(
    content: @Composable ColumnScope.() -> Unit
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
            .padding(
                horizontal = 16.dp
            ),
        content = content
    )
}

@Composable
private fun FilterRow(
    title: String,
    value: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(76.dp),
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

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = value,
                color = UbuntuSecondary,
                fontSize = 15.sp
            )
        }

        Text(
            text = "Select",
            color = UbuntuGreen,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun FilterDivider() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(UbuntuBorder)
    )
}

@Composable
private fun SearchResultCard(
    name: String,
    time: String,
    location: String,
    category: String,
    title: String,
    description: String,
    likes: String,
    comments: String,
    onViewPostClick: () -> Unit
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
            .padding(20.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                modifier = Modifier
                    .size(54.dp)
                    .background(
                        color = Color(0xFFE8ECD8),
                        shape = RoundedCornerShape(27.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = name.take(1),
                    color = UbuntuGreen,
                    fontSize = 22.sp,
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
                    text = name,
                    color = UbuntuGreen,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(3.dp)
                )

                Text(
                    text = "$time • $location",
                    color = UbuntuSecondary,
                    fontSize = 14.sp
                )
            }

            Box(
                modifier = Modifier
                    .background(
                        color = if (category == "Offering Help") {
                            Color(0xFFFFE7C4)
                        } else {
                            Color(0xFFE9ECD9)
                        },
                        shape = RoundedCornerShape(14.dp)
                    )
                    .padding(
                        horizontal = 10.dp,
                        vertical = 7.dp
                    )
            ) {
                Text(
                    text = category,
                    color = if (category == "Offering Help") {
                        UbuntuOrange
                    } else {
                        UbuntuGreen
                    },
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = title,
            color = UbuntuText,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(7.dp)
        )

        Text(
            text = description,
            color = UbuntuText,
            fontSize = 15.sp,
            lineHeight = 22.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Likes $likes",
                color = UbuntuSecondary,
                fontSize = 14.sp
            )

            Spacer(
                modifier = Modifier.width(20.dp)
            )

            Text(
                text = "Comments $comments",
                color = UbuntuSecondary,
                fontSize = 14.sp
            )

            Spacer(
                modifier = Modifier.weight(1f)
            )

            OutlinedButton(
                onClick = onViewPostClick,
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    text = "View Post",
                    color = UbuntuGreen,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
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
                vertical = 16.dp
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        SearchBottomItem(
            label = "Home",
            selected = false,
            onClick = onHomeClick
        )

        SearchBottomItem(
            label = "Explore",
            selected = true,
            onClick = onExploreClick
        )

        SearchBottomItem(
            label = "Post",
            selected = false,
            onClick = onPostClick
        )

        SearchBottomItem(
            label = "Messages",
            selected = false,
            onClick = onMessagesClick
        )

        SearchBottomItem(
            label = "Profile",
            selected = false,
            onClick = onProfileClick
        )
    }
}

@Composable
private fun SearchBottomItem(
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
        fontWeight = if (selected) {
            FontWeight.Bold
        } else {
            FontWeight.Normal
        }
    )
}