package com.example.artdealer

import android.app.Application
import com.example.artdealer.data.AppContainer

class ArtDealerApplication : Application() {

    // Holder for alle avhengigheter i appen
    lateinit var container: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()

        // Initialiser AppContainer med applicationContext
        container = AppContainer(this)
    }
}
