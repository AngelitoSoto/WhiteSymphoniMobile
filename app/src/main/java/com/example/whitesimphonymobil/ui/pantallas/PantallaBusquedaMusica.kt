package com.example.whitesimphonymobil.ui.pantallas

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.whitesimphonymobil.ui.viewmodel.DeezerViewModel
import androidx.activity.compose.BackHandler

@Composable
fun PantallaBusquedaMusica(
    deezerViewModel: DeezerViewModel,
    onVolver: () -> Unit
) {
    BackHandler {
        onVolver()
    }

    var query by remember { mutableStateOf("") }
    val tracks by deezerViewModel.tracks.collectAsState()
    var isLoading by remember { mutableStateOf(false) }
    val mediaPlayer = remember { MediaPlayer() }

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()
    ) {

        Text("Buscar música online 🎧", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = query,
            onValueChange = { query = it },
            label = { Text("Escribe el nombre de una canción o artista") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                if (query.isNotBlank()) {
                    isLoading = true
                    deezerViewModel.search(query)
                    isLoading = false
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar")
        }

        Spacer(modifier = Modifier.height(16.dp))


        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }


        if (!isLoading && tracks.isEmpty()) {
            Text(
                "No hay resultados. Prueba otra búsqueda 🎶",
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }


        LazyColumn {
            items(tracks) { track ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {


                        Image(
                            painter = rememberAsyncImagePainter(track.album.cover),
                            contentDescription = null,
                            modifier = Modifier.size(70.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Text(track.title, fontWeight = FontWeight.Bold)
                            Text("Artista: ${track.artist.name}")
                            Text("Álbum: ${track.album.title}")
                        }


                        Button(
                            onClick = {
                                try {
                                    mediaPlayer.reset()
                                    mediaPlayer.setDataSource(track.preview)
                                    mediaPlayer.prepare()
                                    mediaPlayer.start()
                                } catch (_: Exception) { }
                            }
                        ) {
                            Text("▶️")
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = onVolver,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Volver")
        }
    }
}
