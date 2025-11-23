package com.example.whitesymphonimobil.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.whitesymphonimobil.data.local.entity.CarritoProducto
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoCarrito {

    @Query("SELECT * FROM cart_items ORDER BY id DESC")
    fun observeCart(): Flow<List<CarritoProducto>>

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun upsert(item: CarritoProducto): Long

    @Update
    suspend fun update(item: CarritoProducto)

    @Delete
    suspend fun delete(item: CarritoProducto)

    @Query("DELETE FROM cart_items")
    suspend fun clear()

    @Query("SELECT SUM(precio * cantidad) FROM cart_items")
    fun observeTotal(): Flow<Double?>
}