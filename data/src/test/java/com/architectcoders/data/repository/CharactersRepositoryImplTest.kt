package com.architectcoders.data.repository

import arrow.core.right
import com.architectcoders.data.datasource.LocalDataSource
import com.architectcoders.data.datasource.RemoteDataSource
import com.architectcoders.domain.model.CharacterBo
import com.architectcoders.domain.repository.CharactersRepository
import com.architectcoders.testshared.sampleCharacter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class CharactersRepositoryImplTest {

    private lateinit var charactersRepository: CharactersRepository

    @Mock
    private lateinit var localDataSource: LocalDataSource

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource

    private val localCharacters: Flow<List<CharacterBo>> = flowOf(listOf(sampleCharacter.copy(id = 1)))

    @BeforeEach
    fun setUp() {
        charactersRepository = CharactersRepositoryImpl(remoteDataSource, localDataSource)
    }

    @Test
    fun `Characters are taken from local data source if available`(): Unit = runBlocking {
        whenever(localDataSource.characters).thenReturn(localCharacters)

        val result = charactersRepository.characters

        assertEquals(localCharacters, result)
    }

    @Test
    fun `Characters are taken from remote data source if local data source is empty`(): Unit = runBlocking {
        val remoteCharacters = listOf(sampleCharacter.copy(id = 2))
        whenever(localDataSource.isEmpty()).thenReturn(true)
        whenever(remoteDataSource.getAllCharacters()).thenReturn(remoteCharacters.right())

        charactersRepository.fetchAllCharacters()

        verify(localDataSource).saveCharacters(remoteCharacters)
    }

    @Test
    fun `Finding a character by id in local data source`(): Unit = runBlocking {
        val character: Flow<CharacterBo> = flowOf(sampleCharacter.copy(id = 10))
        whenever(localDataSource.findById(10)).thenReturn(character)

        val result = charactersRepository.findById(10)

        assertEquals(
            character,
            result
        )
    }
}