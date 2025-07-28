package com.example.helloworldapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.helloworldapp.data.AppDatabase
import com.example.helloworldapp.model.Preference
import com.example.helloworldapp.ui.theme.dietprefsGrey
import com.example.helloworldapp.ui.theme.user1Red
import com.example.helloworldapp.ui.theme.user2Magenta
import com.example.helloworldapp.viewmodel.SharedViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun SearchResultsScreen(
    navController: NavController,
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

    val listState = rememberLazyListState()
    val visibleRange by sharedViewModel.visibleRange.collectAsState()
    val totalResults by sharedViewModel.totalResultsCount.collectAsState()

    // Determine user mode for results display
    val isTwoUserMode = user1Prefs.isNotEmpty() && user2Prefs.isNotEmpty()
    val isSingleUserModeWithUser1 = user1Prefs.isNotEmpty() && user2Prefs.isEmpty()
    val isSingleUserModeWithUser2 = user1Prefs.isEmpty() && user2Prefs.isNotEmpty()
    // A general single user mode flag (if either has prefs but not both)
    val isSingleUserResultsMode = (user1Prefs.isNotEmpty() || user2Prefs.isNotEmpty()) && !isTwoUserMode


    LaunchedEffect(listState.firstVisibleItemIndex, displayVendors.size) {
        val start = listState.firstVisibleItemIndex + 1
        val end = (start + listState.layoutInfo.visibleItemsInfo.size - 1).coerceAtMost(displayVendors.size)
        sharedViewModel.updateVisibleRange(start, end)
    }

    // Load results on first launch
    LaunchedEffect(Unit) {
        sharedViewModel.loadAndComputeResults(AppDatabase.getDatabase(context))
    }

    Scaffold(
        topBar = {
            SearchResultsTopBar(
                user1Prefs = user1Prefs,
                user2Prefs = user2Prefs,
                navController = navController,
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
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .defaultMinSize(minHeight = if (isTwoUserMode) 56.dp else Dp.Unspecified), // T,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(2f)) {
                    Text(
                        text = "Vendor",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterStart)
                    )
                    if (totalResults > 0) {
                        Text(
                            text = "${visibleRange.first}–${visibleRange.second} of $totalResults",
                            fontWeight = FontWeight.Normal,
                            fontSize = 12.sp,
                            color = Color.DarkGray,
                            modifier = Modifier
                                .align(Alignment.CenterEnd)
                                .padding(end = 8.dp)
                        )
                    }
                }
                Text("Dist", fontWeight = FontWeight.Bold, modifier = Modifier.weight(1f))
                // Conditional Menu Items Header
                if (isTwoUserMode) {
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally // Center the icons and text
                    ) {
                        Text(
                            "Menu Items", // Or "U1 | U2" if you prefer that text
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center // Ensure text is centered
                        )
                        Spacer(modifier = Modifier.height(2.dp)) // Small space
                        Row {
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "User 1 Items",
                                tint = user1Red, // Light Red for User 1
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Icon(
                                imageVector = Icons.Filled.Person,
                                contentDescription = "User 2 Items",
                                tint = user2Magenta, // Magenta for User 2
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                } else { // Single user mode or no users with preferences (though no users means no results typically)
                    Text(
                        "Menu Items",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                }
            }

//            if (totalResults > 0) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .background(Color(0xFFF7F7F7))
//                        .padding(horizontal = 16.dp, vertical = 4.dp),
//                    horizontalArrangement = Arrangement.Center
//                ) {
//                    Text(
//                        text = "${visibleRange.first}–${visibleRange.second} of $totalResults",
//                        fontWeight = FontWeight.SemiBold,
//                        fontSize = 14.sp,
//                        color = Color.Gray
//                    )
//                }
//            }


            // Table Content
            LazyColumn(modifier = Modifier.weight(1f), state = listState) {
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
                        // Conditional results display
                        val menuItemsText = when {
                            isTwoUserMode -> "${vendor.user1Count} | ${vendor.user2Count}"
                            isSingleUserModeWithUser1 -> "${vendor.user1Count}"
                            isSingleUserModeWithUser2 -> "${vendor.user2Count}"
                            // Fallback if somehow no prefs but vendors are shown (e.g. initial state before computation)
                            // or if only one user has prefs, show their count.
                            user1Prefs.isNotEmpty() -> "${vendor.user1Count}"
                            user2Prefs.isNotEmpty() -> "${vendor.user2Count}"
                            else -> "N/A" // Should ideally not happen if filtering is correct
                        }
                        Text(menuItemsText, modifier = Modifier.weight(1f))
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
    user1Prefs: Set<Preference>,
    user2Prefs: Set<Preference>,
    navController: NavController,
    onSettingsClick: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var isBackEnabled by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(128.dp)
            .background(dietprefsGrey)
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        IconButton(
            onClick = {
                if (isBackEnabled) {
                    isBackEnabled = false
                    coroutineScope.launch {
                        navController.popBackStack()
                        delay(500)
                        isBackEnabled = true
                    }
            }
        },
            modifier = Modifier.align(Alignment.CenterStart)
        ) {
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
                    Icon(Icons.Default.Person, contentDescription = "User 1", tint = user1Red, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        user1Prefs.joinToString(", ") { it.display },
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = user1Red,
                        maxLines = if (user2Prefs.isEmpty()) 4 else 2
                    )
                }
            }
            if (user2Prefs.isNotEmpty()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Person, contentDescription = "User 2", tint = user2Magenta, modifier = Modifier.size(20.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        user2Prefs.joinToString(", ") { it.display },
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = user2Magenta,
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
