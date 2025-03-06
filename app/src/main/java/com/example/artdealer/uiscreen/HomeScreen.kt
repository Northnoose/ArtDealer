package com.example.artdealer.uiscreen


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        // Topplinje med rundede nederste hjørner og større font
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .clip(
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
                            fontSize = 30.sp  //
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFFFFA84E),
                    titleContentColor = Color.Black
                )
            )
        },

        bottomBar = {
            BottomBar(
                itemCount = 0,
                totalPrice = 0f,
                onPayClicked = { /* TODO: Naviger til betal-skjerm */ }
            )
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
                    // TODO: Naviger til kunstner-liste
                }) {
                    Text(
                        text = "Kunstner",
                        fontSize = 20.sp
                    )
                }
                Button(onClick = {
                    // TODO: Naviger til kategori-liste
                }) {
                    Text(
                        text = "Kategori",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}



@Composable
fun BottomBar(
    itemCount: Int,
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
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
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
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.SpaceBetween
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