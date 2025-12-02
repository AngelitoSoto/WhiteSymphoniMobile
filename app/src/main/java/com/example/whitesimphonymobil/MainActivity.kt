package com.example.whitesymphonymobil

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

import com.example.whitesymphonymobil.ui.theme.WhiteSymphonyTheme
import com.example.whitesymphonymobil.ui.componentes.BarraInferior

import com.example.whitesymphonymobil.data.local.AppDatabase
import com.example.whitesimphonymobil.data.repository.CarritoRepository
import com.example.whitesimphonymobil.ui.viewmodel.CarritoViewModel

import com.example.whitesimphonymobil.data.repository.ProductoRepository
import com.example.whitesimphonymobil.ui.viewmodel.ProductoViewModel

import com.example.whitesimphonymobil.ui.viewmodel.DeezerViewModel
import com.example.whitesimphonymobil.data.remote.external.ApiClientDeezer
import com.example.whitesimphonymobil.data.remote.external.DeezerApiService

import com.example.whitesymphonymobil.ui.screens.PantallaPrincipal
import com.example.whitesimphonymobil.ui.screens.PantallaCarrito
import com.example.whitesimphonymobil.ui.pantallas.PantallaPago
import com.example.whitesimphonymobil.ui.screens.PantallaResena
import com.example.whitesimphonymobil.ui.screens.PantallaUsuario
import com.example.whitesimphonymobil.ui.pantallas.PantallaBusquedaMusica
import com.example.whitesimphonymobil.ui.pantallas.PantallaProductos
import com.example.whitesimphonymobil.ui.pantallas.PantallaAgregarProducto
import com.example.whitesimphonymobil.ui.pantallas.PantallaEditarProducto

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WhiteSymphonyTheme {


                var pantallaActual by remember { mutableStateOf("home") }

                val productoIdAEditar = remember { mutableStateOf(0L) }


                val context = LocalContext.current
                val db = AppDatabase.get(context)
                val CarritoRepo = CarritoRepository(db.cartDao())
                val carritoViewModel = remember { CarritoViewModel(CarritoRepo) }

                val productoRepo = ProductoRepository(db.productoDao())
                val productoViewModel = remember { ProductoViewModel(productoRepo) }

                val deezerViewModel = DeezerViewModel(
                    ApiClientDeezer.retrofit.create(DeezerApiService::class.java)
                )

                Scaffold(
                    bottomBar = {
                        if (pantallaActual in listOf("home", "productos", "usuario")) {
                            BarraInferior(
                                onHomeClick = { pantallaActual = "home" },
                                onUserClick = { pantallaActual = "usuario" },
                                onProductosClick = { pantallaActual = "productos"},
                                currentScreen = pantallaActual
                            )
                        }
                    }
                ) { innerPadding ->

                    Box(modifier = Modifier.padding(innerPadding)) {

                        when (pantallaActual) {

                            "home" -> PantallaPrincipal(
                                onIrCarrito = { pantallaActual = "carrito" },
                                onIrBusquedaMusica = { pantallaActual = "busqueda" },
                                carritoViewModel = carritoViewModel,
                                productoViewModel = productoViewModel
                            )

                            "productos" -> PantallaProductos(
                                viewModel = productoViewModel,
                                onAgregar = { pantallaActual = "agregarProducto" },
                                onEditar = { id ->
                                    productoIdAEditar.value = id
                                    pantallaActual = "editarProducto"
                                }
                            )

                            "agregarProducto" -> PantallaAgregarProducto(
                                viewModel = productoViewModel,
                                onVolver = { pantallaActual = "productos" }
                            )

                            "editarProducto" -> PantallaEditarProducto(
                                productId = productoIdAEditar.value,
                                viewModel = productoViewModel,
                                onVolver = { pantallaActual = "productos" }
                            )

                            "carrito" -> PantallaCarrito(
                                viewModel = carritoViewModel,
                                onVolver = { pantallaActual = "home" }
                            )

                            "pago" -> PantallaPago {
                                pantallaActual = "resena"
                            }

                            "resena" -> PantallaResena(
                                productos = emptyList(),
                                onFinish = { pantallaActual = "usuario" }
                            )

                            "usuario" -> PantallaUsuario(
                                onLoginSuccess = { pantallaActual = "home" }
                            )

                            "busqueda" -> PantallaBusquedaMusica(
                                deezerViewModel = deezerViewModel,
                                onVolver = { pantallaActual = "home" }
                            )
                        }
                    }
                }
            }
        }
    }
}



