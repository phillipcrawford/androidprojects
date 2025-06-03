package com.example.helloworldapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworldapp.ui.viewmodel.SharedViewModel

@Composable
fun SearchResultsScreen(
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    val user1Prefs by sharedViewModel.user1Prefs.collectAsState()
    val user2Prefs by sharedViewModel.user2Prefs.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SearchResultsTopBar(
                user1PrefsSummary = user1Prefs.joinToString(", "),
                user2PrefsSummary = user2Prefs.takeIf { it.isNotEmpty() }?.joinToString(", "),
                onSettingsClick = onSettingsClick,
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            // Table of vendors
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ) {
                items(sampleVendors) { vendor ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = vendor,
                            modifier = Modifier.weight(1f),
                            fontSize = 16.sp
                        )
                        Text(text = "2.5 mi", fontSize = 14.sp, modifier = Modifier.padding(horizontal = 8.dp))
                        Text(text = "3 | 11", fontSize = 14.sp)
                    }
                }
            }

            // Search Bar
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search vendors...") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                shape = RoundedCornerShape(24.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFF0F0F0),
                    unfocusedContainerColor = Color(0xFFF0F0F0),
                    disabledContainerColor = Color(0xFFF0F0F0),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )

            // Filter Buttons
            Column(modifier = Modifier.padding(12.dp)) {
                for (row in 0 until 2) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        for (col in 0 until 5) {
                            FilterButton("Filter ${(row * 5) + col + 1}")
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun SearchResultsTopBar(
    user1PrefsSummary: String,
    user2PrefsSummary: String?,
    onSettingsClick: () -> Unit,
    onBackClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)
            .background(MaterialTheme.colorScheme.surface),
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
                Column {
                    Text(
                        text = user1PrefsSummary,
                        fontSize = 14.sp,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                    if (!user2PrefsSummary.isNullOrBlank()) {
                        Text(
                            text = user2PrefsSummary,
                            fontSize = 14.sp,
                            color = Color(0xFFB57EDC), // lavender
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            IconButton(onClick = onSettingsClick) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }
        }
    }
}

@Composable
fun FilterButton(label: String) {
    Button(
        onClick = { /* TODO: Implement filter logic */ },
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEEEEEE)),
        modifier = Modifier
            .height(36.dp)
            .padding(horizontal = 4.dp)
    ) {
        Text(label, fontSize = 12.sp, color = Color.DarkGray)
    }
}

val sampleVendors = listOf(
    "Pacific Pita", "Baja Fresh", "Fresh Grill", "It's Greek to Me",
    "Petra Grill", "Charlie Hong Kong", "Star of India", "Gold Leaf Collective",
    "Heart Ethiopia", "Chipotle"
)