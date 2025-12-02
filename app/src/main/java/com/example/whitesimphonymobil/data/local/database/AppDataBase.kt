package com.example.whitesymphonymobil.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.whitesimphonymobil.data.local.dao.DaoCarrito
import com.example.whitesimphonymobil.data.local.entity.CarritoProducto
import com.example.whitesimphonymobil.data.local.entity.ProductoEntity
import com.example.whitesimphonymobil.data.local.dao.DaoProducto

@Database(
    entities = [CarritoProducto::class, ProductoEntity::class],
    version = 3,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): DaoCarrito
    abstract fun productoDao(): DaoProducto

    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null

        fun get(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "white_symphony.db"
                )
                    .fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
        }
    }
}
