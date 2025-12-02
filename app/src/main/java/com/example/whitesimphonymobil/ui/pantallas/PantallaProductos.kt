package com.example.whitesimphonymobil.ui.pantallas

import androidx.compose.runtime.Composable
import com.example.whitesimphonymobil.ui.viewmodel.ProductoViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.material3.Scaffold
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme

@Composable
fun PantallaProductos(
    viewModel: ProductoViewModel,
    onAgregar: () -> Unit,
    onEditar: (Long) -> Unit
) {
    val productos = viewModel.productos.collectAsState().value

    LaunchedEffect(Unit) {
        viewModel.cargarProductos()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAgregar) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(Modifier.padding(padding)) {
            items(productos) { p ->
                Card(Modifier.padding(8.dp)) {
                    Row(Modifier.padding(16.dp)) {
                        Column(Modifier.weight(1f)) {
                            Text(p.nombre)
                            Text("Precio: $${p.precio}")
                        }

                        Button(onClick = { onEditar(p.id) }) {
                            Text("Editar")
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Button(
                            onClick = { viewModel.eliminarProducto(p.id) },
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.error)
                        ) {
                            Text("Eliminar")
                        }
                    }
                }
            }
        }
    }
}
