package com.example.helloworldapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworldapp.model.Preference
import com.example.helloworldapp.ui.theme.dietprefsGrey
import com.example.helloworldapp.ui.theme.dietprefsTeal
import com.example.helloworldapp.ui.theme.selectedGrey
import com.example.helloworldapp.ui.theme.selectedTeal
import com.example.helloworldapp.ui.theme.user1Red
import com.example.helloworldapp.ui.theme.user2Magenta
import com.example.helloworldapp.viewmodel.SharedViewModel

@Composable
fun PreferenceScreen(
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onUserModeClick: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    val user1Prefs by sharedViewModel.user1Prefs.collectAsState()
    val user2Prefs by sharedViewModel.user2Prefs.collectAsState()
    val isUser2Active = remember { mutableStateOf(false) }

    val user1Selected = user1Prefs.map { it.display }
    val user2Selected = user2Prefs.map { it.display }

    Scaffold(
        topBar = {
            PreferencesTopBar(
                user1Selected = user1Selected,
                user2Selected = user2Selected,
                onSettingsClick = onSettingsClick,
                onUserModeClick = onUserModeClick
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(dietprefsGrey)
                    .padding(4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onSearchClick,
                    modifier = Modifier
                        .weight(1f)
                        .padding(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text("Search", color = user1Red, fontSize = 32.sp)
                }
                Button(
                    onClick = {
                        sharedViewModel.clearPrefs()
                        isUser2Active.value = false
                    },
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB35100))
                ) {
                    Text("C", color = Color.White, fontSize = 20.sp)
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(Color(0xFF2C2C2C))
                .padding(0.dp, 4.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                val rows = Preference.orderedForUI.take(32).chunked(2).take(16)

                rows.forEachIndexed { rowIndex, rowPrefs ->
                    val isTeal = rowIndex >= 8
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        rowPrefs.forEach { pref ->
                            val isSelected = if (isUser2Active.value)
                                user2Prefs.contains(pref)
                            else
                                user1Prefs.contains(pref)

                            val bgColor = when {
                                isTeal && isSelected -> selectedTeal
                                isTeal && !isSelected -> dietprefsTeal
                                isSelected -> selectedGrey
                                else -> dietprefsGrey
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .background(bgColor, shape = RoundedCornerShape(4.dp))
                                    .clickable {
                                        if (isUser2Active.value) {
                                            sharedViewModel.toggleUser2Pref(pref)
                                        } else {
                                            sharedViewModel.toggleUser1Pref(pref)
                                        }
                                    }
                                    .padding(start = 12.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = pref.display,
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                            }
                        }
                        if (rowPrefs.size == 1) {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }

                // Last row with "low price" and user toggle
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val lowPricePref = Preference.LOW_PRICE
                    val isLowPriceSelected = if (isUser2Active.value)
                        user2Prefs.contains(lowPricePref)
                    else
                        user1Prefs.contains(lowPricePref)

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(
                                if (isLowPriceSelected) selectedGrey else dietprefsGrey,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .clickable {
                                if (isUser2Active.value) {
                                    sharedViewModel.toggleUser2Pref(lowPricePref)
                                } else {
                                    sharedViewModel.toggleUser1Pref(lowPricePref)
                                }
                            }
                            .padding(start = 12.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = lowPricePref.display,
                            color = Color.White,
                            fontSize = 16.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(
                                if (isUser2Active.value) selectedGrey else dietprefsGrey,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .clickable(enabled = user1Selected.isNotEmpty() || user2Selected.isNotEmpty() || isUser2Active.value) {
                                if (user1Selected.isEmpty() && user2Selected.isEmpty()) {
                                    isUser2Active.value = false
                                } else {
                                    isUser2Active.value = !isUser2Active.value
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add",
                                tint = Color.White
                            )
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Person",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PreferencesTopBar(
    user1Selected: List<String>,
    user2Selected: List<String>,
    onSettingsClick: () -> Unit,
    onUserModeClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .background(dietprefsGrey)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        if (user1Selected.isEmpty() && user2Selected.isEmpty()) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    modifier = Modifier
                        .size(52.dp)
                        .alpha(0f),
                    tint = Color.Transparent
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Preferences",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = user1Red,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f)
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .fillMaxWidth(0.85f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                if (user1Selected.isNotEmpty()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = onUserModeClick) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "User 1",
                                tint = user1Red
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = user1Selected.joinToString(", "),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = user1Red,
                            maxLines = if (user2Selected.isEmpty()) 4 else 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                if (user2Selected.isNotEmpty()) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = onUserModeClick) {
                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "User 2",
                                tint = user2Magenta
                            )
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = user2Selected.joinToString(", "),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = user2Magenta,
                            maxLines = if (user1Selected.isEmpty()) 4 else 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }

        IconButton(
            onClick = onSettingsClick,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}
