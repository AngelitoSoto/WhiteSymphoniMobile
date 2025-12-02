package com.example.whitesimphonymobil.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.whitesimphonymobil.data.local.entity.CarritoProducto
import com.example.whitesimphonymobil.ui.pantallas.PantallaPago
import com.example.whitesimphonymobil.ui.viewmodel.CarritoViewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun PantallaCarrito(
    viewModel: CarritoViewModel,
    onVolver: () -> Unit
) {
    BackHandler {
        onVolver()
    }

    var mostrandoPago by remember { mutableStateOf(false) }
    var mostrandoResena by remember { mutableStateOf(false) }
    var pagoFinalizado by remember { mutableStateOf(false) }


    var productosParaResena by remember { mutableStateOf<List<com.example.whitesimphonymobil.data.model.Producto>>(emptyList()) }

    val carrito by viewModel.cartItems.collectAsState()
    val total by viewModel.total.collectAsState()

    when {


        mostrandoPago -> {
            PantallaPago {

                productosParaResena = carrito.map {
                    com.example.whitesimphonymobil.data.model.Producto(
                        nombre = it.nombre,
                        precio = it.precio,
                        imageRes = it.imageRes,
                        rating = it.rating
                    )
                }

                mostrandoPago = false
                mostrandoResena = true

                // Ahora sí limpiamos SQLite
                viewModel.clear()
            }
        }


        mostrandoResena -> {
            PantallaResena(
                productos = productosParaResena,
                onFinish = {
                    mostrandoResena = false
                    pagoFinalizado = true
                }
            )
        }


        pagoFinalizado -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(
                        Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = Color(0xFF4CAF50),
                        modifier = Modifier.size(80.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text("¡Reseña publicada con éxito!", fontSize = 22.sp)
                    Spacer(modifier = Modifier.height(24.dp))
                    Button(onClick = onVolver) { Text("Volver al inicio") }
                }
            }
        }


        else -> {
            Column(modifier = Modifier.fillMaxSize()) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = onVolver) {
                        Text("Volver al inicio")
                    }
                    Text(
                        "Carrito",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }

                if (carrito.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Tu carrito está vacío 🛒")
                    }
                } else {
                    LazyColumn(modifier = Modifier.weight(1f)) {
                        items(carrito) { producto ->
                            ItemCarritoRow(
                                producto = producto,
                                onEliminar = { viewModel.remove(producto) }
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Total: $${String.format("%,.0f", total)}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = { mostrandoPago = true },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Pagar")
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedButton(
                            onClick = onVolver,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Volver a la página principal")
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ItemCarritoRow(
    producto: CarritoProducto,
    onEliminar: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val painter = if (producto.imageRes.startsWith("http")) {

            rememberAsyncImagePainter(producto.imageRes)
        } else {

            painterResource(id = producto.imageRes.toInt())
        }

        Image(
            painter = painter,
            contentDescription = producto.nombre,
            modifier = Modifier.size(64.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column(modifier = Modifier.weight(1f)) {
            Text(producto.nombre, fontWeight = FontWeight.Bold)
            Text("$${producto.precio}")
            Text("Cantidad: ${producto.cantidad}", fontSize = 12.sp)
        }

        Button(
            onClick = onEliminar,
            modifier = Modifier.height(40.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Cyan)
        ) {
            Text("Eliminar")
        }
    }
}
