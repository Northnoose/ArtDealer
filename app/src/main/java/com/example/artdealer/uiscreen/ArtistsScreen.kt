package com.example.artdealer.uiscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.artdealer.viewmodel.ArtViewModel
import com.example.artdealer.data.ArtistData
import com.example.artdealer.data.Screens
import androidx.compose.ui.res.stringResource
import com.example.artdealer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistsScreen(
    navController: NavController,
    viewModel: ArtViewModel
) {
    val uiState by viewModel.uiState.collectAsState() // Henter oppdatert tilstand fra server

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.clip(RoundedCornerShape(bottomStart = 4.dp, bottomEnd = 4.dp)),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(R.string.choose_artist),
                        color = Color.Black,
                        fontSize = 24.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFA84E),
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        ArtistList(
            artists = uiState.artists, // Nå hentes artister fra ViewModel sin server-data
            modifier = Modifier.padding(paddingValues),
            viewModel = viewModel,
            navController = navController
        )
    }
}

@Composable
fun ArtistList(
    artists: List<ArtistData>,
    modifier: Modifier = Modifier,
    viewModel: ArtViewModel,
    navController: NavController
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        artists.forEach { artist ->
            ArtistButton(artist, viewModel, navController)
        }
    }
}

@Composable
fun ArtistButton(
    artist: ArtistData,
    viewModel: ArtViewModel,
    navController: NavController
) {
    Button(
        onClick = {
            viewModel.filterPhotosByArtist(artist)
            navController.navigate(Screens.Gallery.route)
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA84E))
    ) {
        Text(text = "${artist.name} ${artist.familyName}", color = Color.Black, fontSize = 18.sp)
    }
}
