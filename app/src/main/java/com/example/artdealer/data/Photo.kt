package com.example.artdealer.data

import androidx.annotation.DrawableRes

data class Photo(
    val id: Long,
    val title: String,
    val imageUrl: String,
    val artist: ArtistData,
    val category: Category,
    val price: Float = 0.0f
)