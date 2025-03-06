package com.example.artdealer.uiscreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable


@Composable
fun ArtDealerNavGraph(startDestination: String = "home") {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("home") {
            HomeScreen(navController = navController)
        }

        composable("details") {
            DetailsScreen(navController = navController)
        }
    }
}

