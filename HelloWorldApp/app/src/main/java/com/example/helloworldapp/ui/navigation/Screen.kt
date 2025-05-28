package com.example.helloworldapp.ui.navigation

sealed class Screen(val route: String) {
    object Preferences : Screen("prefwcaerences")
    object SearchResults : Screen("searchResults")
}