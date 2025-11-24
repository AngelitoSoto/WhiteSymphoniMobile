package com.example.whitesimphonymobil


import com.example.whitesimphonymobil.data.remote.dto.ProductoDto
import com.example.whitesimphonymobil.data.local.entity.ProductoEntity
import com.example.whitesimphonymobil.data.mappers.toEntity
import com.example.whitesymphonymobil.R
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Rule

class ProductoConversionTest {

    @Test
    fun `convierte ProductoDto a ProductoEntity correctamente`() {

        val dto = ProductoDto(
            id = 10L,
            nombre = "Producto Test",
            precio = 45000.0,
            imagenUrl = "https://ejemplo.com/imagen.jpg",
            rating = 4.5
        )

        val entity: ProductoEntity = dto.toEntity()

        assertEquals(10L, entity.id)
        assertEquals("Producto Test", entity.nombre)
        assertEquals(45000.0, entity.precio, 0.0)
        assertEquals(R.drawable.logo, entity.imageRes)
        assertEquals(4.5, entity.rating, 0.0)
    }
}
