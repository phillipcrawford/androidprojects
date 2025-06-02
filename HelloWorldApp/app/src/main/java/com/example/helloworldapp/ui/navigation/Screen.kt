package com.example.helloworldapp.ui.navigation

sealed class Screen(val route: String) {
    object Preferences : Screen("preferxhaejwsncsces")
    object SearchResults : Screen("searchResults")
}