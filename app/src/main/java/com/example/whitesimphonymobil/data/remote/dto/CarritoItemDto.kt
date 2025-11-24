package com.example.whitesimphonymobil.data.remote.dto


data class CarritoItemDto(
    val id: Long? = null,
    val nombre: String,
    val precio: Double,
    val cantidad: Int,
    val imagenUrl: String?
)