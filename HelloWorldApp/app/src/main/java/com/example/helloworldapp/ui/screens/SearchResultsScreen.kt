package com.example.helloworldapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworldapp.data.AppDatabase
import com.example.helloworldapp.ui.theme.dietprefsGrey
import com.example.helloworldapp.viewmodel.SharedViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun SearchResultsScreen(
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit,
    sharedViewModel: SharedViewModel
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    val user1Prefs by sharedViewModel.user1Prefs.collectAsState()
    val user2Prefs by sharedViewModel.user2Prefs.collectAsState()
    val displayVendors by sharedViewModel.displayVendors.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    val pagedVendors by sharedViewModel.pagedVendors.collectAsState()

    // Load results on first launch
    LaunchedEffect(Unit) {
        sharedViewModel.loadAndComputeResults(AppDatabase.getDatabase(context))
    }

    Scaffold(
        topBar = {
            SearchResultsTopBar(
                user1Prefs = user1Prefs,
                user2Prefs = user2Prefs,
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
            // Table Header
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFEFEFEF))
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Vendor", fontWeight = FontWeight.Bold, modifier = Modifier.weight(2f))
                Text("Dist", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                Text("Uw1 | U2", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
            }

            // Table Content
            LazyColumn(modifier = Modifier.weight(1f)) {
                itemsIndexed(pagedVendors.filter {
                    it.vendorName.contains(searchQuery, ignoreCase = true)
                }) { index, vendor ->
                    if (index >= pagedVendors.size - 2) {
                        LaunchedEffect(index) {
                            sharedViewModel.loadNextPage()
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(vendor.vendorName, modifier = Modifier.weight(2f))
                        Text("${"%.1f".format(vendor.distanceMiles)} mi", modifier = Modifier.weight(1f))
                        Text("${vendor.user1Count} | ${vendor.user2Count}", modifier = Modifier.weight(1f))
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
                    .padding(horizontal = 16.dp, vertical = 8.dp),
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
                            val filterName = "Filter ${(row * 5) + col + 1}"
                            FilterButton(label = filterName)
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
    user1Prefs: Map<String, Boolean>,
    user2Prefs: Map<String, Boolean>,
    onBackClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val user1Color = Color(0xFFEE6C6C)
    val user2Color = Color(0xFFFF77FF)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .background(dietprefsGrey)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        IconButton(onClick = onBackClick, modifier = Modifier.align(Alignment.CenterStart)) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
        }

        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.75f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
            horizontalAlignment = Alignment.Start
        ) {
            if (user1Prefs.isNotEmpty()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Person, contentDescription = "User 1", tint = user1Color, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        user1Prefs.filterValues { it }.keys.joinToString(", "),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = user1Color,
                        maxLines = if (user2Prefs.isEmpty()) 4 else 2
                    )
                }
            }
            if (user2Prefs.isNotEmpty()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Person, contentDescription = "User 2", tint = user2Color, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        user2Prefs.filterValues { it }.keys.joinToString(", "),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = user2Color,
                        maxLines = if (user1Prefs.isEmpty()) 4 else 2
                    )
                }
            }
        }

        IconButton(onClick = onSettingsClick, modifier = Modifier.align(Alignment.CenterEnd)) {
            Icon(Icons.Default.Settings, contentDescription = "Settings", tint = Color.White, modifier = Modifier.size(32.dp))
        }
    }
}

@Composable
fun FilterButton(label: String) {
    Button(
        onClick = { /* TODO: Add filtering logic */ },
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEEEEEE)),
        modifier = Modifier
            .height(36.dp)
            .padding(horizontal = 4.dp)
    ) {
        Text(label, fontSize = 12.sp, color = Color.DarkGray)
    }
}
