package com.example.artdealer.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [SelectedPhotoEntity::class], version = 1, exportSchema = false)
abstract class ShoppingCartDatabase : RoomDatabase() {
    abstract fun shoppingCartDao(): ShoppingCartDao

    companion object {
        @Volatile
        private var INSTANCE: ShoppingCartDatabase? = null

        fun getDatabase(context: Context): ShoppingCartDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShoppingCartDatabase::class.java,
                    "shopping_cart_db"
                )
                    .fallbackToDestructiveMigration()  // Valgfritt, for enkel testing
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
