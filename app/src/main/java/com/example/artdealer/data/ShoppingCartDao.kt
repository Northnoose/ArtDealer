package com.example.artdealer.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingCartDao {
    @Query("SELECT * FROM shopping_cart")
    fun getAllItems(): Flow<List<SelectedPhotoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: SelectedPhotoEntity)

    @Delete
    suspend fun deleteItem(item: SelectedPhotoEntity)

    @Query("DELETE FROM shopping_cart")
    suspend fun clearCart()
}
