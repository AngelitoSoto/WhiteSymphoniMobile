package com.example.whitesimphonymobil.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "productos")
data class ProductoEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long,
    val nombre: String,
    val precio: Double,
    val imagenUrl: String,
    val rating: Double
)