package com.example.whitesimphonymobil.data.repository


import com.example.whitesimphonymobil.data.local.dao.DaoProducto
import com.example.whitesimphonymobil.data.local.entity.ProductoEntity
import com.example.whitesimphonymobil.data.remote.ApiClient
import com.example.whitesimphonymobil.data.remote.ProductoApiService
import com.example.whitesimphonymobil.data.remote.dto.ProductoDto

class ProductoRepository(
    private val dao: DaoProducto
) {
    private val api = ApiClient.retrofit.create(ProductoApiService::class.java)


    suspend fun syncProductos() {
        val productosRemotos = api.obtenerProductos()

        val productosLocal = productosRemotos.map {
            ProductoEntity(
                id = it.id ?: 0L,
                nombre = it.nombre,
                precio = it.precio,
                imagenUrl = it.imagenUrl,
                rating = it.rating
            )
        }

        dao.clearTable()
        dao.insertAll(productosLocal)
    }

    suspend fun obtenerLocal(): List<ProductoEntity> {
        return dao.getAll()
    }


    suspend fun eliminarProducto(id: Long) {
        val response = api.eliminarProducto(id)
        if (response.isSuccessful) {
            dao.deleteById(id)
        } else {
            throw Exception("Error DELETE API: ${response.code()}")
        }
    }


    suspend fun agregarProducto(dto: ProductoDto) {
        val response = api.agregarProducto(dto)
        if (response.isSuccessful) {
            syncProductos()
        } else {
            throw Exception("Error POST API: ${response.code()}")
        }
    }


    suspend fun editarProducto(id: Long, dto: ProductoDto) {
        val response = api.editarProducto(id, dto)
        if (response.isSuccessful) {
            syncProductos()
        } else {
            throw Exception("Error PUT API: ${response.code()}")
        }
    }
}

