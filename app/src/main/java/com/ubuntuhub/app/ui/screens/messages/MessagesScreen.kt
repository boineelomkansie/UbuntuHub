package com.ubuntuhub.app.ui.screens.messages

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
private val UbuntuAvatar = Color(0xFFE8ECD8)

private data class MessageConversation(
    val name: String,
    val initials: String,
    val message: String,
    val time: String,
    val unreadCount: Int = 0
)

@Composable
fun MessagesScreen(
    onHomeClick: () -> Unit = {},
    onExploreClick: () -> Unit = {},
    onPostClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {},
    onConversationClick: (String) -> Unit = {},
    onNewMessageClick: () -> Unit = {},
    onFiltersClick: () -> Unit = {}
) {

    val conversations = remember {
        listOf(
            MessageConversation(
                name = "Thabo M.",
                initials = "TM",
                message = "Thank you so much, Danny! I really appreciate your help.",
                time = "8:45 AM",
                unreadCount = 1
            ),
            MessageConversation(
                name = "Lindiwe K.",
                initials = "LK",
                message = "I can drop them off this afternoon.",
                time = "7:20 AM",
                unreadCount = 2
            ),
            MessageConversation(
                name = "Sikho D.",
                initials = "SD",
                message = "Let's coordinate on Saturday.",
                time = "Yesterday"
            ),
            MessageConversation(
                name = "Community Care",
                initials = "CC",
                message = "Sipho: I'll bring some extra blankets.",
                time = "Yesterday",
                unreadCount = 3
            ),
            MessageConversation(
                name = "Nandi P.",
                initials = "NP",
                message = "Do you still need help with tutoring?",
                time = "2 days ago"
            ),
            MessageConversation(
                name = "Jason R.",
                initials = "JR",
                message = "Thanks again for your help!",
                time = "2 days ago"
            ),
            MessageConversation(
                name = "Events Team",
                initials = "ET",
                message = "Nandi: Don't forget the meeting tomorrow.",
                time = "3 days ago",
                unreadCount = 1
            ),
            MessageConversation(
                name = "Karabo S.",
                initials = "KS",
                message = "I have a few textbooks I can donate.",
                time = "4 days ago"
            )
        )
    }

    var searchText by remember {
        mutableStateOf("")
    }

    var selectedTab by remember {
        mutableStateOf("Chats")
    }

    val filteredConversations = conversations.filter { conversation ->
        conversation.name.contains(searchText, ignoreCase = true) ||
                conversation.message.contains(searchText, ignoreCase = true)
    }

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
                        start = 22.dp,
                        end = 18.dp,
                        top = 14.dp,
                        bottom = 4.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Messages",
                    modifier = Modifier.weight(1f),
                    color = UbuntuGreen,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold
                )

                TextButton(
                    onClick = onNewMessageClick
                ) {
                    Text(
                        text = "New",
                        color = UbuntuGreen,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // SEARCH
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 22.dp,
                        end = 22.dp,
                        top = 4.dp,
                        bottom = 10.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                    },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Search messages",
                            color = UbuntuSecondary,
                            fontSize = 14.sp
                        )
                    },
                    textStyle = androidx.compose.ui.text.TextStyle(
                        fontSize = 14.sp
                    ),
                    shape = RoundedCornerShape(14.dp)
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Box(
                    modifier = Modifier
                        .height(48.dp)
                        .border(
                            width = 1.dp,
                            color = UbuntuBorder,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .clickable {
                            onFiltersClick()
                        }
                        .padding(
                            horizontal = 13.dp
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Filters",
                        color = UbuntuGreen,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            // TABS
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 22.dp
                    )
                    .border(
                        width = 1.dp,
                        color = UbuntuBorder,
                        shape = RoundedCornerShape(14.dp)
                    )
                    .background(
                        color = UbuntuWhite,
                        shape = RoundedCornerShape(14.dp)
                    )
            ) {

                MessageTab(
                    title = "Chats",
                    selected = selectedTab == "Chats",
                    modifier = Modifier.weight(1f)
                ) {
                    selectedTab = "Chats"
                }

                MessageTab(
                    title = "Requests",
                    selected = selectedTab == "Requests",
                    modifier = Modifier.weight(1f)
                ) {
                    selectedTab = "Requests"
                }
            }

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            // CHAT LIST
            if (selectedTab == "Chats") {

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(
                        start = 22.dp,
                        end = 22.dp,
                        bottom = 12.dp
                    )
                ) {

                    items(
                        items = filteredConversations,
                        key = {
                            it.name
                        }
                    ) { conversation ->

                        MessageConversationRow(
                            conversation = conversation,
                            onClick = {
                                onConversationClick(conversation.name)
                            }
                        )

                        HorizontalDivider(
                            color = UbuntuBorder,
                            thickness = 1.dp
                        )
                    }
                }

            } else {

                RequestsContent(
                    modifier = Modifier.weight(1f)
                )
            }

            // BOTTOM NAVIGATION
            MessagesBottomNavigationBar(
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
private fun MessageTab(
    title: String,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .height(44.dp)
            .clickable {
                onClick()
            }
            .background(
                color = if (selected) {
                    UbuntuGreen
                } else {
                    Color.Transparent
                },
                shape = RoundedCornerShape(13.dp)
            ),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = title,
            color = if (selected) {
                Color.White
            } else {
                UbuntuSecondary
            },
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
private fun MessageConversationRow(
    conversation: MessageConversation,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(
                vertical = 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        // AVATAR
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(
                    color = UbuntuAvatar,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = conversation.initials,
                color = UbuntuGreen,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        // MESSAGE INFORMATION
        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = conversation.name,
                color = UbuntuText,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(3.dp)
            )

            Text(
                text = conversation.message,
                color = UbuntuSecondary,
                fontSize = 13.sp,
                lineHeight = 18.sp,
                maxLines = 1
            )
        }

        Spacer(
            modifier = Modifier.width(8.dp)
        )

        // TIME + UNREAD
        Column(
            horizontalAlignment = Alignment.End
        ) {

            Text(
                text = conversation.time,
                color = UbuntuSecondary,
                fontSize = 11.sp
            )

            if (conversation.unreadCount > 0) {

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Box(
                    modifier = Modifier
                        .size(23.dp)
                        .background(
                            color = UbuntuGreen,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = conversation.unreadCount.toString(),
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun RequestsContent(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 30.dp,
                vertical = 35.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Message Requests",
            color = UbuntuGreen,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Messages from people you have not connected with will appear here.",
            color = UbuntuSecondary,
            fontSize = 14.sp,
            lineHeight = 20.sp
        )
    }
}

@Composable
private fun MessagesBottomNavigationBar(
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
                vertical = 10.dp
            )
            .windowInsetsPadding(
                WindowInsets.navigationBars
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        MessageBottomNavigationItem(
            label = "Home",
            selected = false,
            onClick = onHomeClick
        )

        MessageBottomNavigationItem(
            label = "Explore",
            selected = false,
            onClick = onExploreClick
        )

        MessageBottomNavigationItem(
            label = "Post",
            selected = false,
            onClick = onPostClick
        )

        MessageBottomNavigationItem(
            label = "Messages",
            selected = true,
            onClick = onMessagesClick
        )

        MessageBottomNavigationItem(
            label = "Profile",
            selected = false,
            onClick = onProfileClick
        )
    }
}

@Composable
private fun MessageBottomNavigationItem(
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
                vertical = 7.dp
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