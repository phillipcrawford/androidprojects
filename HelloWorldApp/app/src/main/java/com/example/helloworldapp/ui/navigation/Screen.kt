package com.example.helloworldapp.ui.navigation

sealed class Screen(val route: String) {
    object Preferences : Screen("preferddences")
    object SearchResults : Screen("searchResults")
}