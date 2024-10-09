package com.architectcoders.usecases

import com.architectcoders.domain.model.CharacterBo
import com.architectcoders.domain.repository.CharactersRepository
import com.architectcoders.testshared.sampleCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class GetCharactersUseCaseTest {
    @Test
    fun invoke(): Unit = runBlocking {
        val charactersList: List<CharacterBo> = listOf(sampleCharacter.copy(id = 1))
        val getCharactersUseCase = GetCharactersUseCase(
            mock {
                on { characters } doReturn flowOf(charactersList)
            }
        )

        val result = getCharactersUseCase.invoke().first()

        assertEquals(charactersList, result)
    }

    @Test
    fun `calls fetchAllCharacters when characters flow is empty`() = runBlocking {
        val emptyCharactersFlow: Flow<List<CharacterBo>> = flowOf()

        val repository = mock<CharactersRepository> {
            on { characters } doReturn emptyCharactersFlow
            onBlocking { fetchAllCharacters() } doReturn Unit
        }

        val useCase = GetCharactersUseCase(repository)

        useCase.invoke().collect()

        verify(repository).fetchAllCharacters()
    }
}