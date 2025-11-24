package com.example.whitesimphonymobil.data.remote.dto

data class TrackDto(
    val title: String,
    val artist: ArtistDto,
    val album: AlbumDto,
    val preview: String
)