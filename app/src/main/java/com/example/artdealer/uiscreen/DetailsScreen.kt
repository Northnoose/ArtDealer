package com.example.artdealer.uiscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.artdealer.data.*
import com.example.artdealer.viewmodel.ArtViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: ArtViewModel,
    // For testing, vi henter første bilde om ingen photo ble sendt inn via navigasjon
    photo: Photo = PhotoDataSource.loadPictures().first()
) {

    val selectedFrameType by viewModel.selectedFrameType.collectAsState()
    val selectedFrameWidth by viewModel.selectedFrameWidth.collectAsState()
    val selectedPhotoSize by viewModel.selectedPhotoSize.collectAsState()

    val singleItemPrice by viewModel.finalPrice.collectAsState()
    val cartSize by viewModel.shoppingCart.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.clip(RoundedCornerShape(bottomStart = 4.dp, bottomEnd = 4.dp)),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Tilbake"
                        )
                    }
                },
                title = {
                    Text(
                        text = "Tilpass",
                        fontSize = 24.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFA84E),
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },
        bottomBar = {
            BottomBar(
                itemCount = cartSize,
                totalPrice = singleItemPrice,
                onPayClicked = { navController.navigate("home") }
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp)
        ) {

            Image(
                painter = painterResource(id = photo.imageID),
                contentDescription = photo.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))


            Text("Velg rammekvalitet:", fontSize = 18.sp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FrameType.entries.forEach { frameType ->
                    Row(
                        modifier = Modifier
                            .selectable(
                                selected = (frameType == selectedFrameType),
                                onClick = { viewModel.setFrameType(frameType) }
                            )
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (frameType == selectedFrameType),
                            onClick = { viewModel.setFrameType(frameType) }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(frameType.name, fontSize = 16.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Text("Velg rammens bredde (mm):", fontSize = 18.sp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                FrameWidth.entries.forEach { width ->
                    Row(
                        modifier = Modifier
                            .selectable(
                                selected = (width == selectedFrameWidth),
                                onClick = { viewModel.setFrameWidth(width) }
                            )
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (width == selectedFrameWidth),
                            onClick = { viewModel.setFrameWidth(width) }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("$width", fontSize = 16.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Text("Velg bildestørrelse:", fontSize = 18.sp)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                PhotoSize.entries.forEach { size ->
                    Row(
                        modifier = Modifier
                            .selectable(
                                selected = (size == selectedPhotoSize),
                                onClick = { viewModel.setPhotoSize(size) }
                            )
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (size == selectedPhotoSize),
                            onClick = { viewModel.setPhotoSize(size) }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(size.name, fontSize = 16.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Pris for dette valget: ${singleItemPrice + photo.price},- Kr",
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))


            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {

                        val selectedItem = viewModel.selectPhoto(
                            photo = photo,
                            frame = selectedFrameType,
                            frameWidth = selectedFrameWidth,
                            size = selectedPhotoSize,
                        )
                        viewModel.addToCart()
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Legg til", fontSize = 18.sp)
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { navController.navigate("home") },
                    modifier = Modifier.weight(1f)
                ) {
                    Text("Hjem", fontSize = 18.sp)
                }
            }
        }
    }
}