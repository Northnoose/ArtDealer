package com.example.artdealer.data

import kotlinx.coroutines.flow.Flow

class ShoppingCartRepository(private val dao: ShoppingCartDao) {
    val allItems: Flow<List<SelectedPhotoEntity>> = dao.getAllItems()

    suspend fun insert(item: SelectedPhotoEntity) {
        dao.insertItem(item)
    }

    suspend fun delete(item: SelectedPhotoEntity) {
        dao.deleteItem(item)
    }

    suspend fun clearCart() {
        dao.clearCart()
    }
}


