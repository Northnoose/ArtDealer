package com.example.artdealer.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.artdealer.data.ArtistData
import com.example.artdealer.data.Category
import com.example.artdealer.data.FrameType
import com.example.artdealer.data.FrameWidth
import com.example.artdealer.data.Photo
import com.example.artdealer.data.PhotoSize
import com.example.artdealer.data.SelectedPhoto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ArtViewModel : ViewModel(){
    private val _uiState = MutableStateFlow(PhotoUiState())
    val uiState: StateFlow<PhotoUiState> = _uiState

    var chosenPhoto = mutableStateOf<SelectedPhoto?>(null)
    fun filterPhotosByCategory(category: Category) {
        _uiState.update { current ->
            current.copy(filteredPhotos = current.album.filter { it.category == category })
        }
    }

    fun filterPhotosByArtist(artistData: ArtistData) {
        _uiState.update { current ->
            current.copy(filteredPhotos = current.album.filter { it.artist.id == artistData.id })
        }
    }

    fun resetFilteredPhotos() {
        _uiState.update { current ->
            current.copy(filteredPhotos = current.album)
        }
    }

    fun setFrameType(frameType: FrameType) {
        _uiState.update { it.copy(selectedFrameType = frameType) }
    }

    fun setFrameWidth(frameWidth: FrameWidth) {
        _uiState.update { it.copy(selectedFrameWidth = frameWidth) }
    }

    fun setPhotoSize(size: PhotoSize) {
        _uiState.update { it.copy(selectedPhotoSize = size) }
    }

    fun calculateSelectionPrice(photo: Photo): Float {
        return photo.price +
                uiState.value.selectedFrameType.extraPrice +
                uiState.value.selectedFrameWidth.extraPrice +
                uiState.value.selectedPhotoSize.extraPrice
    }

    fun selectPhoto(
        photo: Photo,
        frame: FrameType = FrameType.WOOD,
        frameWidth: FrameWidth = FrameWidth.SMALL,
        size: PhotoSize = PhotoSize.SMALL
    ) {
        chosenPhoto.value = SelectedPhoto(
            photo = photo,
            frameType = frame,
            frameWidth = frameWidth,
            photoSize = size,
            photoPrice = calculateSelectionPrice(photo)
        )
    }

    fun addToCart() {
        chosenPhoto.value?.let { selectedPhoto ->
            _uiState.update { it.copy(shoppingCart = it.shoppingCart + selectedPhoto) }
        }
    }

    fun removeFromCart(index: Int) {
        _uiState.update { current ->
            if (index in current.shoppingCart.indices) {
                val updatedCart = current.shoppingCart.toMutableList().apply { removeAt(index) }
                current.copy(shoppingCart = updatedCart)
            } else current
        }
    }

    fun clearCart() {
        _uiState.update { it.copy(shoppingCart = emptyList()) }
    }

    val totalPrice: Float
        get() = uiState.value.shoppingCart.sumOf { selectedPhoto ->
            (selectedPhoto.photo.price +
                    selectedPhoto.frameType.extraPrice +
                    selectedPhoto.frameWidth.extraPrice +
                    selectedPhoto.photoSize.extraPrice).toDouble()
        }.toFloat()
}