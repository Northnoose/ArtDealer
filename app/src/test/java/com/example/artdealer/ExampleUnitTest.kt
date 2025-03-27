package com.example.artdealer

import org.junit.Test

import org.junit.Assert.*
import com.example.artdealer.data.*
import com.example.artdealer.viewmodel.ArtViewModel
import org.junit.Assert.assertEquals
import org.junit.Before


class ArtViewModelTest {

    private lateinit var viewModel: ArtViewModel
    private val testPhoto = Photo(
        id = 1,
        title = "Test",
        imageID = 0, // Dummy
        artist = ArtistData(1, "Test", "Artist"),
        category = Category.NATURE,
        price = 100f
    )

    @Before
    fun setup() {
        viewModel = ArtViewModel()
    }

    @Test
    fun testPriceWithDefaultSelections() {
        val price = viewModel.calculateSelectionPrice(testPhoto)
        assertEquals(100f, price, 0.01f)
    }

    @Test
    fun testPriceWithMetalLargeLarge() {
        viewModel.setFrameType(FrameType.METAL)
        viewModel.setFrameWidth(FrameWidth.LARGE)
        viewModel.setPhotoSize(PhotoSize.LARGE)

        val price = viewModel.calculateSelectionPrice(testPhoto)
        assertEquals(660f, price, 0.01f)
    }

    @Test
    fun testPriceWithPlasticMediumMedium() {
        viewModel.setFrameType(FrameType.PLASTIC)
        viewModel.setFrameWidth(FrameWidth.MEDIUM)
        viewModel.setPhotoSize(PhotoSize.MEDIUM)

        val price = viewModel.calculateSelectionPrice(testPhoto)
        assertEquals(390f, price, 0.01f)
    }
}
