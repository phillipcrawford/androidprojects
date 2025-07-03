package com.example.helloworldapp.ui.navigation

sealed class Screen(val route: String) {
    object Preferences : Screen("preferencsdes")
    object SearchResults : Screen("searchResults")
}