package com.example.helloworldapp.ui.navigation

sealed class Screen(val route: String) {
    object Preferences : Screen("preaferences")
    object SearchResults : Screen("searchResults")
}