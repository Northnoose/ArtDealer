package com.example.artdealer.repository

import com.example.artdealer.network.RetrofitInstance

class PhotoRepository {
    private val api = RetrofitInstance.api

    suspend fun fetchCategories() = api.getCategory()
    suspend fun fetchFrameType() = api.getFrameType()
    suspend fun fetchFrameWidth() = api.getFrameWidht()
    suspend fun fetchPhotoSize() = api.getPhotoSize()
}