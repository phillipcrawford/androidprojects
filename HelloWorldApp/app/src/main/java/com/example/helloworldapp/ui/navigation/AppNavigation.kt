package com.example.helloworldapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.helloworldapp.ui.screens.PreferenceScreen
import com.example.helloworldapp.ui.screens.SearchResultsScreen
import com.example.helloworldapp.viewmodel.SharedViewModel

@Composable
fun AppNavigation(sharedViewModel: SharedViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Preferences.route
    ) {
        composable(Screen.Preferences.route) {
            PreferenceScreen(
                onSearchClick = {
                    navController.navigate(Screen.SearchResults.route)
                },
                onSettingsClick = { /* Handle settings */ },
                onUserModeClick = { /* Handle user switch */ },
                sharedViewModel = sharedViewModel
            )
        }
        composable(Screen.SearchResults.route) {
            SearchResultsScreen(
                onBackClick = { navController.popBackStack() },
                onSettingsClick = { /* handle */ },
                sharedViewModel = sharedViewModel
            )
        }
    }
}