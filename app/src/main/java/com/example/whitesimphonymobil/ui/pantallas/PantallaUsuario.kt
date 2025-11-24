package com.example.whitesimphonymobil.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class LoginResult(
    val emailError: String? = null,
    val passwordError: String? = null,
    val isValid: Boolean = false
)

object LoginValidator {
    private val emailPattern = Regex("^.+@.+\\..+\$")

    fun validate(email: String, password: String): LoginResult {
        var emailErr: String? = null
        var passErr: String? = null

        if (!emailPattern.matches(email)) {
            emailErr = "Correo inválido, debe contener '@' y un dominio"
        }

        if (password.length !in 6..12) {
            passErr = "Contraseña debe tener entre 6 y 12 caracteres"
        }

        return LoginResult(
            emailError = emailErr,
            passwordError = passErr,
            isValid = emailErr == null && passErr == null
        )
    }
}

@Composable
fun PantallaUsuario(onLoginSuccess: () -> Unit = {}) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var loginResult by remember { mutableStateOf(LoginResult()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Iniciar Sesión", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electrónico") },
            isError = loginResult.emailError != null,
            modifier = Modifier.fillMaxWidth()
        )
        loginResult.emailError?.let {
            Text(it, color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            isError = loginResult.passwordError != null,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        loginResult.passwordError?.let {
            Text(it, color = Color.Red, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                loginResult = LoginValidator.validate(email, password)
                if (loginResult.isValid) {
                    onLoginSuccess()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }
    }
}
