package com.example.artdealer.data

data class SelectedPhoto(
    val photo: Photo,              // Endret fra Long til Photo
    val frameType: FrameType,
    val frameWidth: FrameWidth,
    val photoSize: PhotoSize,
    val photoPrice: Float?        // Kan evt. fjernes og hentes direkte fra photo.price
)