package com.example.artdealer.uiscreen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.artdealer.data.SelectedPhoto
import com.example.artdealer.viewmodel.ArtViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: ArtViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.clip(
                    RoundedCornerShape(bottomStart = 4.dp, bottomEnd = 4.dp)
                ),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Kunsthandler",
                            color = Color.Black,
                            fontSize = 30.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFffa84e),
                    titleContentColor = Color.Black
                )
            )
        },
        bottomBar = {
            if (viewModel.itemCount == 0) {

                BottomBar(
                    itemCount = 0,
                    totalPrice = 0f,
                    onPayClicked = {

                    }
                )
            } else {

                CheckoutBar(
                    viewModel = ArtViewModel()
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Velg Bilde basert på",
                fontSize = 28.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {

                }) {
                    Text(text = "Kunstner", fontSize = 20.sp)
                }
                Button(onClick = {

                }) {
                    Text(text = "Kategori", fontSize = 20.sp)
                }
            }
        }
    }
}



@Composable
fun BottomBar(
    itemCount: List<SelectedPhoto>,
    totalPrice: Float,
    onPayClicked: () -> Unit
) {

    val formattedPrice = "${totalPrice.toInt()},- Kr"
    val buttonText = if (itemCount == 0) {
        "Ingen varer i kurven"
    } else {
        "Betal $formattedPrice"
    }

    Surface(
        modifier = Modifier
            .clip(

                RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
            ),
        shadowElevation = 8.dp,

        color = Color(0xFFFFA84E).copy(alpha = 1f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Antall bilder valgt:",
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = "$itemCount",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Totalpris:",
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = formattedPrice,
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }
            Button(
                onClick = onPayClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                enabled = itemCount > 0
            ) {
                Text(
                    text = buttonText,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun CheckoutBar(
    viewModel: ArtViewModel
) {
    val itemCount = viewModel.itemCount
    val totalPrice = viewModel.totalPrice

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp)
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
        shadowElevation = 8.dp,
        color = Color(0xFFFFA84E)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {

            Text(
                text = "CHECKOUT",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(8.dp))


            viewModel.selectedPhotos.forEach { selected ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            text = "${selected.photo.category} | " +
                                    "${selected.photo.artist.name} " +
                                    selected.photo.artist.familyName,
                            fontSize = 16.sp,
                            color = Color.Black
                        )

                        Text(
                            text = "${selected.frameType.name}, " +
                                    "${selected.frameWidth} mm, " +
                                    selected.photoSize.name,
                            fontSize = 16.sp,
                            color = Color.Black
                        )

                        Text(
                            text = selected.photo.title,
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                    }
                    IconButton(onClick = { viewModel.removeSelectedPhoto(selected) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Fjern bilde"
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Antall bilder valgt:",
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = "$itemCount",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Totalpris:",
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = "${totalPrice.toInt()},- Kr",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            Spacer(Modifier.height(8.dp))

            Button(
                onClick = {
                    viewModel.clearCart()

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Betal ${totalPrice.toInt()},- Kr", fontSize = 18.sp)
            }
        }
    }
}