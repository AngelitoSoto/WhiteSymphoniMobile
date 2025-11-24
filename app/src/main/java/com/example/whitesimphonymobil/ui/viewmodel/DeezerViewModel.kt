package com.example.whitesimphonymobil.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.whitesimphonymobil.data.remote.dto.TrackDto
import com.example.whitesimphonymobil.data.remote.dto.DeezerResponseDto
import com.example.whitesimphonymobil.data.remote.external.DeezerApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import com.example.whitesimphonymobil.data.remote.external.ApiClientDeezer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class DeezerViewModel(
    private val api: DeezerApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {

    private val _tracks = MutableStateFlow<List<TrackDto>>(emptyList())
    val tracks: StateFlow<List<TrackDto>> = _tracks

    fun search(query: String) {
        viewModelScope.launch(dispatcher) {
            val response = api.searchTracks(query)
            _tracks.value = response.data
        }
    }
}




