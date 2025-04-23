package com.example.artdealer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.artdealer.data.ShoppingCartRepository
import com.example.artdealer.network.PhotoService

class ArtViewModelFactory(
    private val repository: ShoppingCartRepository,
    private val photoService: PhotoService
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArtViewModel::class.java)) {
            return ArtViewModel(repository, photoService) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
