package com.example.artdealer.uiscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun ArtistsScreen() {
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
                            fontSize = 30.sp
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFFFA84E),
                    titleContentColor = Color.Black
                )
            )
        },
        modifier = TODO(),
        bottomBar = TODO(),
        snackbarHost = TODO(),
        floatingActionButton = TODO(),
        floatingActionButtonPosition = TODO(),
        containerColor = TODO(),
        contentColor = TODO(),
        contentWindowInsets = TODO(),
        content = TODO()
    )
}