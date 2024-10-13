package com.architectcoders.rickandmortyapp.ui.screens.detail

import app.cash.turbine.test
import com.architectcoders.apptestshared.buildLocalCharacters
import com.architectcoders.apptestshared.buildRepositoryWith
import com.architectcoders.domain.model.CharacterBo
import com.architectcoders.rickandmortyapp.data.local.database.model.CharacterDbo
import com.architectcoders.rickandmortyapp.data.remote.model.CharacterDto
import com.architectcoders.rickandmortyapp.testrules.CoroutinesTestRule
import com.architectcoders.usecases.GetCharacterByIDUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(CoroutinesTestRule::class)
class DetailIntegrationTests {

    private lateinit var vm: DetailViewModel

    @Test
    fun `UI is updated with the movie on start`() = runTest {
        vm = buildViewModelWith(
            id = 10,
            localData = buildLocalCharacters(10),
        )

        vm.state.test {
            assertEquals(DetailState(), awaitItem())
            assertEquals(10, awaitItem().character!!.id)
            cancel()
        }
    }

    private fun buildViewModelWith(
        id: Long,
        localData: List<CharacterDbo> = emptyList(),
        remoteData: List<CharacterDto> = emptyList()
    ): DetailViewModel {
        val charactersRepository = buildRepositoryWith(localData, remoteData)
        val getCharacterByIDUseCase = GetCharacterByIDUseCase(charactersRepository)
        return DetailViewModel(id, getCharacterByIDUseCase)
    }
}