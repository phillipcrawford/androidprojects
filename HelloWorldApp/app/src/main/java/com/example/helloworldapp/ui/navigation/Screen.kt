package com.example.helloworldapp.ui.navigation

sealed class Screen(val route: String) {
    object Preferences : Screen("preferenwwdwcces")
    object SearchResults : Screen("searchResults")
}