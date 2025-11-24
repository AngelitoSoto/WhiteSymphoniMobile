package com.example.whitesymphonymobil.utils

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.annotation.SuppressLint

@SuppressLint("MissingPermission")
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

    try {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permiso = context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
            if (permiso == android.content.pm.PackageManager.PERMISSION_GRANTED) {
                NotificationManagerCompat.from(context).notify(notificationId, builder.build())
            } else {

                println("⚠️ Permiso de notificaciones no concedido.")
            }
        } else {

            NotificationManagerCompat.from(context).notify(notificationId, builder.build())
        }
    } catch (e: SecurityException) {

        e.printStackTrace()
        println("⚠️ No se pudo mostrar la notificación: permiso no concedido o error de seguridad.")
    }
}

