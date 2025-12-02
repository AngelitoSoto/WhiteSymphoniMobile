package com.example.whitesimphonymobil.data.remote.dto

data class ProductoDto(
    val id: Long? = null,
    val nombre: String,
    val precio: Double,
    val imagenUrl: String = "",
    val rating: Double
)