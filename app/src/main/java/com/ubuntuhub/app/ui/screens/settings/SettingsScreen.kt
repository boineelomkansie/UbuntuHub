package com.ubuntuhub.app.ui.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth

private val UbuntuGreen = Color(0xFF205C3B)
private val UbuntuOrange = Color(0xFFF39A24)
private val UbuntuCream = Color(0xFFFAE8D4)
private val UbuntuWhite = Color(0xFFFFFCF8)
private val UbuntuText = Color(0xFF202124)
private val UbuntuSecondary = Color(0xFF6B7280)
private val UbuntuBorder = Color(0xFFE8DCCF)
private val UbuntuError = Color(0xFFD32F2F)

@Composable
fun SettingsScreen(
    onBackClick: () -> Unit = {},
    onLogoutClick: () -> Unit = {},
    onEditProfileClick: () -> Unit = {},
    onChangePasswordClick: () -> Unit = {},
    onEmailPreferencesClick: () -> Unit = {},
    onPhoneNumberClick: () -> Unit = {},
    onAccountSecurityClick: () -> Unit = {},
    onPushNotificationsClick: () -> Unit = {},
    onEmailNotificationsClick: () -> Unit = {},
    onMessageNotificationsClick: () -> Unit = {},
    onPrivacySettingsClick: () -> Unit = {},
    onBlockedUsersClick: () -> Unit = {},
    onHideActivityClick: () -> Unit = {},
    onLanguageClick: () -> Unit = {},
    onHelpSupportClick: () -> Unit = {},
    onAboutClick: () -> Unit = {},
    onHomeClick: () -> Unit = {},
    onExploreClick: () -> Unit = {},
    onPostClick: () -> Unit = {},
    onMessagesClick: () -> Unit = {}
) {

    val auth = remember {
        FirebaseAuth.getInstance()
    }

    var pushNotificationsEnabled by remember {
        mutableStateOf(true)
    }

    var emailNotificationsEnabled by remember {
        mutableStateOf(true)
    }

    var messageNotificationsEnabled by remember {
        mutableStateOf(true)
    }

    var darkModeEnabled by remember {
        mutableStateOf(false)
    }

    var errorMessage by remember {
        mutableStateOf("")
    }

    val backgroundColor =
        if (darkModeEnabled) {
            Color(0xFF171717)
        } else {
            UbuntuCream
        }

    val surfaceColor =
        if (darkModeEnabled) {
            Color(0xFF242424)
        } else {
            UbuntuWhite
        }

    val primaryTextColor =
        if (darkModeEnabled) {
            Color.White
        } else {
            UbuntuText
        }

    val secondaryTextColor =
        if (darkModeEnabled) {
            Color(0xFFBDBDBD)
        } else {
            UbuntuSecondary
        }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
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
                        start = 22.dp,
                        end = 22.dp,
                        top = 10.dp,
                        bottom = 8.dp
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
                            vertical = 8.dp,
                            horizontal = 2.dp
                        ),
                    color = UbuntuGreen,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )

                Box(
                    modifier = Modifier.weight(1f),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "Settings",
                        color = UbuntuGreen,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(
                    modifier = Modifier.width(42.dp)
                )
            }

            // CONTENT
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

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                // ACCOUNT
                SettingsSectionTitle(
                    title = "Account",
                    color = secondaryTextColor
                )

                Spacer(
                    modifier = Modifier.height(7.dp)
                )

                SettingsCard(
                    backgroundColor = surfaceColor
                ) {

                    SettingsNavigationRow(
                        title = "Edit Profile",
                        textColor = primaryTextColor,
                        onClick = onEditProfileClick
                    )

                    SettingsDivider()

                    SettingsNavigationRow(
                        title = "Change Password",
                        textColor = primaryTextColor,
                        onClick = onChangePasswordClick
                    )

                    SettingsDivider()

                    SettingsNavigationRow(
                        title = "Email Preferences",
                        textColor = primaryTextColor,
                        onClick = onEmailPreferencesClick
                    )

                    SettingsDivider()

                    SettingsNavigationRow(
                        title = "Phone Number",
                        textColor = primaryTextColor,
                        onClick = onPhoneNumberClick
                    )

                    SettingsDivider()

                    SettingsNavigationRow(
                        title = "Account Security",
                        textColor = primaryTextColor,
                        onClick = onAccountSecurityClick
                    )
                }

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                // NOTIFICATIONS
                SettingsSectionTitle(
                    title = "Notifications",
                    color = secondaryTextColor
                )

                Spacer(
                    modifier = Modifier.height(7.dp)
                )

                SettingsCard(
                    backgroundColor = surfaceColor
                ) {

                    SettingsSwitchRow(
                        title = "Push Notifications",
                        description = "Receive important community updates",
                        checked = pushNotificationsEnabled,
                        textColor = primaryTextColor,
                        secondaryColor = secondaryTextColor,
                        onCheckedChange = {
                            pushNotificationsEnabled = it
                            onPushNotificationsClick()
                        }
                    )

                    SettingsDivider()

                    SettingsSwitchRow(
                        title = "Email Notifications",
                        description = "Receive updates by email",
                        checked = emailNotificationsEnabled,
                        textColor = primaryTextColor,
                        secondaryColor = secondaryTextColor,
                        onCheckedChange = {
                            emailNotificationsEnabled = it
                            onEmailNotificationsClick()
                        }
                    )

                    SettingsDivider()

                    SettingsSwitchRow(
                        title = "Message Notifications",
                        description = "Receive alerts for new messages",
                        checked = messageNotificationsEnabled,
                        textColor = primaryTextColor,
                        secondaryColor = secondaryTextColor,
                        onCheckedChange = {
                            messageNotificationsEnabled = it
                            onMessageNotificationsClick()
                        }
                    )
                }

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                // PRIVACY
                SettingsSectionTitle(
                    title = "Privacy",
                    color = secondaryTextColor
                )

                Spacer(
                    modifier = Modifier.height(7.dp)
                )

                SettingsCard(
                    backgroundColor = surfaceColor
                ) {

                    SettingsNavigationRow(
                        title = "Privacy Settings",
                        textColor = primaryTextColor,
                        onClick = onPrivacySettingsClick
                    )

                    SettingsDivider()

                    SettingsNavigationRow(
                        title = "Blocked Users",
                        textColor = primaryTextColor,
                        onClick = onBlockedUsersClick
                    )

                    SettingsDivider()

                    SettingsNavigationRow(
                        title = "Hide My Activity",
                        textColor = primaryTextColor,
                        onClick = onHideActivityClick
                    )
                }

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                // APP
                SettingsSectionTitle(
                    title = "App",
                    color = secondaryTextColor
                )

                Spacer(
                    modifier = Modifier.height(7.dp)
                )

                SettingsCard(
                    backgroundColor = surfaceColor
                ) {

                    SettingsSwitchRow(
                        title = "Dark Mode",
                        description = "Change the appearance of UbuntuHub",
                        checked = darkModeEnabled,
                        textColor = primaryTextColor,
                        secondaryColor = secondaryTextColor,
                        onCheckedChange = {
                            darkModeEnabled = it
                        }
                    )

                    SettingsDivider()

                    SettingsNavigationRowWithValue(
                        title = "Language",
                        value = "English",
                        textColor = primaryTextColor,
                        onClick = onLanguageClick
                    )

                    SettingsDivider()

                    SettingsNavigationRow(
                        title = "Help & Support",
                        textColor = primaryTextColor,
                        onClick = onHelpSupportClick
                    )

                    SettingsDivider()

                    SettingsNavigationRow(
                        title = "About UbuntuHub",
                        textColor = primaryTextColor,
                        onClick = onAboutClick
                    )
                }

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                // LOGOUT
                SettingsCard(
                    backgroundColor = surfaceColor
                ) {

                    SettingsNavigationRow(
                        title = "Log Out",
                        textColor = UbuntuError,
                        arrowColor = UbuntuError,
                        onClick = {

                            val currentUser = auth.currentUser

                            if (currentUser == null) {
                                errorMessage =
                                    "No signed-in user was found."
                            } else {
                                auth.signOut()
                                errorMessage = ""
                                onLogoutClick()
                            }
                        }
                    )
                }

                if (errorMessage.isNotEmpty()) {

                    Spacer(
                        modifier = Modifier.height(10.dp)
                    )

                    Text(
                        text = errorMessage,
                        modifier = Modifier.fillMaxWidth(),
                        color = UbuntuError,
                        fontSize = 13.sp,
                        textAlign = TextAlign.Center
                    )
                }

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                Text(
                    text = "UbuntuHub",
                    modifier = Modifier.fillMaxWidth(),
                    color = UbuntuOrange,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )
            }

            // BOTTOM NAVIGATION
            SettingsBottomNavigationBar(
                onHomeClick = onHomeClick,
                onExploreClick = onExploreClick,
                onPostClick = onPostClick,
                onMessagesClick = onMessagesClick
            )
        }
    }
}

