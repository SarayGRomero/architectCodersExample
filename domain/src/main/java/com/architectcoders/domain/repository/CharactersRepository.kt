package com.architectcoders.domain.repository

import kotlinx.coroutines.flow.Flow
import com.architectcoders.domain.model.CharacterBo

interface CharactersRepository {
    val characters: Flow<List<CharacterBo>>

    suspend fun fetchAllCharacters()

    fun findById(id: Long): Flow<CharacterBo>
}