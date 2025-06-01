package com.example.helloworldapp.ui.navigation

sealed class Screen(val route: String) {
    object Preferences : Screen("preferenfces")
    object SearchResults : Screen("searchResults")
}