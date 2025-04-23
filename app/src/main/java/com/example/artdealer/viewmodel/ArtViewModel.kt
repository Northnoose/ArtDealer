package com.example.artdealer.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.artdealer.data.*
import com.example.artdealer.network.PhotoService
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ArtViewModel(
    private val repository: ShoppingCartRepository,
    private val photoService: PhotoService
) : ViewModel() {

    private val _uiState = MutableStateFlow(PhotoUiState())
    val uiState: StateFlow<PhotoUiState> = _uiState

    var chosenPhoto = mutableStateOf<SelectedPhoto?>(null)

    val shoppingCartItems: StateFlow<List<SelectedPhotoEntity>> = repository.allItems.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    init {
        fetchDataFromServer()
    }

    private fun fetchDataFromServer() {
        viewModelScope.launch {
            try {
                val photos = photoService.getPhotos()
                val artists = photoService.getArtisData()
                val categories = photoService.getCategory()
                val frameTypes = photoService.getFrameType()
                val frameWidths = photoService.getFrameWidht()
                val photoSizes = photoService.getPhotoSize()

                val mappedPhotos = photos.map { photo ->
                    val artist = artists.find { it.id.toLong().toInt() == photo.artistId }
                    val category = categories.find { it.id.toLong().toInt() == photo.categoryId }
                    // Bruk safe call ?. og fallback til tom artist/category hvis null
                    photo.copy(
                        artist = artist ?: ArtistData(0, "", ""),
                        category = category ?: Category(0, "")
                    )
                }

                _uiState.update {
                    it.copy(
                        album = mappedPhotos,
                        filteredPhotos = mappedPhotos,
                        artists = artists,
                        categories = categories,
                        frameTypes = frameTypes,
                        frameWidths = frameWidths,
                        photoSizes = photoSizes,
                        selectedFrameType = frameTypes.firstOrNull(),
                        selectedFrameWidth = frameWidths.firstOrNull(),
                        selectedPhotoSize = photoSizes.firstOrNull()
                    )
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun filterPhotosByCategory(category: Category) {
        _uiState.update { current ->
            current.copy(filteredPhotos = current.album.filter { it.category?.id == category.id })
        }
    }

    fun filterPhotosByArtist(artistData: ArtistData) {
        _uiState.update { current ->
            current.copy(filteredPhotos = current.album.filter { it.artist?.id == artistData.id })
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
                (uiState.value.selectedFrameType?.extraPrice ?: 0f) +
                (uiState.value.selectedFrameWidth?.extraPrice ?: 0f) +
                (uiState.value.selectedPhotoSize?.extraPrice ?: 0f)
    }

    fun selectPhoto(
        photo: Photo,
        frame: FrameType,
        frameWidth: FrameWidth,
        size: PhotoSize
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
            val entity = SelectedPhotoEntity(
                photoTitle = selectedPhoto.photo.title,
                artistName = "${selectedPhoto.photo.artist?.name} ${selectedPhoto.photo.artist?.familyName}",
                category = selectedPhoto.photo.category!!.name,
                frameType = selectedPhoto.frameType.name,
                frameWidth = selectedPhoto.frameWidth.name,
                photoSize = selectedPhoto.photoSize.name,
                photoPrice = selectedPhoto.photoPrice ?: 0f
            )

            viewModelScope.launch {
                repository.insert(entity)
            }
        }
    }

    fun removeFromCart(item: SelectedPhotoEntity) {
        viewModelScope.launch {
            repository.delete(item)
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            repository.clearCart()
        }
    }

    val totalPrice: StateFlow<Float> = shoppingCartItems.map { items ->
        items.sumOf { it.photoPrice.toDouble() }.toFloat()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0f)
}
