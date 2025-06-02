package com.example.helloworldapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.helloworldapp.ui.navigation.Screen
import com.example.helloworldapp.ui.screens.PreferenceScreen

@Composable
fun AppNavigation(navController: NavHostController) {
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
                onUserModeClick = { /* Handle user switch */ }
            )
        }
        composable(Screen.SearchResults.route) {
            SearchResultsScreen()
        }
    }
}