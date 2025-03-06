package com.example.artdealer.data

import androidx.compose.ui.graphics.Color

enum class FrameType(val extraPrice: Float = 0f, val color: Color = Color.Yellow) {
    WOOD(0f, color = Color.Yellow),
    METAL(100f, color = Color.Gray),
    PLASTIC(30f, color = Color.Black)
}