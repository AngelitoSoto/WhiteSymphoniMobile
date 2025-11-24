package com.example.whitesimphonymobil

import com.example.whitesimphonymobil.data.remote.dto.*
import com.example.whitesimphonymobil.data.remote.external.DeezerApiService
import com.example.whitesimphonymobil.ui.viewmodel.DeezerViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DeezerViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val testDispatcher = StandardTestDispatcher()
    private val api = mockk<DeezerApiService>()

    @Test
    fun `cuando search es llamado, actualiza la lista de tracks`() = runTest {

        val fakeTrack = TrackDto(
            title = "Song Test",
            artist = ArtistDto("Artist X"),
            album = AlbumDto("Album Y", "cover.jpg"),
            preview = "audio.mp3"
        )

        val fakeResponse = DeezerResponseDto(data = listOf(fakeTrack))

        coEvery { api.searchTracks("test") } returns fakeResponse

        val viewModel = DeezerViewModel(api, dispatcher = StandardTestDispatcher(testScheduler))

        viewModel.search("test")

        advanceUntilIdle()

        val resultado = viewModel.tracks.value

        assertEquals(1, resultado.size)
        assertEquals("Song Test", resultado[0].title)
    }
}
