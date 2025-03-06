package com.example.artdealer.data

import androidx.annotation.DrawableRes

data class Photo(
    val id: Long,
    val title: String,
    @DrawableRes val imageID: Int,
    val artist: ArtistData,
    val category: Category,
    val price: Float = 0.0f
)