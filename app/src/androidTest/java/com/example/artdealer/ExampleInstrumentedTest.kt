package com.example.artdealer

import androidx.compose.ui.test.filter
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.artdealer.data.FrameType
import com.example.artdealer.data.FrameWidth
import com.example.artdealer.data.PhotoSize
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private fun addTestPhotoToCart() {
        val artist = context.getString(R.string.artist)
        val addToCart = context.getString(R.string.add_to_cart)
        val chose = context.getString(R.string.choose_frame_quality)

        composeTestRule.onNodeWithText(artist).performClick()
        composeTestRule.onNodeWithText("Frida Kahlo").performClick()
        composeTestRule.onNodeWithContentDescription("De To Fridaer").performClick()

        composeTestRule.onNodeWithText(chose).assertExists()
        composeTestRule.onNodeWithText(FrameType.METAL.name, useUnmergedTree = true).performClick()

        composeTestRule.onNodeWithTag("FrameWidthOptions")
            .onChildren().filter(hasText(FrameWidth.SMALL.name)).onFirst().performClick()

        composeTestRule.onNodeWithTag("FrameWidthOptions")
            .onChildren().filter(hasText(PhotoSize.LARGE.name)).onFirst().performClick()

        composeTestRule.onNodeWithText(addToCart).performClick()
    }

    @Test
    fun navigateToGalleryAndBack() {
        val category = context.getString(R.string.category)
        val back = context.getString(R.string.back)

        composeTestRule.onNodeWithText(category).performClick()
        composeTestRule.onNodeWithText("ANIMALS").performClick()
        composeTestRule.onNodeWithContentDescription(back).performClick()
        composeTestRule.onNodeWithContentDescription(back).assertExists()
    }

    @Test
    fun selectPhotoAndAddToCart() {
        addTestPhotoToCart()
        composeTestRule.onNodeWithText("Hjem").assertExists()
    }

    @Test
    fun removePhotoFromCart() {
        val remove = context.getString(R.string.remove_picture)
        val emptyCart = context.getString(R.string.empty_cart)

        addTestPhotoToCart()
        composeTestRule.onNodeWithText("Hjem").performClick()

        composeTestRule.onNodeWithContentDescription(remove).performClick()
        composeTestRule.onNodeWithText(emptyCart).assertExists()
    }
}
