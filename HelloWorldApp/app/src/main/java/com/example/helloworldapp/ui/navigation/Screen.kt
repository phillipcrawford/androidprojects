package com.example.helloworldapp.ui.navigation

sealed class Screen(val route: String) {
    object Preferences : Screen("prefescrences")
    object SearchResults : Screen("searchResults")
}