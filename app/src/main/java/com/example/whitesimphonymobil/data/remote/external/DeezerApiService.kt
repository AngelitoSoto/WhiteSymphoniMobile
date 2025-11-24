package com.example.whitesimphonymobil.data.remote.external


import com.example.whitesimphonymobil.data.remote.dto.DeezerResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface DeezerApiService {

    @GET("search")
    suspend fun searchTracks(
        @Query("q") query: String
    ): DeezerResponseDto
}

