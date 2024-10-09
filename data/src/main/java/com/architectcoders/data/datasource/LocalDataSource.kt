package com.architectcoders.data.datasource

import com.architectcoders.domain.model.CharacterBo
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    val characters: Flow<List<CharacterBo>>

    suspend fun isEmpty(): Boolean

    suspend fun saveCharacters(characters: List<CharacterBo>)

    fun findById(id: Long): Flow<CharacterBo>
}