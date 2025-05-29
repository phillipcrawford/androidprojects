package com.example.helloworldapp.ui.navigation

sealed class Screen(val route: String) {
    object Preferences : Screen("prefexcrqences")
    object SearchResults : Screen("searchResults")
}