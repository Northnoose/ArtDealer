package com.example.artdealer.data

import android.content.Context
import com.example.artdealer.network.RetrofitInstance

class AppContainer(context: Context) {

    // Retrofit-tjeneste for nettverkskall
    val retrofitService = RetrofitInstance.api

    // Lokal database og DAO
    private val shoppingCartDatabase = ShoppingCartDatabase.getDatabase(context)
    private val shoppingCartDao = shoppingCartDatabase.shoppingCartDao()

    // Repository for handlekurv
    val shoppingCartRepository = ShoppingCartRepository(shoppingCartDao)
}
