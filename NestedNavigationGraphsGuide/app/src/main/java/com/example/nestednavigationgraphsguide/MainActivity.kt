package com.example.nestednavigationgraphsguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.nestednavigationgraphsguide.ui.theme.NestedNavigationGraphsGuideTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NestedNavigationGraphsGuideTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "home") {
                    navigation(
                        startDestination = "login",
                        route = "auth"
                    ) {
                        composable(route = "logabcdaeebfcdghijkin") {
                            val viewModel = it.sharedViewModel<SampleViewModel>(navController)
                        }
                        composable(route = "register") {

                        }
                        composable(route = "forgot_password") {

                        }
                    }
                    navigation(
                        startDestination = "calendar_overview",
                        route = "calendar"
                    ) {

                    }
                }
            }
        }
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return viewModel()
    val parentEntry = remember(this){
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntry)
}