// ================================================================
// SECTION TITLE
// ================================================================

@Composable
private fun SettingsSectionTitle(
    title: String,
    color: Color
) {
    Text(
        text = title,
        color = color,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(
            horizontal = 4.dp
        )
    )
}

// ================================================================
// SETTINGS CARD
// ================================================================

@Composable
private fun SettingsCard(
    backgroundColor: Color,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(18.dp)
            )
            .border(
                width = 1.dp,
                color = UbuntuBorder,
                shape = RoundedCornerShape(18.dp)
            ),
        content = content
    )
}

// ================================================================
// NAVIGATION ROW
// ================================================================

@Composable
private fun SettingsNavigationRow(
    title: String,
    textColor: Color,
    arrowColor: Color = UbuntuGreen,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 18.dp,
                vertical = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            color = textColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = "Open",
            color = arrowColor,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

// ================================================================
// NAVIGATION ROW WITH VALUE
// ================================================================

@Composable
private fun SettingsNavigationRowWithValue(
    title: String,
    value: String,
    textColor: Color,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            }
            .padding(
                horizontal = 18.dp,
                vertical = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            modifier = Modifier.weight(1f),
            color = textColor,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium
        )

        Text(
            text = value,
            color = UbuntuSecondary,
            fontSize = 13.sp
        )
    }
}

