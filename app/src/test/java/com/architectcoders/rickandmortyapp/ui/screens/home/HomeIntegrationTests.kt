package com.architectcoders.rickandmortyapp.ui.screens.home

import app.cash.turbine.test
import com.architectcoders.apptestshared.buildLocalCharacters
import com.architectcoders.apptestshared.buildRemoteCharacters
import com.architectcoders.apptestshared.buildRepositoryWith
import com.architectcoders.rickandmortyapp.data.local.database.model.CharacterDbo
import com.architectcoders.rickandmortyapp.data.remote.model.CharacterDto
import com.architectcoders.rickandmortyapp.testrules.CoroutinesTestRule
import com.architectcoders.usecases.GetCharactersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
@ExtendWith(CoroutinesTestRule::class)
class HomeIntegrationTests {

    @AfterEach
    fun tearDown() {
        CoroutinesTestRule().clear()
    }

    @Test
    fun `data is loaded from local source when local source is not empty`() = runTest {
        val localData = buildLocalCharacters(1, 2, 3)
        val remoteData = buildRemoteCharacters(4, 5, 6)
        val vm = buildViewModelWith(
            localData = localData,
            remoteData = remoteData
        )

        vm.state.test {
            assertEquals(HomeState(loading = true), awaitItem())

            val characters = awaitItem().characters!!
            assertEquals("Rick Sanchez 1", characters[0].name)
            assertEquals("Rick Sanchez 2", characters[1].name)
            assertEquals("Rick Sanchez 3", characters[2].name)

            cancel()
        }
    }


    private fun buildViewModelWith(
        localData: List<CharacterDbo> = emptyList(),
        remoteData: List<CharacterDto> = emptyList(),
    ): HomeViewModel {
        val charactersRepository = buildRepositoryWith(localData, remoteData)
        val getCharactersUseCase = GetCharactersUseCase(charactersRepository)
        return HomeViewModel(getCharactersUseCase)
    }
}