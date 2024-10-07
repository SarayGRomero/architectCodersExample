package com.architectcoders.rickandmortyapp.domain.repository

import com.architectcoders.rickandmortyapp.domain.model.CharacterBo
import kotlinx.coroutines.flow.Flow

interface CharactersRepository {
    val characters: Flow<List<CharacterBo>>

    suspend fun fetchAllCharacters()

    fun findById(id: Long): Flow<CharacterBo>
}