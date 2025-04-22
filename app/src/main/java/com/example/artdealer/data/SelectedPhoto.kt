package com.example.artdealer.data

data class SelectedPhoto(
    val photo: Photo,
    val frameType: FrameType,
    val frameWidth: FrameWidth,
    val photoSize: PhotoSize,
    val photoPrice: Float?
)