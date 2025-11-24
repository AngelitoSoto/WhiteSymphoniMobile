package com.example.whitesimphonymobil.data.mappers


import com.example.whitesymphonymobil.R
import com.example.whitesimphonymobil.data.remote.dto.ProductoDto
import com.example.whitesimphonymobil.data.local.entity.ProductoEntity

fun ProductoDto.toEntity(): ProductoEntity {
    return ProductoEntity(
        id = id,
        nombre = nombre,
        precio = precio,
        imageRes = R.drawable.logo,
        rating = rating
    )
}
