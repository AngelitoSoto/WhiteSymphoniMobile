package com.example.whitesymphonimobil.data.repository

import com.example.whitesymphonimobil.data.local.dao.DaoCarrito
import com.example.whitesymphonimobil.data.local.entity.CarritoProducto

class CarritoRepository(private val dao: DaoCarrito) {
    val cartItems = dao.observeCart()
    val total = dao.observeTotal()

    suspend fun add(item: CarritoProducto) = dao.upsert(item)
    suspend fun update(item: CarritoProducto) = dao.update(item)
    suspend fun remove(item: CarritoProducto) = dao.delete(item)
    suspend fun clear() = dao.clear()
}