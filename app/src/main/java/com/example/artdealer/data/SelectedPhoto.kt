package com.example.artdealer.data

data class SelectedPhoto(
    val id: Long,
    val frame: FrameType,
    val size: PhotoSize,
    val price: Float
)