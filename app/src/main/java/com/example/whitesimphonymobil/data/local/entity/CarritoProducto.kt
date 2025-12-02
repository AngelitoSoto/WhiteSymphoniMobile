package com.example.whitesimphonymobil.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CarritoProducto(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val nombre: String,
    val precio: Double,
    val imageRes: String,
    val rating: Double,
    val cantidad: Int = 1
)