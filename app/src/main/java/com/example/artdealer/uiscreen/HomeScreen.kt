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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.artdealer.R
import com.example.artdealer.data.Screens
import com.example.artdealer.data.SelectedPhotoEntity
import com.example.artdealer.viewmodel.ArtViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: ArtViewModel
) {
    val shoppingCart by viewModel.shoppingCartItems.collectAsState()
    val totalPrice by viewModel.totalPrice.collectAsState()

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
                            text = stringResource(id = R.string.app_name),
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
                totalPrice = totalPrice,
                onRemoveItem = { item -> viewModel.removeFromCart(item) },
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
                text = stringResource(id = R.string.choose_image),
                fontSize = 28.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Button(
                onClick = { navController.navigate(Screens.Artist.route) },
                modifier = Modifier.fillMaxWidth(0.95f).height(56.dp)
            ) {
                Text(text = stringResource(id = R.string.artist), fontSize = 20.sp)
            }
            Button(
                onClick = { navController.navigate(Screens.Category.route) },
                modifier = Modifier.fillMaxWidth(0.95f).height(56.dp)
            ) {
                Text(text = stringResource(id = R.string.category), fontSize = 20.sp)
            }
        }
    }
}

@Composable
fun BottomBar(
    shoppingCart: List<SelectedPhotoEntity>,
    totalPrice: Float,
    onRemoveItem: (SelectedPhotoEntity) -> Unit,
    onRemoveAllItems: () -> Unit
) {
    if (shoppingCart.isEmpty()) {
        EmptyCartBar()
    } else {
        CheckoutBar(
            shoppingCart = shoppingCart,
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
            Text(stringResource(id = R.string.empty_cart), fontSize = 18.sp, color = Color.Black)
        }
    }
}

@Composable
fun CheckoutBar(
    shoppingCart: List<SelectedPhotoEntity>,
    totalPrice: Float,
    onRemoveItem: (SelectedPhotoEntity) -> Unit,
    onRemoveAllItems: () -> Unit
) {
    var paymentDone by remember { mutableStateOf(false) }
    val context = LocalContext.current

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
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text(
                text = stringResource(id = R.string.checkout),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(Modifier.height(8.dp))

            shoppingCart.forEach { selected ->
                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 4.dp),
                    thickness = 1.dp,
                    color = Color.Gray.copy(alpha = 0.5f)
                )

                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("${selected.category} | ${selected.artistName}", fontSize = 16.sp)
                        Text("${selected.frameType}, ${selected.frameWidth}, ${selected.photoSize}", fontSize = 14.sp)
                        Text(selected.photoTitle, fontSize = 14.sp)
                    }
                    IconButton(
                        onClick = { onRemoveItem(selected) },
                        modifier = Modifier.semantics {
                            contentDescription = context.getString(R.string.remove_picture)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null
                        )
                    }
                }
            }

            Spacer(Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(stringResource(id = R.string.item_count))
                Text("${shoppingCart.size}")
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(stringResource(id = R.string.pay_price))
                Text("${totalPrice.toInt()},- Kr")
            }

            Spacer(Modifier.height(8.dp))

            val darkGreen = Color(0xFF2E7D32)
            val buttonColor by animateColorAsState(
                targetValue = if (paymentDone) darkGreen else MaterialTheme.colorScheme.primary,
                animationSpec = tween(durationMillis = 500)
            )

            Button(
                onClick = { paymentDone = true },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
            ) {
                Text(
                    text = if (paymentDone)
                        stringResource(id = R.string.payment_done)
                    else
                        stringResource(id = R.string.pay_price, totalPrice.toInt()),
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
    }
}