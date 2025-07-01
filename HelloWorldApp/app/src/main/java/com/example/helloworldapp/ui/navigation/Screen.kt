package com.example.helloworldapp.ui.navigation

sealed class Screen(val route: String) {
    object Preferences : Screen("preferenceeees")
    object SearchResults : Screen("searchResults")
}