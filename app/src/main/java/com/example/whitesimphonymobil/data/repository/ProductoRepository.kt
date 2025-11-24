package com.example.whitesimphonymobil.data.repository


import com.example.whitesimphonymobil.data.local.dao.DaoProducto
import com.example.whitesimphonymobil.data.local.entity.ProductoEntity
import com.example.whitesimphonymobil.data.remote.ApiClient
import com.example.whitesimphonymobil.data.remote.ProductoApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductoRepository(
    private val dao: DaoProducto
) {
    private val api = ApiClient.retrofit.create(ProductoApiService::class.java)


    suspend fun syncProductos() {
        val productosRemotos = api.obtenerProductos()

        val productosLocal = productosRemotos.map {
            ProductoEntity(
                id = it.id,
                nombre = it.nombre,
                precio = it.precio,
                rating = it.rating,
                imageRes = com.example.whitesymphonymobil.R.drawable.logo // placeholder
            )
        }

        dao.insertAll(productosLocal)
    }


    suspend fun obtenerLocal(): List<ProductoEntity> {
        return dao.getAll()
    }
}
