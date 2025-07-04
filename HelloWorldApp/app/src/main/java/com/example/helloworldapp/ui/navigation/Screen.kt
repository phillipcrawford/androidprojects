package com.example.helloworldapp.ui.navigation

sealed class Screen(val route: String) {
    object Preferences : Screen("preferddddences")
    object SearchResults : Screen("searchResults")
}