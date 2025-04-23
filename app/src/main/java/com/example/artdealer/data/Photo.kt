package com.example.artdealer.data

data class Photo(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val artistId: Int,  // Endret fra Long til Int
    val categoryId: Int,  // Endret fra Long til Int
    val price: Float = 0.0f,
    var artist: ArtistData? = null,
    var category: Category? = null
)