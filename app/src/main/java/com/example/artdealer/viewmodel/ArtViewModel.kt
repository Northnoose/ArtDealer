package com.example.artdealer.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.artdealer.data.ArtistData
import com.example.artdealer.data.ArtistDataSource.loadArtists
import com.example.artdealer.data.Category
import com.example.artdealer.data.FrameType
import com.example.artdealer.data.FrameWidth
import com.example.artdealer.data.Photo
import com.example.artdealer.data.PhotoDataSource.loadPictures
import com.example.artdealer.data.PhotoSize
import com.example.artdealer.data.Screens
import com.example.artdealer.data.SelectedPhoto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ArtViewModel : ViewModel(){
    private val _album = MutableStateFlow(loadPictures())
    val album: StateFlow<List<Photo>> = _album

    private val _filteredPhotos = MutableStateFlow(loadPictures())
    val filteredPhotos: StateFlow<List<Photo>> = _filteredPhotos

    private val _shoppingCart = MutableStateFlow<List<SelectedPhoto>>(emptyList())
    val shoppingCart: StateFlow<List<SelectedPhoto>> = _shoppingCart

    private val _selectedFrameType = MutableStateFlow(FrameType.WOOD)
    val selectedFrameType: StateFlow<FrameType> = _selectedFrameType

    private val _selectedFrameWidth = MutableStateFlow(FrameWidth.SMALL)
    val selectedFrameWidth: StateFlow<FrameWidth> = _selectedFrameWidth

    private val _selectedPhotoSize = MutableStateFlow(PhotoSize.SMALL)
    val selectedPhotoSize: StateFlow<PhotoSize> = _selectedPhotoSize

    var chosenPhoto = mutableStateOf<SelectedPhoto?>(null)

    fun filterPhotosByCategory(category: Category) {
        val filteredList = _album.value.filter { it.category == category }
        _filteredPhotos.value = filteredList.toList()
    }

    fun filterPhotosByArtist(artistData: ArtistData) {
        val filteredList = _album.value.filter { it.artist.id == artistData.id }
        _filteredPhotos.value = filteredList.toList()
    }

    fun resetFilteredPhotos() {
        _filteredPhotos.value = _album.value
    }

    fun setFrameType(frameType: FrameType) {
        _selectedFrameType.value = frameType
    }

    fun setFrameWidth(frameWidth: FrameWidth) {
        _selectedFrameWidth.value = frameWidth
    }

    fun setPhotoSize(size: PhotoSize) {
        _selectedPhotoSize.value = size
    }

    fun calculateSelectionPrice(photo: Photo): Float {
        return photo.price +
                _selectedFrameType.value.extraPrice +
                _selectedFrameWidth.value.extraPrice +
                _selectedPhotoSize.value.extraPrice
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
            _shoppingCart.update { it + selectedPhoto }
        }
    }

    fun removeFromCart(index: Int) {
        if (index in _shoppingCart.value.indices) {
            _shoppingCart.update { it.toMutableList().apply { removeAt(index) } }
        }
    }

    fun clearCart() {
        _shoppingCart.value = emptyList()
    }

    val totalPrice: Float
        get() = _shoppingCart.value.sumOf { selectedPhoto ->
            (selectedPhoto.photo.price +
                    selectedPhoto.frameType.extraPrice +
                    selectedPhoto.frameWidth.extraPrice +
                    selectedPhoto.photoSize.extraPrice).toDouble()
        }.toFloat()
}