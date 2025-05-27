package com.example.helloworldapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle

import com.example.helloworldapp.ui.theme.dietprefsGrey
import com.example.helloworldapp.ui.theme.dietprefsTeal
import com.example.helloworldapp.ui.theme.selectedGrey
import com.example.helloworldapp.ui.theme.selectedTeal

@Composable
fun PreferenceScreen(
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onUserModeClick: () -> Unit
) {
    val preferences = listOf(
        "vegetarian", "pescetarian", "vegan", "keto", "organic", "gmo-free",
        "locally sourced", "raw", "entree", "sweet", "Kosher", "Halal",
        "beef", "chicken", "bacon/pork/ham", "seafood",
        "low sugar", "high protein", "low carb", "no alliums",
        "no pork products", "no red meat", "no msg", "no sesame",
        "no milk", "no eggs", "no fish", "no shellfish",
        "no peanuts", "no treenuts", "gluten-free", "no soy"
    )

    val user1Prefs = remember { mutableStateMapOf<String, Boolean>() }
    val user2Prefs = remember { mutableStateMapOf<String, Boolean>() }
    val isUser2Active = remember { mutableStateOf(false) }

    val user1Selected = user1Prefs.filterValues { it }.keys.toList()
    val user2Selected = user2Prefs.filterValues { it }.keys.toList()

    val title = buildAnnotatedString {
        if (user1Selected.isNotEmpty()) {
            withStyle(style = SpanStyle(color = Color(0xFFEE6C6C))) {
                append(user1Selected.joinToString(" & "))
            }
        }
        if (user1Selected.isNotEmpty() && user2Selected.isNotEmpty()) {
            append(" & ")
        }
        if (user2Selected.isNotEmpty()) {
            withStyle(style = SpanStyle(color = Color.Magenta)) {
                append(user2Selected.joinToString(" & "))
            }
        }
        if (user1Selected.isEmpty() && user2Selected.isEmpty()) {
            append("Preferences")
        }
    }

    Scaffold(
        topBar = {
            PreferencesTopBar(title = title, onSettingsClick = onSettingsClick)
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
                        .padding(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent)
                ) {
                    Text("Search", color = Color(0xFFEE6C6C), fontSize = 20.sp)
                }
                Button(
                    onClick = {
                        user1Prefs.clear()
                        user2Prefs.clear()
                        isUser2Active.value = false
                    },
                    modifier = Modifier
                        .padding(8.dp),
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
                val rows = preferences.chunked(2).take(16)

                rows.forEachIndexed { rowIndex, rowPrefs ->
                    val isTeal = rowIndex >= 8
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        rowPrefs.forEach { pref ->
                            val activePrefs = if (isUser2Active.value) user2Prefs else user1Prefs
                            val isSelected = activePrefs[pref] ?: false
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
                                    .background(
                                        bgColor,
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .clickable {
                                        activePrefs[pref] = !(activePrefs[pref] ?: false)
                                    }
                                    .padding(12.dp, 0.dp, 0.dp, 0.dp),
                                contentAlignment = Alignment.CenterStart
                            ) {
                                Text(
                                    text = pref,
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

                // Final row: low price + person toggle
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val activePrefs = if (isUser2Active.value) user2Prefs else user1Prefs
                    val lowPricePref = "low price"
                    val isLowPriceSelected = activePrefs[lowPricePref] ?: false

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .background(
                                if (isLowPriceSelected) selectedGrey else dietprefsGrey,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .clickable {
                                activePrefs[lowPricePref] = !isLowPriceSelected
                            }
                            .padding(12.dp, 0.dp, 0.dp, 0.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = lowPricePref,
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
                            .clickable {
                                isUser2Active.value = !isUser2Active.value
                            }
                            .padding(0.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = "Toggle Person",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PreferencesTopBar(title: AnnotatedString, onSettingsClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(dietprefsGrey)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        IconButton(onClick = onSettingsClick) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = Color.White
            )
        }
    }
}