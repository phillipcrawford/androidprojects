package com.example.helloworldapp.ui.navigation

sealed class Screen(val route: String) {
    object Preferences : Screen("prefexrences")
    object SearchResults : Screen("searchResults")
}