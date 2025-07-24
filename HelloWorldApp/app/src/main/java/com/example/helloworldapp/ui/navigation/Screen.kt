package com.example.helloworldapp.ui.navigation

sealed class Screen(val route: String) {
    object Preferences : Screen("prefwewerences")
    object SearchResults : Screen("searchResults")
}