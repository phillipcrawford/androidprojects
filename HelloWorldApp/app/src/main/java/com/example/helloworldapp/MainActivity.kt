package com.example.helloworldapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.helloworldapp.ui.screens.PreferenceScreen
import com.example.helloworldapp.ui.screens.SearchResultsScreen
import com.example.helloworldapp.viewmodel.SharedViewModel
import com.example.helloworldapp.ui.theme.HelloWorldAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Only one instance of SharedViewModel tied to this Activity
        val sharedViewModel: SharedViewModel by viewModels()

        setContent {
            HelloWorldAppTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController, sharedViewModel = sharedViewModel)
            }
        }
    }
}

@Composable
fun AppNavHost(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    NavHost(
        navController = navController,
        startDestination = "PreferenceScreen"
    ) {
        composable("PreferenceScreen") {
            PreferenceScreen(
                onSearchClick = { navController.navigate("SearchResultsScreen") },
                onSettingsClick = { /* TODO: Settings */ },
                onUserModeClick = { /* TODO: toggle user mode */ },
                sharedViewModel = sharedViewModel
            )
        }
        composable("SearchResultsScreen") {
            SearchResultsScreen(
                onBackClick = { navController.popBackStack() },
                onSettingsClick = { /* TODO: Settings */ },
                sharedViewModel = sharedViewModel
            )
        }
    }
}