package com.example.whitesimphonymobil.ui.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whitesimphonymobil.data.repository.ProductoRepository
import com.example.whitesimphonymobil.data.local.entity.ProductoEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.whitesimphonymobil.data.remote.dto.ProductoDto

class ProductoViewModel(
    private val repository: ProductoRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _productos = MutableStateFlow<List<ProductoEntity>>(emptyList())
    val productos: StateFlow<List<ProductoEntity>> = _productos

    fun cargarProductos() {
        viewModelScope.launch(dispatcher) {
            try {
                repository.syncProductos()
            } catch (e: Exception) {

                println("❌ Error: servidor no disponible -> ${e.message}")
            }


            _productos.value = try {
                repository.obtenerLocal()
            } catch (e: Exception) {
                emptyList()
            }
        }
    }

    fun eliminarProducto(id: Long) {
        viewModelScope.launch(dispatcher) {
            try {
                repository.eliminarProducto(id)
                _productos.value = repository.obtenerLocal()
            } catch (e: Exception) {
                println("Error al eliminar: ${e.message}")
            }
        }
    }

    fun agregarProducto(dto: ProductoDto) {
        viewModelScope.launch(dispatcher) {
            try {
                repository.agregarProducto(dto)
                cargarProductos()
            } catch (e: Exception) {
                println("❌ Error POST: ${e.message}")
            }
        }
    }

    fun editarProducto(id: Long, dto: ProductoDto) {
        viewModelScope.launch(dispatcher) {
            try {
                repository.editarProducto(id, dto)
                cargarProductos()
            } catch (e: Exception) {
                println("❌ Error PUT: ${e.message}")
            }
        }
    }
}


