package com.example.whitesimphonymobil.data.local.dao

import androidx.room.*

import com.example.whitesimphonymobil.data.local.entity.ProductoEntity

@Dao
interface DaoProducto {

    @Query("SELECT * FROM productos")
    suspend fun getAll(): List<ProductoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(productos: List<ProductoEntity>)

    @Query("DELETE FROM productos WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM productos")
    suspend fun clearTable()
}