package com.example.whitesimphonymobil.data.remote


import com.example.whitesimphonymobil.data.remote.dto.CarritoItemDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CarritoApiService {

    @GET("whitesimphony/carrito")
    suspend fun obtenerCarrito(): List<CarritoItemDto>

    @POST("whitesimphony/carrito")
    suspend fun agregarAlCarrito(@Body item: CarritoItemDto): CarritoItemDto

    @DELETE("whitesimphony/carrito/{id}")
    suspend fun eliminarItem(@Path("id") id: Long)

    @DELETE("whitesimphony/carrito")
    suspend fun limpiarCarrito()
}

