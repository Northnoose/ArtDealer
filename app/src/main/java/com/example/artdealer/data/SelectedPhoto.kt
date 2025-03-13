
package com.example.artdealer.data

data class SelectedPhoto(
    val photo: Photo,
    val frameType: FrameType,
    val frameWidth: Int,
    val photoSize: PhotoSize,
    val finalPrice: Float
)
