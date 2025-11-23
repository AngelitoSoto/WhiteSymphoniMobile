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
import com.example.whitesymphonimobil.data.repository.CarritoRepository
import com.example.whitesymphonymobil.ui.viewmodel.CarritoViewModel

import com.example.whitesymphonymobil.ui.screens.PantallaPrincipal
import com.example.whitesymphonymobil.ui.screens.PantallaCarrito
import com.example.whitesymphonymobil.ui.pantallas.PantallaPago
import com.example.whitesymphonymobil.ui.screens.PantallaResena
import com.example.whitesymphonymobil.ui.screens.PantallaUsuario

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WhiteSymphonyTheme {


                var pantallaActual by remember { mutableStateOf("home") }


                val context = LocalContext.current
                val db = AppDatabase.get(context)
                val repo = CarritoRepository(db.cartDao())
                val carritoViewModel = remember { CarritoViewModel(repo) }

                Scaffold(
                    bottomBar = {
                        if (pantallaActual in listOf("home", "usuario")) {
                            BarraInferior(
                                onHomeClick = { pantallaActual = "home" },
                                onUserClick = { pantallaActual = "usuario" },
                                currentScreen = pantallaActual
                            )
                        }
                    }
                ) { innerPadding ->

                    Box(modifier = Modifier.padding(innerPadding)) {

                        when (pantallaActual) {


                            "home" -> PantallaPrincipal(
                                onIrCarrito = { pantallaActual = "carrito" },
                                viewModel = carritoViewModel
                            )


                            "carrito" -> PantallaCarrito(
                                viewModel = carritoViewModel,
                                onVolver = { pantallaActual = "home" }
                            )

                            // 💳 PAGO
                            "pago" -> PantallaPago {
                                pantallaActual = "resena"
                            }


                            "resena" -> PantallaResena(
                                productos = emptyList(),
                                onFinish = { pantallaActual = "usuario" }
                            )

                            // 👤 USUARIO
                            "usuario" -> PantallaUsuario(
                                onLoginSuccess = { pantallaActual = "home" }
                            )
                        }
                    }
                }
            }
        }
    }
}



