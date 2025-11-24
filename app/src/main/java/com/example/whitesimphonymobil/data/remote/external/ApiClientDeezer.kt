package com.example.whitesimphonymobil.data.remote.external


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientDeezer {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.deezer.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
