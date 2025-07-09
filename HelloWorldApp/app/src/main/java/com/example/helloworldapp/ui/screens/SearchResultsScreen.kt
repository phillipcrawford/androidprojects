// SearchResultsScreen.kt
package com.example.helloworldapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworldapp.ui.theme.dietprefsGrey
import com.example.helloworldapp.ui.viewmodel.SharedViewModel

@Composable
fun SearchResultsScreen(
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SearchResultsTopBar(
                sharedViewModel = sharedViewModel,
                onBackClick = onBackClick,
                onSettingsClick = onSettingsClick
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
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
                        Text(
                            text = "2.5 mi",
                            fontSize = 14.sp,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                        Text(text = "3 | 11", fontSize = 14.sp)
                    }
                }
            }

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
    sharedViewModel: SharedViewModel,
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val user1Prefs by sharedViewModel.user1Prefs.collectAsState()
    val user2Prefs by sharedViewModel.user2Prefs.collectAsState()

    val user1Color = Color(0xFFEE6C6C)
    val user2Color = Color(0xFFFF77FF)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .background(dietprefsGrey)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        // Back arrow
        IconButton(
            onClick = onBackClick,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
            )
        }

        // Center content: preferences
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.75f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            if (user1Prefs.isNotEmpty()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "User 1",
                        tint = user1Color,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = user1Prefs.filterValues { it }.keys.joinToString(", "),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = user1Color,
                        maxLines = if (user2Prefs.isEmpty()) 4 else 2
                    )
                }
            }
            if (user2Prefs.isNotEmpty()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "User 2",
                        tint = user2Color,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = user2Prefs.filterValues { it }.keys.joinToString(", "),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = user2Color,
                        maxLines = if (user1Prefs.isEmpty()) 4 else 2
                    )
                }
            }
        }

        // Settings icon
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

@Composable
fun FilterButton(label: String) {
    Button(
        onClick = { /* TODO */ },
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
    "Pacific Pita", "Baja Fresh", "Fresh Grill", "It's Greekewdqd to Me",
    "Petra Grill", "Charlie Hong Kong", "Star of India", "Gold Leaf Collectives",
    "Heart Ethiopia", "Chipotle"
)