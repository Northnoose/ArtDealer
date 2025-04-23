package com.example.artdealer.viewmodel

import com.example.artdealer.data.*

data class PhotoUiState(
    val album: List<Photo> = emptyList(),
    val filteredPhotos: List<Photo> = emptyList(),
    val shoppingCart: List<SelectedPhoto> = emptyList(),
    val selectedFrameType: FrameType? = null,
    val selectedFrameWidth: FrameWidth? = null,
    val selectedPhotoSize: PhotoSize? = null,
    val artists: List<ArtistData> = emptyList(),
    val categories: List<Category> = emptyList(),
    val frameTypes: List<FrameType> = emptyList(),
    val frameWidths: List<FrameWidth> = emptyList(),
    val photoSizes: List<PhotoSize> = emptyList()
)

