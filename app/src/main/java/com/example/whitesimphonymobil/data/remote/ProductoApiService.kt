package com.example.whitesimphonymobil.data.remote


import com.example.whitesimphonymobil.data.remote.dto.ProductoDto
import retrofit2.http.GET
import retrofit2.http.DELETE
import retrofit2.http.Path
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.PUT

interface ProductoApiService {
    @GET("whitesimphony/productos")
    suspend fun obtenerProductos(): List<ProductoDto>

    @POST("whitesimphony/productos")
    suspend fun agregarProducto(@Body producto: ProductoDto): Response<ProductoDto>

    @PUT("whitesimphony/productos/{id}")
    suspend fun editarProducto(@Path("id") id: Long, @Body producto: ProductoDto): Response<ProductoDto>

    @DELETE("whitesimphony/productos/{id}")
    suspend fun eliminarProducto(@Path("id") id: Long): Response<Unit>
}
