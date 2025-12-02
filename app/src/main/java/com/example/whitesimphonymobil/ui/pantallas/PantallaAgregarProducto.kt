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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height

@Composable
fun PantallaAgregarProducto(
    viewModel: ProductoViewModel,
    onVolver: () -> Unit
) {

    var nombre by remember { mutableStateOf("") }
    var precio by remember { mutableStateOf("") }

    Column(Modifier.padding(16.dp)) {

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre del producto") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = {
                viewModel.agregarProducto(
                    ProductoDto(
                        id = null,          // 🔥 NO ENVIAR ID
                        nombre = nombre,
                        precio = precio.toDoubleOrNull() ?: 0.0,
                        imagenUrl = "",      // por ahora ok
                        rating = 5.0
                    )
                )
                onVolver()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }
    }
}

