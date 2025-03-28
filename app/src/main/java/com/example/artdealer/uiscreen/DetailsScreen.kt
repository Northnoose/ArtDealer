package com.example.artdealer.uiscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.artdealer.data.FrameType
import com.example.artdealer.data.FrameWidth
import com.example.artdealer.data.Photo
import com.example.artdealer.data.PhotoDataSource
import com.example.artdealer.data.PhotoSize
import com.example.artdealer.data.Screens
import com.example.artdealer.viewmodel.ArtViewModel
import kotlinx.coroutines.launch
import androidx.compose.ui.res.stringResource
import com.example.artdealer.R

// DetailsScreen.kt
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navController: NavController,
    viewModel: ArtViewModel,
    photo: Photo?
) {
    if (photo == null) {
        Text(stringResource(R.string.image_not_found), fontSize = 20.sp, color = Color.Red)
        return
    }

    val selectedFrameType by viewModel.selectedFrameType.collectAsState()
    val selectedFrameWidth by viewModel.selectedFrameWidth.collectAsState()
    val selectedPhotoSize by viewModel.selectedPhotoSize.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val itemAdded = stringResource(R.string.item_added)

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
                        text = stringResource(R.string.customize),
                        color = Color.Black,
                        fontSize = 24.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFA84E),
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        },

        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
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

            Text(stringResource(R.string.choose_frame_quality), fontSize = 18.sp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("FrameTypeOptions"),
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

            Text(stringResource(R.string.choose_frame_width), fontSize = 18.sp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("FrameWidthOptions"),
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

            Text(stringResource(R.string.choose_photo_size), fontSize = 18.sp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("PhotoSizeOptions"),
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

            val selectionPrice = viewModel.calculateSelectionPrice(photo)
            Text(
                text = stringResource(R.string.price_label) + " " + selectionPrice,
                fontSize = 18.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(65.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        viewModel.selectPhoto(
                            photo = photo,
                            frame = selectedFrameType,
                            frameWidth = selectedFrameWidth,
                            size = selectedPhotoSize,
                        )
                        viewModel.addToCart()

                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = itemAdded,
                                duration = SnackbarDuration.Short
                            )
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.add_to_cart), fontSize = 18.sp)
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { navController.navigate(Screens.Home.route) },
                    modifier = Modifier.weight(1f)
                ) {
                    Text(stringResource(R.string.home), fontSize = 18.sp)
                }
            }
        }
    }
}
