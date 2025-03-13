package com.example.artdealer.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.artdealer.data.ArtistData
import com.example.artdealer.data.ArtistDataSource.loadArtists
import com.example.artdealer.data.Category
import com.example.artdealer.data.FrameType
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

    private val _artists = MutableStateFlow(loadArtists())
    val artists: StateFlow<List<ArtistData>> = _artists

    private val _filteredPhotos = MutableStateFlow(loadPictures())
    val filteredPhotos: StateFlow<List<Photo>> = _filteredPhotos

    private val _shoppingCart = MutableStateFlow<List<SelectedPhoto>>(emptyList())
    val shoppingCart: StateFlow<List<SelectedPhoto>> = _shoppingCart

    private val _currentScreen = MutableStateFlow(Screens.Home)
    val currentScreen: StateFlow<Screens> = _currentScreen

    var chosenPhoto = mutableStateOf<SelectedPhoto?>(null)

    fun filterPhotosByCategory(category: Category) {
        _filteredPhotos.value = _album.value.filter {it.category == category}
    }

    fun filterPhotosByArtist(artistData: ArtistData) {
        _filteredPhotos.value = _album.value.filter {it.artist.id == artistData.id}
    }

    fun selectPhoto(photo: Photo, frame : FrameType = FrameType.WOOD, size: PhotoSize = PhotoSize.SMALL) {
        chosenPhoto.value = SelectedPhoto(
            id = photo.id,
            frame = frame,
            size = size,
            price = frame.extraPrice + size.extraPrice + photo.price,
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

    fun emptyCart() {
        _shoppingCart.update { emptyList() }
    }

    fun navigateTo(screen: Screens) {
        _currentScreen.update { screen }
    }
}