@file:Suppress("MissingPermission")
package com.example.whitesimphonymobil.ui.pantallas

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.delay
import com.example.whitesymphonymobil.utils.mostrarNotificacion

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaPago(onPagoFinalizado: () -> Unit) {
    var procesando by remember { mutableStateOf(true) }
    val context = LocalContext.current


    val notificationPermissionLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                mostrarNotificacion(
                    context,
                    titulo = "Pago exitoso",
                    mensaje = "Tu pago se ha realizado correctamente ✅"
                )
            }
        }


    LaunchedEffect(Unit) {
        delay(3000)
        procesando = false


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        } else {
            mostrarNotificacion(
                context,
                titulo = "Pago exitoso",
                mensaje = "Tu pago se ha realizado correctamente ✅"
            )
        }

        delay(2000)
        onPagoFinalizado()
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        if (procesando) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(16.dp))
                Text("Procesando pago...", fontSize = 18.sp)
            }
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Pago realizado",
                    tint = Color(0xFF4CAF50),
                    modifier = Modifier.size(64.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "¡Pago realizado con éxito!",
                    fontSize = 20.sp,
                    color = Color(0xFF4CAF50)
                )
            }
        }
    }
}


fun mostrarNotificacion(context: Context, titulo: String, mensaje: String) {
    val channelId = "pagos_channel"
    val notificationId = 1

    val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel = NotificationChannel(
            channelId,
            "Notificaciones de pago",
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = "Canal para confirmar pagos exitosos"
        }
        notificationManager.createNotificationChannel(channel)
    }


    val builder = NotificationCompat.Builder(context, channelId)
        .setSmallIcon(android.R.drawable.stat_sys_upload_done)
        .setContentTitle(titulo)
        .setContentText(mensaje)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)


    with(NotificationManagerCompat.from(context)) {
        notify(notificationId, builder.build())
    }
}


