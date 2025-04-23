package com.example.artdealer.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_cart")
data class ShoppingCartItem(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val photoId: Long,
    val frameType: String,
    val frameWidth: String,
    val photoSize: String,
    val price: Float
)


