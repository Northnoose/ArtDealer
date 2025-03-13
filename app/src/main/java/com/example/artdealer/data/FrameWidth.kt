package com.example.artdealer.data

enum class FrameWidth(val extraPrice: Float = 0f, val size: Int = 170) {
    SMALL(extraPrice = 0f, size = 10),
    MEDIUM(extraPrice = 130f, size = 15),
    LARGE(extraPrice = 230f, size = 20)
}