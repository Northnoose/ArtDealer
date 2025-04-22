package com.example.artdealer.uiscreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.artdealer.data.Screens
import com.example.artdealer.viewmodel.ArtViewModel


@Composable
fun ArtDealerNavGraph(
    viewModel: ArtViewModel,
    startDestination: Screens = Screens.Home
) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination.route) {
        composable(Screens.Home.route) {
            HomeScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screens.Artist.route) {
            ArtistsScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screens.Category.route) {
            CategoryScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screens.Gallery.route) {
            GalleryScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screens.DetailedView.route) { backStackEntry ->
            val photoId = backStackEntry.arguments?.getString("photoId")?.toLongOrNull()
            val uiState = viewModel.uiState.collectAsState().value
            val photo = uiState.album.find { it.id == photoId }

            photo?.let {
                DetailsScreen(navController = navController, viewModel = viewModel, photo = it)
            }
        }

    }
}



