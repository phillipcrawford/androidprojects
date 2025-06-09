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
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworldapp.ui.viewmodel.SharedViewModel

// Replace with your real app's grey if needed
val dietprefsGrey = Color(0xFF555555)

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
            // Vendor List
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
    sharedViewModel: SharedViewModel,
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val user1Prefs by sharedViewModel.user1Prefs.collectAsState()
    val user2Prefs by sharedViewModel.user2Prefs.collectAsState()

    val title = buildAnnotatedString {
        append(user1Prefs.joinToString(", "))
        if (user2Prefs.isNotEmpty()) {
            append("\n")
            append(user2Prefs.joinToString(", "))
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .background(dietprefsGrey)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                maxLines = 2
            )
        }

        IconButton(
            onClick = onSettingsClick,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",
                tint = Color.White
            )
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
    "Petra Grill", "Charlie Hong Kong", "Star of India", "Gold Leaf Collfdecjrtogihpves",
    "Heart Ethiopia", "Chipotle"
)