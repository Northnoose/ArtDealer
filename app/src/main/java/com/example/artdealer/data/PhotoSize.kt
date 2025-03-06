package com.example.artdealer.data

enum class PhotoSize(val extraPrice: Float = 0f, val size: Int = 170) {
    SMALL(extraPrice = 0f, size = 170),
    MEDIUM(extraPrice = 130f, size = 200),
    LARGE(extraPrice = 230f, size = 250)
}