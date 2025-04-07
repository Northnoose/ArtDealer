package com.example.artdealer.viewmodel

import com.example.artdealer.data.FrameType
import com.example.artdealer.data.FrameWidth
import com.example.artdealer.data.Photo
import com.example.artdealer.data.PhotoDataSource.loadPictures
import com.example.artdealer.data.PhotoSize
import com.example.artdealer.data.SelectedPhoto

data class PhotoUiState(
    val album: List<Photo> = loadPictures(),
    val filteredPhotos: List<Photo> = loadPictures(),
    val shoppingCart: List<SelectedPhoto> = emptyList(),
    val selectedFrameType: FrameType = FrameType.WOOD,
    val selectedFrameWidth: FrameWidth = FrameWidth.SMALL,
    val selectedPhotoSize: PhotoSize = PhotoSize.SMALL
)