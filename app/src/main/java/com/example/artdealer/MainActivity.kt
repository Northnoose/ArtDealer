package com.example.artdealer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.artdealer.ui.theme.ArtDealerTheme
import com.example.artdealer.uiscreen.ArtDealerNavGraph
import com.example.artdealer.viewmodel.ArtViewModel
import com.example.artdealer.viewmodel.ArtViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val appContainer = (application as ArtDealerApplication).container

        setContent {
            ArtDealerTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val viewModel: ArtViewModel = viewModel(
                        factory = ArtViewModelFactory(
                            appContainer.shoppingCartRepository,
                            appContainer.retrofitService
                        )
                    )
                    ArtDealerNavGraph(viewModel = viewModel)
                }
            }
        }
    }
}
