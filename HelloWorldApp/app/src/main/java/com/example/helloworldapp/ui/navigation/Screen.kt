package com.example.helloworldapp.ui.navigation

sealed class Screen(val route: String) {
    object Preferences : Screen("preferesnfces")
    object SearchResults : Screen("searchResults")
}