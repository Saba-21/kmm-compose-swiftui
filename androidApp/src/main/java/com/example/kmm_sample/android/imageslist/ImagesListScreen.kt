package com.example.kmm_sample.android.imageslist

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.kmm_sample.ImageConverter

@ExperimentalComposeUiApi
@ExperimentalFoundationApi
@Composable
fun ImagesListScreen(
    imagesViewModel: ImagesViewModel,
    onImageClicked: (Int) -> Unit
) {
    when (val uiState = imagesViewModel.uiState.collectAsState().value) {
        ImagesUiState.Loading ->
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
            }
        is ImagesUiState.Success -> {
            Column {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(3)
                ) {
                    items(uiState.images) {
                        Image(
                            painter = rememberImagePainter(
                                ImageConverter().getUrl(it)
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .size(128.dp)
                                .padding(8.dp)
                                .clickable {
                                    println(it)
                                    onImageClicked(it.id)
                                },
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
        is ImagesUiState.Error -> {

        }
    }
}
