package com.example.whitesimphonymobil.ui.pantallas

import androidx.compose.runtime.Composable
import com.example.whitesimphonymobil.ui.viewmodel.ProductoViewModel
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.material3.OutlinedTextField
import com.example.whitesimphonymobil.data.remote.dto.ProductoDto
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState

@Composable
fun PantallaEditarProducto(
    productId: Long,
    viewModel: ProductoViewModel,
    onVolver: () -> Unit
) {
    val producto = viewModel.productos.collectAsState().value.first { it.id == productId }

    var nombre by remember { mutableStateOf(producto.nombre) }
    var precio by remember { mutableStateOf(producto.precio.toString()) }

    Column(Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") }
        )

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio") }
        )

        Button(onClick = {
            viewModel.editarProducto(
                productId,
                ProductoDto(
                    id = productId,
                    nombre = nombre,
                    precio = precio.toDouble(),
                    rating = producto.rating,
                    imagenUrl = producto.imagenUrl
                )
            )
            onVolver()
        }) {
            Text("Actualizar")
        }
    }
}
