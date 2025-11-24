package com.example.whitesimphonymobil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whitesimphonymobil.data.local.entity.CarritoProducto
import com.example.whitesimphonymobil.data.repository.CarritoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class CarritoViewModel(
    private val repository: CarritoRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _cartItems = MutableStateFlow<List<CarritoProducto>>(emptyList())
    val cartItems: StateFlow<List<CarritoProducto>> = _cartItems.asStateFlow()

    private val _total = MutableStateFlow(0.0)
    val total: StateFlow<Double> = _total.asStateFlow()

    init {
        observeItems()
        observeTotal()
    }

    private fun observeItems() {
        viewModelScope.launch(dispatcher) {
            repository.cartItems.collectLatest { items ->
                _cartItems.value = items
            }
        }
    }

    private fun observeTotal() {
        viewModelScope.launch(dispatcher) {
            repository.total.collectLatest { amount ->
                _total.value = amount ?: 0.0
            }
        }
    }

    fun add(item: CarritoProducto) {
        viewModelScope.launch(dispatcher) {
            repository.add(item)
        }
    }

    fun update(item: CarritoProducto) {
        viewModelScope.launch(dispatcher) {
            repository.update(item)
        }
    }

    fun remove(item: CarritoProducto) {
        viewModelScope.launch(dispatcher) {
            repository.remove(item)
        }
    }

    fun clear() {
        viewModelScope.launch(dispatcher) {
            repository.clear()
        }
    }
}
