package com.example.artdealer.network

import com.example.artdealer.data.ArtistData
import com.example.artdealer.data.Category
import com.example.artdealer.data.FrameType
import com.example.artdealer.data.FrameWidth
import com.example.artdealer.data.Photo
import com.example.artdealer.data.PhotoSize
import retrofit2.http.GET

interface PhotoService {
    @GET("categories")
    suspend fun getCategory(): List<Category>

    @GET("frametypes")
    suspend fun getFrameType(): List<FrameType>

    @GET("photosizes")
    suspend fun getPhotoSize(): List<PhotoSize>

    @GET("framewidth")
    suspend fun getFrameWidht(): List<FrameWidth>

    @GET("artists")
    suspend fun getArtisData(): List<ArtistData>

    @GET("photos")
    suspend fun getPhotos(): List<Photo>
}