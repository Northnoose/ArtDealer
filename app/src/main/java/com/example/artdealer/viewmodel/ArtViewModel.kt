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
import com.example.artdealer.data.SelectedPhoto

class ArtViewModel : ViewModel(){
    var album = mutableStateOf(loadPictures())
    var artists = mutableStateOf(loadArtists())

    var filteredPhotos = mutableStateOf(album.value)
    var chosenPhoto = mutableStateOf<SelectedPhoto?>(null)

    fun filterPhotosByCategory(category: Category) {
        filteredPhotos.value = album.value.filter {it.category == category}
    }

    fun filterPhotosByArtist(artistData: ArtistData) {
        filteredPhotos.value = album.value.filter {it.artist.id == artistData.id}
    }

    fun chooseAPhoto(photo: Photo, frame : FrameType = FrameType.WOOD, size: PhotoSize = PhotoSize.SMALL) {
        chosenPhoto.value = SelectedPhoto(
            id = photo.id,
            frame = frame,
            size = size,
            price = frame.extraPrice + size.extraPrice + photo.price,
        )
    }
}