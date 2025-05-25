package com.example.helloworldapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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

@Composable
fun PreferenceScreen(
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit,
    onUserModeClick: () -> Unit
) {
    val allPreferences = listOf(
        "vegetarian", "pescetariacaedfn", "vegan", "keto", "organic", "gmo-free",
        "locally sourced", "raw", "entree", "sweet", "Kosher", "Halal",
        "beef", "chicken", "bacon/pork/ham", "seafood",
        "low sugar", "high protein", "low carb", "no alliums",
        "no pork products", "no red meat", "no msg", "no sesame",
        "no milk", "no eggs", "no fish", "no shellfish",
        "no peanuts", "no treenuts", "gluten-free", "no soy"
    )
    val selected = remember { mutableStateMapOf<String, Boolean>() }

    Scaffold(
        topBar = { PreferencesTopBar(onSettingsClick = onSettingsClick) },
        bottomBar = {
            BottomAppBar(
                containerColor = Color.DarkGray
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
                    onClick = onUserModeClick,
                    modifier = Modifier.padding(8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB35100))
                ) {
                    Text("C", color = Color.White)
                }
            }
        }
    ) { padding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 8.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(allPreferences) { pref ->
                val isSelected = selected[pref] ?: false
                Box(
                    modifier = Modifier
                        .background(
                            if (isSelected) Color(0xFF005B5B) else Color.DarkGray,
                            shape = RoundedCornerShape(4.dp)
                        )
                        .clickable { selected[pref] = !isSelected }
                        .padding(12.dp)
                ) {
                    Text(
                        text = pref,
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}
@Composable
fun PreferencesTopBar(onSettingsClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.DarkGray)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Preferences",
            color = Color(0xFFEE6C6C),
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