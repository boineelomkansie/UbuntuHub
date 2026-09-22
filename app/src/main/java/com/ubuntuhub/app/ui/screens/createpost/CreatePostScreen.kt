package com.ubuntuhub.app.ui.screens.createpost

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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.rememberCoroutineScope
import com.ubuntuhub.app.data.CreatePostRequest
import com.ubuntuhub.app.data.RetrofitClient
import kotlinx.coroutines.launch

private val UbuntuGreen = Color(0xFF205C3B)
private val UbuntuOrange = Color(0xFFF39A24)
private val UbuntuCream = Color(0xFFFAE8D4)
private val UbuntuWhite = Color(0xFFFFFCF8)
private val UbuntuText = Color(0xFF202124)
private val UbuntuSecondary = Color(0xFF6B7280)
private val UbuntuBorder = Color(0xFFE5D7C8)
private val UbuntuError = Color(0xFFD32F2F)
private val UbuntuSoftGreen = Color(0xFFE8F0E7)
private val UbuntuAvatar = Color(0xFFE1E4C8)

private const val MAX_POST_LENGTH = 1500

@Composable
fun CreatePostScreen(
    onBackClick: () -> Unit = {},
    onPostCreated: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onExploreClick: () -> Unit = {},
    onPostClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {},
    onProfileClick: () -> Unit = {}
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

    var postText by remember {
        mutableStateOf("")
    }

    var selectedCategory by remember {
        mutableStateOf("Requesting Help")
    }

    var location by remember {
        mutableStateOf("")
    }

    var privacy by remember {
        mutableStateOf("Public")
    }

    var errorMessage by remember {
        mutableStateOf("")
    }

    var draftSaved by remember {
        mutableStateOf(false)
    }

    var isPosting by remember {
        mutableStateOf(false)
    }

    val coroutineScope = rememberCoroutineScope()

    val categories = listOf(
        "Requesting Help",
        "Offering Help",
        "Community Update",
        "Event",
        "Other"
    )

    val characterCount = postText.length

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
                    top = 16.dp,
                    bottom = 10.dp
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
                    text = "Create Post",
                    color = UbuntuGreen,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.width(45.dp)
            )
        }

        // =============================================================
        // SCROLLABLE CONTENT
        // =============================================================

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    bottom = 28.dp
                )
        ) {

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            // =========================================================
            // POST AS
            // =========================================================

            SectionTitle(
                title = "Post As"
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            ProfileCard(
                userName = userName,
                userInitial = userInitial
            )

            Spacer(
                modifier = Modifier.height(26.dp)
            )

            // =========================================================
            // POST CONTENT
            // =========================================================

            SectionTitle(
                title = "What's on your mind?"
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Box(
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
                    .padding(4.dp)
            ) {

                OutlinedTextField(
                    value = postText,
                    onValueChange = {
                        if (it.length <= MAX_POST_LENGTH) {
                            postText = it
                            errorMessage = ""
                            draftSaved = false
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(205.dp),
                    placeholder = {
                        Text(
                            text = "Share something with your community...",
                            color = UbuntuSecondary,
                            fontSize = 16.sp
                        )
                    },
                    supportingText = {
                        Text(
                            text = "$characterCount/$MAX_POST_LENGTH",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                            color = UbuntuSecondary,
                            fontSize = 12.sp
                        )
                    }
                )
            }

            Spacer(
                modifier = Modifier.height(26.dp)
            )

            // =========================================================
            // CATEGORY
            // =========================================================

            SectionTitle(
                title = "Category"
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                categories.chunked(2).forEach { rowCategories ->

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {

                        rowCategories.forEach { category ->

                            CategoryButton(
                                category = category,
                                selected = selectedCategory == category,
                                onClick = {
                                    selectedCategory = category
                                    errorMessage = ""
                                },
                                modifier = Modifier.weight(1f)
                            )
                        }

                        if (rowCategories.size == 1) {
                            Spacer(
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }

            Spacer(
                modifier = Modifier.height(26.dp)
            )

            // =========================================================
            // LOCATION
            // =========================================================

            Row(
                verticalAlignment = Alignment.Bottom
            ) {

                SectionTitle(
                    title = "Add Location"
                )

                Spacer(
                    modifier = Modifier.width(6.dp)
                )

                Text(
                    text = "(Optional)",
                    color = UbuntuSecondary,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(
                        bottom = 2.dp
                    )
                )
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            OutlinedTextField(
                value = location,
                onValueChange = {
                    location = it
                },
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = "Add location...",
                        color = UbuntuSecondary
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )

            Spacer(
                modifier = Modifier.height(26.dp)
            )

            // =========================================================
            // PHOTOS
            // =========================================================

            Row(
                verticalAlignment = Alignment.Bottom
            ) {

                SectionTitle(
                    title = "Add Photos"
                )

                Spacer(
                    modifier = Modifier.width(6.dp)
                )

                Text(
                    text = "(Optional)",
                    color = UbuntuSecondary,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(
                        bottom = 2.dp
                    )
                )
            }

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(125.dp)
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
                        // Photo picker will be implemented later.
                    },
                contentAlignment = Alignment.Center
            ) {

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "Add photos",
                        color = UbuntuGreen,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(5.dp)
                    )

                    Text(
                        text = "PNG or JPG up to 10MB each",
                        color = UbuntuSecondary,
                        fontSize = 13.sp
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(26.dp)
            )

            // =========================================================
            // PRIVACY
            // =========================================================

            SectionTitle(
                title = "Privacy"
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            PrivacyCard(
                selectedPrivacy = privacy,
                onPrivacyChanged = {
                    privacy = it
                }
            )

            // =========================================================
            // MESSAGES
            // =========================================================

            if (errorMessage.isNotEmpty()) {

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = Color(0xFFFFEEEE),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(12.dp)
                ) {

                    Text(
                        text = errorMessage,
                        color = UbuntuError,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            if (draftSaved) {

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = UbuntuSoftGreen,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(12.dp)
                ) {

                    Text(
                        text = "Draft saved successfully.",
                        color = UbuntuGreen,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(24.dp)
            )

            // =========================================================
            // ACTION BUTTONS
            // =========================================================

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp)
                        .background(
                            color = UbuntuWhite,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = UbuntuGreen,
                            shape = RoundedCornerShape(14.dp)
                        )
                        .clickable {
                            draftSaved = true
                            errorMessage = ""
                        },
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "Save Draft",
                        color = UbuntuGreen,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Button(
                    onClick = {
                        when {
                            postText.isBlank() -> {
                                errorMessage =
                                    "Please enter something to share with your community."
                            }

                            selectedCategory.isBlank() -> {
                                errorMessage =
                                    "Please select a category."
                            }

                            location.isBlank() -> {
                                errorMessage =
                                    "Please enter a location."
                            }

                            else -> {
                                errorMessage = ""
                                draftSaved = false
                                isPosting = true

                                coroutineScope.launch {
                                    try {
                                        val request = CreatePostRequest(
                                            userId = 1,
                                            title = selectedCategory,
                                            description = postText.trim(),
                                            location = location.trim(),
                                            category = selectedCategory
                                        )

                                        RetrofitClient.apiService.createPost(request)

                                        postText = ""
                                        location = ""
                                        errorMessage = ""

                                        onPostCreated()

                                    } catch (e: Exception) {
                                        errorMessage =
                                            "Unable to create post. Please check your connection and try again."
                                    } finally {
                                        isPosting = false
                                    }
                                }
                            }
                        }
                    },
                    enabled = !isPosting,
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = UbuntuGreen
                    )
                ) {
                    Text(
                        text = if (isPosting) "Posting..." else "Post",
                        color = Color.White,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }

        // =============================================================
        // FIXED BOTTOM NAVIGATION
        // =============================================================

        BottomNavigationBar(
            onHomeClick = onHomeClick,
            onExploreClick = onExploreClick,
            onPostClick = onPostClick,
            onMessagesClick = onMessagesClick,
            onProfileClick = onProfileClick
        )
    }
}

// =====================================================================
// SECTION TITLE
// =====================================================================

@Composable
private fun SectionTitle(
    title: String
) {
    Text(
        text = title,
        color = UbuntuGreen,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
}

// =====================================================================
// PROFILE CARD
// =====================================================================

@Composable
private fun ProfileCard(
    userName: String,
    userInitial: String
) {

    Row(
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
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(54.dp)
                .background(
                    color = UbuntuAvatar,
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = userInitial,
                color = UbuntuGreen,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier.width(13.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = userName,
                color = UbuntuText,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(2.dp)
            )

            Text(
                text = "Posting to the UbuntuHub community",
                color = UbuntuSecondary,
                fontSize = 13.sp
            )
        }

        Text(
            text = "Public",
            color = UbuntuGreen,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// =====================================================================
// CATEGORY BUTTON
// =====================================================================

@Composable
private fun CategoryButton(
    category: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .height(76.dp)
            .background(
                color = if (selected) {
                    UbuntuSoftGreen
                } else {
                    UbuntuWhite
                },
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = if (selected) 2.dp else 1.dp,
                color = if (selected) {
                    UbuntuGreen
                } else {
                    UbuntuBorder
                },
                shape = RoundedCornerShape(16.dp)
            )
            .clickable {
                onClick()
            }
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = category,
            modifier = Modifier.fillMaxWidth(),
            color = if (selected) {
                UbuntuGreen
            } else {
                UbuntuText
            },
            fontSize = 13.sp,
            fontWeight = if (selected) {
                FontWeight.Bold
            } else {
                FontWeight.Medium
            },
            textAlign = TextAlign.Center
        )
    }
}

// =====================================================================
// PRIVACY CARD
// =====================================================================

@Composable
private fun PrivacyCard(
    selectedPrivacy: String,
    onPrivacyChanged: (String) -> Unit
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
    ) {

        PrivacyOption(
            title = "Public",
            description = "Anyone in the community can see this post",
            selected = selectedPrivacy == "Public",
            onClick = {
                onPrivacyChanged("Public")
            }
        )

        HorizontalDivider(
            modifier = Modifier.padding(
                horizontal = 16.dp
            ),
            color = UbuntuBorder
        )

        PrivacyOption(
            title = "Community Only",
            description = "Only registered community members can see this post",
            selected = selectedPrivacy == "Community Only",
            onClick = {
                onPrivacyChanged("Community Only")
            }
        )
    }
}

// =====================================================================
// PRIVACY OPTION
// =====================================================================

@Composable
private fun PrivacyOption(
    title: String,
    description: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 16.dp,
                vertical = 14.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = title,
                color = UbuntuText,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(3.dp)
            )

            Text(
                text = description,
                color = UbuntuSecondary,
                fontSize = 12.sp
            )
        }

        Switch(
            checked = selected,
            onCheckedChange = {
                onClick()
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = UbuntuGreen,
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color(0xFFD0CBC4),
                uncheckedBorderColor = Color.Transparent
            )
        )
    }
}

// =====================================================================
// BOTTOM NAVIGATION
// =====================================================================

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
            .navigationBarsPadding()
            .padding(
                horizontal = 8.dp,
                vertical = 11.dp
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
            selected = true,
            onClick = onPostClick
        )

        BottomNavigationItem(
            label = "Messages",
            selected = false,
            onClick = onMessagesClick
        )

        BottomNavigationItem(
            label = "Profile",
            selected = false,
            onClick = onProfileClick
        )
    }
}

// =====================================================================
// BOTTOM NAVIGATION ITEM
// =====================================================================

@Composable
private fun BottomNavigationItem(
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
                horizontal = 8.dp,
                vertical = 8.dp
            ),
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
        }
    )
}