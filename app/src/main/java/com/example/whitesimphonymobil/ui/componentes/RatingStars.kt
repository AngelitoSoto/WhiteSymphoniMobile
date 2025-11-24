package com.example.whitesymphonymobil.ui.componentes

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import kotlin.math.roundToInt

@Composable
fun RatingStars(rating: Double) {
    val rounded = rating.roundToInt()
    Row {
        for (i in 1..5) {
            Icon(
                imageVector = if (i <= rounded) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
