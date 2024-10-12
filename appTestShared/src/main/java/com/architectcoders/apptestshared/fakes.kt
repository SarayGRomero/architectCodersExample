package com.architectcoders.apptestshared

import com.architectcoders.rickandmortyapp.data.local.database.CharacterDao
import com.architectcoders.rickandmortyapp.data.local.database.model.CharacterDbo
import com.architectcoders.rickandmortyapp.data.remote.RickAndMortyService
import com.architectcoders.rickandmortyapp.data.remote.model.CharacterDto
import com.architectcoders.rickandmortyapp.data.remote.model.CharactersResultDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeCharacterDao(characters: List<CharacterDbo>) : CharacterDao {

    private val inMemoryCharacters = MutableStateFlow(characters)
    private lateinit var findCharacterFlow: MutableStateFlow<CharacterDbo>

    override fun getAllCharacters(): Flow<List<CharacterDbo>> = inMemoryCharacters

    override fun findById(id: Long): Flow<CharacterDbo> {
        findCharacterFlow = MutableStateFlow(inMemoryCharacters.value.first { it.id == id })
        return findCharacterFlow
    }

    override suspend fun getCount(): Int = inMemoryCharacters.value.count()

    override suspend fun saveCharacters(characters: List<CharacterDbo>) {
        inMemoryCharacters.value = characters

        if (::findCharacterFlow.isInitialized) {
            characters.firstOrNull { it.id == findCharacterFlow.value.id }?.let { findCharacterFlow.value = it }
        }
    }
}

class FakeRickAndMortyService(private var characters: List<CharacterDto>) : RickAndMortyService {
    override suspend fun getAllCharacters(): CharactersResultDto = CharactersResultDto(
        characters = characters,
        info = null
    )
}