package com.example.artdealer.uiscreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.artdealer.CartViewModel

@Composable
fun ArtDealerNavGraph(
    cartViewModel: CartViewModel,
    startDestination: String = "details"
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        composable("home") {
            HomeScreen(navController = navController, cartViewModel = cartViewModel)
        }
        composable("details") {
            DetailsScreen(navController = navController, cartViewModel = cartViewModel)
        }
        composable("gallery") {
            val allPhotos = com.example.artdealer.data.PhotoDataSource.loadPictures()
            GalleryScreen(navController = navController, photos = allPhotos, title = "Bilder")
        }
    }
}

