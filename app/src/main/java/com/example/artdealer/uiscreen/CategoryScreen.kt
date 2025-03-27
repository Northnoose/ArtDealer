package com.example.artdealer.uiscreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.artdealer.viewmodel.ArtViewModel
import com.example.artdealer.data.Category
import com.example.artdealer.data.Screens
import androidx.compose.ui.res.stringResource
import com.example.artdealer.R


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CategoryScreen(
    navController: NavController,
    viewModel: ArtViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.clip(RoundedCornerShape(bottomStart = 4.dp, bottomEnd = 4.dp)),
                navigationIcon = {
                    IconButton(onClick = {  navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back)
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(R.string.choose_category),
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
        content = { paddingValues ->
            CategoryList(
                modifier = Modifier.padding(paddingValues),
                viewModel = viewModel,
                navController
            )
        }
    )
}

@Composable
fun CategoryList(
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
        Category.entries.forEach { category ->
            CategoryButton(category, viewModel, navController)
        }
    }
}

@Composable
fun CategoryButton(
    category: Category,
    viewModel: ArtViewModel,
    navController: NavController
) {
    Button(
        onClick = {
            viewModel.filterPhotosByCategory(category)
            navController.navigate(Screens.Gallery.route)
        },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFA84E))
    ) {
        Text(text = category.name, color = Color.Black, fontSize = 18.sp)
    }
}
