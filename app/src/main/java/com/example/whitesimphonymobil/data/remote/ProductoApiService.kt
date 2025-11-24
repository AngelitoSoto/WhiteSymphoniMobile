package com.example.whitesimphonymobil.data.remote


import com.example.whitesimphonymobil.data.remote.dto.ProductoDto
import retrofit2.http.GET

interface ProductoApiService {
    @GET("whitesimphony/productos")
    suspend fun obtenerProductos(): List<ProductoDto>
}
