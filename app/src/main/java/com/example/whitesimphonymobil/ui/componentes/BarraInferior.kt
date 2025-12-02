package com.example.whitesymphonymobil.ui.componentes

import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.material.icons.filled.ShoppingCart

@Composable
fun BarraInferior(
    onHomeClick: () -> Unit,
    onUserClick: () -> Unit,
    onProductosClick: () -> Unit,
    currentScreen: String
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentScreen == "home",
            onClick = onHomeClick,
            icon = { Icon(Icons.Filled.Home, contentDescription = "Inicio") },
            label = { Text("Inicio") }
        )
        NavigationBarItem(
            selected = currentScreen == "productos",
            onClick = onProductosClick,
            icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Productos") },
            label = { Text("Productos") }
        )

        NavigationBarItem(
            selected = currentScreen == "user",
            onClick = onUserClick,
            icon = { Icon(Icons.Filled.Person, contentDescription = "Usuario") },
            label = { Text("Usuario") }
        )
    }
}