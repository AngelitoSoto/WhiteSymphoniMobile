package com.example.whitesimphonymobil


import com.example.whitesimphonymobil.data.local.entity.CarritoProducto
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule

class CalculoTotalTest {

    @Test
    fun totalSeCalculaCorrectamente() {

        val lista = listOf(
            CarritoProducto(
                id = 0,
                nombre = "Producto A",
                precio = 1000.0,
                imageRes = 0,
                rating = 4.0,
                cantidad = 1
            ),
            CarritoProducto(
                id = 0,
                nombre = "Producto B",
                precio = 500.0,
                imageRes = 0,
                rating = 5.0,
                cantidad = 2
            )
        )

        val totalCalculado = lista.sumOf { it.precio * it.cantidad }

        val totalEsperado = 1000.0 + (500.0 * 2)

        assertEquals(totalEsperado, totalCalculado, 0.01)
    }
}
