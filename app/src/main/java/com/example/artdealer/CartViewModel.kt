package com.example.artdealer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.artdealer.data.SelectedPhoto

class CartViewModel : ViewModel() {

    private val _selectedPhotos = mutableListOf<SelectedPhoto>()
    var selectedPhotos: List<SelectedPhoto> by mutableStateOf(listOf())
        private set

    val itemCount: Int
        get() = selectedPhotos.size

    val totalPrice: Float
        get() = selectedPhotos.map { it.finalPrice }.sum()

    fun addSelectedPhoto(item: SelectedPhoto) {
        _selectedPhotos.add(item)
        selectedPhotos = _selectedPhotos.toList()
    }

    fun removeSelectedPhoto(item: SelectedPhoto) {
        _selectedPhotos.remove(item)
        selectedPhotos = _selectedPhotos.toList()
    }

    fun clearCart() {
        _selectedPhotos.clear()
        selectedPhotos = _selectedPhotos.toList()
    }
}



