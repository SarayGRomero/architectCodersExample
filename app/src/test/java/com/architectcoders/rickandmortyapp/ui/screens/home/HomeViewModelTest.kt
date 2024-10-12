package com.architectcoders.rickandmortyapp.ui.screens.home

import app.cash.turbine.test
import com.architectcoders.rickandmortyapp.testrules.CoroutinesTestRule
import com.architectcoders.testshared.sampleCharacter
import com.architectcoders.usecases.GetCharactersUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@ExtendWith(MockitoExtension::class)
@ExtendWith(CoroutinesTestRule::class)
class HomeViewModelTest {

    @Mock
    lateinit var getCharactersUseCase: GetCharactersUseCase

    private lateinit var vm: HomeViewModel

    private val characters = listOf(sampleCharacter.copy(id = 1))

    @AfterEach
    fun tearDown() {
        CoroutinesTestRule().clear()
    }

    @Test
    fun `Progress is shown when screen starts and hidden when it finishes requesting movies`() = runTest {
        vm = buildViewModel()

        vm.state.test {
            assertEquals(HomeState(loading = true, characters = null), awaitItem())
            assertEquals(HomeState(characters = characters, loading = false), awaitItem())
            cancel()
        }
    }

    @Test
    fun `Characters are requested when screen starts`() = runTest {
        vm = buildViewModel()
        runCurrent()

        verify(getCharactersUseCase).invoke()
    }

    private fun buildViewModel(): HomeViewModel {
        whenever(getCharactersUseCase()).thenReturn(flowOf(characters))
        return HomeViewModel(getCharactersUseCase)
    }
}