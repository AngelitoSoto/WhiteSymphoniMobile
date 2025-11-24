package com.example.whitesimphonymobil

import com.example.whitesimphonymobil.data.local.entity.ProductoEntity
import com.example.whitesimphonymobil.data.repository.ProductoRepository
import com.example.whitesimphonymobil.ui.viewmodel.ProductoViewModel
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductoViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val testDispatcher = StandardTestDispatcher()

    private val repo = mockk<ProductoRepository>(relaxed = true)

    @Test
    fun cargarProductosObtieneDatosLocalesDespuesDeSync() = runTest {

        val fakeLocal = listOf(
            ProductoEntity(1, "Producto Test", 15000.0, 10, 4.5)
        )

        coEvery { repo.syncProductos() } returns Unit
        coEvery { repo.obtenerLocal() } returns fakeLocal

        val viewModel = ProductoViewModel(repo, dispatcher = StandardTestDispatcher(testScheduler))

        viewModel.cargarProductos()

        advanceUntilIdle()

        coVerify(exactly = 1) { repo.syncProductos() }
        coVerify(exactly = 1) { repo.obtenerLocal() }

        val resultado = viewModel.productos.value

        assertEquals(1, resultado.size)
        assertEquals("Producto Test", resultado[0].nombre)
    }

}
