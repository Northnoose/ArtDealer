package com.example.artdealer.uiscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
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
import com.example.artdealer.CartViewModel
import com.example.artdealer.data.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navController: NavController,
    cartViewModel: CartViewModel,
    // For testing, vi henter første bilde om ingen photo ble sendt inn via navigasjon
    photo: Photo = PhotoDataSource.loadPictures().first()
) {

    var selectedFrameType by remember { mutableStateOf(FrameType.WOOD) }
    var selectedFrameWidth by remember { mutableIntStateOf(10) }
    var selectedPhotoSize by remember { mutableStateOf(PhotoSize.SMALL) }


    val singleItemPrice = photo.price +
            selectedFrameType.extraPrice +
            selectedPhotoSize.extraPrice +
            (selectedFrameWidth * 5)


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
                itemCount = cartViewModel.itemCount,
                totalPrice = cartViewModel.totalPrice,
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
                                onClick = { selectedFrameType = frameType }
                            )
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (frameType == selectedFrameType),
                            onClick = { selectedFrameType = frameType }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(frameType.name, fontSize = 16.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Text("Velg rammens bredde (mm):", fontSize = 18.sp)
            val frameWidths = listOf(10, 15, 20)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                frameWidths.forEach { width ->
                    Row(
                        modifier = Modifier
                            .selectable(
                                selected = (width == selectedFrameWidth),
                                onClick = { selectedFrameWidth = width }
                            )
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (width == selectedFrameWidth),
                            onClick = { selectedFrameWidth = width }
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
                                onClick = { selectedPhotoSize = size }
                            )
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (size == selectedPhotoSize),
                            onClick = { selectedPhotoSize = size }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(size.name, fontSize = 16.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Pris for dette valget: ${singleItemPrice.toInt()},- Kr",
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

                        val selectedItem = SelectedPhoto(
                            photo = photo,
                            frameType = selectedFrameType,
                            frameWidth = selectedFrameWidth,
                            photoSize = selectedPhotoSize,
                            finalPrice = singleItemPrice
                        )

                        cartViewModel.addSelectedPhoto(selectedItem)
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