package com.example.artdealer.uiscreen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: ArtViewModel
) {

    val shoppingCart by viewModel.shoppingCart.collectAsState()
    val itemCount = shoppingCart.size
    val totalPrice = viewModel.totalPrice

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.clip(RoundedCornerShape(bottomStart = 4.dp, bottomEnd = 4.dp)),
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
            BottomBar(
                shoppingCart = shoppingCart,
                itemCount = itemCount,
                totalPrice = totalPrice,
                onRemoveItem = { index -> viewModel.removeFromCart(index) },
                onRemoveAllItems = { viewModel.clearCart() }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(top = 32.dp, start = 16.dp, end = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Velg Bilde basert på",
                fontSize = 28.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .height(56.dp)
            ) {
                Text(text = "Kunstner", fontSize = 20.sp)
            }
            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .height(56.dp)
            ) {
                Text(text = "Kategori", fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun BottomBar(
    shoppingCart: List<SelectedPhoto>,
    itemCount: Int,
    totalPrice: Float,
    onRemoveItem: (Int) -> Unit,
    onRemoveAllItems: () -> Unit
) {
    if (itemCount == 0) {
        EmptyCartBar()
    } else {
        CheckoutBar(
            shoppingCart = shoppingCart,
            itemCount = itemCount,
            totalPrice = totalPrice,
            onRemoveItem = onRemoveItem,
            onRemoveAllItems = onRemoveAllItems
        )
    }
}

@Composable
fun EmptyCartBar() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shadowElevation = 8.dp,
        color = Color(0xFFFFA84E)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Ingen varer i kurven", fontSize = 18.sp, color = Color.Black)
        }
    }
}

@Composable
fun CheckoutBar(
    shoppingCart: List<SelectedPhoto>,
    itemCount: Int,
    totalPrice: Float,
    onRemoveItem: (Int) -> Unit,
    onRemoveAllItems: () -> Unit
) {
    var paymentDone by remember { mutableStateOf(false) }

    LaunchedEffect(paymentDone) {
        if (paymentDone) {
            delay(2000)
            onRemoveAllItems()
            paymentDone = false
        }
    }

    Surface(
        modifier = Modifier.fillMaxWidth(),
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


            shoppingCart.forEachIndexed { index, selected ->
                if (index > 0) {
                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 4.dp),
                        thickness = 1.dp,
                        color = Color.Gray.copy(alpha = 0.5f)
                    )
                }

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
                                    "${selected.photo.artist.name} ${selected.photo.artist.familyName}",
                            fontSize = 16.sp
                        )
                        Text(
                            text = "${selected.frameType.name}, ${selected.frameWidth.name}, ${selected.photoSize.name}",
                            fontSize = 14.sp
                        )
                        Text(
                            text = selected.photo.title,
                            fontSize = 14.sp
                        )
                    }
                    IconButton(onClick = {
                        val indexToRemove = shoppingCart.indexOf(selected)
                        if (indexToRemove != -1) onRemoveItem(indexToRemove)
                    }) {
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
                Text("Antall bilder valgt:")
                Text("$itemCount")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Totalpris:")
                Text("${totalPrice.toInt()},- Kr")
            }

            Spacer(Modifier.height(8.dp))

            val darkGreen = Color(0xFF2E7D32)
            val buttonColor by animateColorAsState(
                targetValue = if (paymentDone) darkGreen else MaterialTheme.colorScheme.primary,
                animationSpec = tween(durationMillis = 500)
            )

            Button(
                onClick = {
                    paymentDone = true
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
            ) {
                Text(
                    text = if (paymentDone) "Betaling utført" else "Betal ${totalPrice.toInt()},- Kr",
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
    }
}
