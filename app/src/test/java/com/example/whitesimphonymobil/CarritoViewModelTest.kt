package com.example.whitesimphonymobil

import com.example.whitesimphonymobil.data.local.entity.CarritoProducto
import com.example.whitesimphonymobil.data.repository.CarritoRepository
import com.example.whitesimphonymobil.ui.viewmodel.CarritoViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
class CarritoViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val repo: CarritoRepository = mock()

    private lateinit var viewModel: CarritoViewModel

    // Flows falsos
    private val fakeItemsFlow = MutableStateFlow<List<CarritoProducto>>(emptyList())
    private val fakeTotalFlow = MutableStateFlow(0.0)

    @Before
    fun setup() {
        // Mockear los flows CORRECTOS (los que existen)
        whenever(repo.cartItems).thenReturn(fakeItemsFlow)
        whenever(repo.total).thenReturn(fakeTotalFlow)

        viewModel = CarritoViewModel(repo)
    }

    @Test
    fun agregarProductoLlamaAlRepositorio() = runTest {
        val producto = CarritoProducto(
            nombre = "Disco X",
            precio = 10000.0,
            cantidad = 1,
            imageRes = 0,
            rating = 4.0
        )

        viewModel.add(producto)

        verify(repo).add(producto)
    }

    @Test
    fun eliminarProductoLlamaAlRepositorio() = runTest {
        val producto = CarritoProducto(
            nombre = "A",
            precio = 5000.0,
            cantidad = 1,
            imageRes = 0,
            rating = 4.0
        )

        viewModel.remove(producto)

        verify(repo).remove(producto)
    }
}
