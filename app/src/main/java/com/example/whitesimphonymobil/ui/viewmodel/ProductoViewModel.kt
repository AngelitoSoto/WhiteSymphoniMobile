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

class ProductoViewModel(
    private val repository: ProductoRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _productos = MutableStateFlow<List<ProductoEntity>>(emptyList())
    val productos: StateFlow<List<ProductoEntity>> = _productos

    fun cargarProductos() {
        viewModelScope.launch(dispatcher) {
            repository.syncProductos()
            _productos.value = repository.obtenerLocal()
        }
    }
}


