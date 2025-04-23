package com.example.artdealer.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_cart")
data class SelectedPhotoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val photoTitle: String,
    val artistName: String,
    val category: String,
    val frameType: String,
    val frameWidth: String,
    val photoSize: String,
    val photoPrice: Float
)