// ================================================================
// SWITCH ROW
// ================================================================

@Composable
private fun SettingsSwitchRow(
    title: String,
    description: String,
    checked: Boolean,
    textColor: Color,
    secondaryColor: Color,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 18.dp,
                vertical = 13.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = title,
                color = textColor,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(
                modifier = Modifier.height(3.dp)
            )

            Text(
                text = description,
                color = secondaryColor,
                fontSize = 12.sp,
                lineHeight = 16.sp
            )
        }

        Spacer(
            modifier = Modifier.width(10.dp)
        )

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
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

// ================================================================
// DIVIDER
// ================================================================

@Composable
private fun SettingsDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(
            horizontal = 18.dp
        ),
        color = UbuntuBorder,
        thickness = 1.dp
    )
}

// ================================================================
// BOTTOM NAVIGATION
// ================================================================

@Composable
private fun SettingsBottomNavigationBar(
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
            .windowInsetsPadding(
                WindowInsets.navigationBars
            ),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {

        SettingsBottomNavigationItem(
            label = "Home",
            selected = false,
            onClick = onHomeClick
        )

        SettingsBottomNavigationItem(
            label = "Explore",
            selected = false,
            onClick = onExploreClick
        )

        SettingsBottomNavigationItem(
            label = "Post",
            selected = false,
            onClick = onPostClick
        )

        SettingsBottomNavigationItem(
            label = "Messages",
            selected = false,
            onClick = onMessagesClick
        )

        SettingsBottomNavigationItem(
            label = "Profile",
            selected = false,
            onClick = {}
        )
    }
}

// ================================================================
// BOTTOM NAVIGATION ITEM
// ================================================================

@Composable
private fun SettingsBottomNavigationItem(
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