package com.example.artdealer.uiscreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.artdealer.viewmodel.ArtViewModel

@Composable
fun ArtDealerNavGraph(
    viewModel: ArtViewModel,
    startDestination: String = "details"
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("home") {
            HomeScreen(navController = navController, viewModel = viewModel)
        }

        //composable("artist") {
        //    ArtistsScreen(navController = navController, viewModel = viewModel)
        //}

        //composable("category") {
        //    CategoryScreen(navController = navController, viewModel = viewModel)
        //}

        composable("details") {
            DetailsScreen(navController = navController, viewModel = viewModel)
        }


        composable("gallery") {
            GalleryScreen(navController = navController, viewModel = viewModel, title = "Bilder")
        }
    }
}
