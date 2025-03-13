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
            HomeScreen(navController = navController, cartViewModel = viewModel)
        }
        composable("home") {
            ArtistsScreen(navController = navController, cartViewModel = viewModel)
        }
        composable("details") {
            DetailsScreen(navController = navController, cartViewModel = viewModel)
        }
        composable("gallery") {
            val allPhotos = com.example.artdealer.data.PhotoDataSource.loadPictures()
            GalleryScreen(navController = navController, photos = allPhotos, title = "Bilder")
        }
    }
}

