package com.example.whitesymphonymobil.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.whitesymphonimobil.data.local.dao.DaoCarrito
import com.example.whitesymphonimobil.data.local.entity.CarritoProducto

@Database(
    entities = [CarritoProducto::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): DaoCarrito

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "white_symphony.db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
