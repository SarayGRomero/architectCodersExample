package com.architectcoders.rickandmortyapp.ui.screens.detail

import app.cash.turbine.test
import com.architectcoders.rickandmortyapp.testrules.CoroutinesTestRule
import com.architectcoders.testshared.sampleCharacter
import com.architectcoders.usecases.GetCharacterByIDUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.whenever

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(CoroutinesTestRule::class)
@ExtendWith(MockitoExtension::class)
class DetailViewModelTest {

    @Mock
    lateinit var getCharacterByIDUseCase: GetCharacterByIDUseCase

    private lateinit var vm: DetailViewModel

    private val character = sampleCharacter.copy(id = 10)

    @Test
    fun `UI is updated with the movie on start`() = runTest {
        vm = buildViewModel()
        vm.state.test {
            assertEquals(DetailState(), awaitItem())
            assertEquals(DetailState(character = character), awaitItem())
            cancel()
        }
    }

    private fun buildViewModel(): DetailViewModel {
        whenever(getCharacterByIDUseCase(10)).thenReturn(flowOf(character))
        return DetailViewModel(10, getCharacterByIDUseCase)
    }
}