package com.architectcoders.rickandmortyapp.data.local.database.datasource

import com.architectcoders.rickandmortyapp.data.local.database.CharacterDao
import com.architectcoders.rickandmortyapp.data.local.database.mapper.toBo
import com.architectcoders.rickandmortyapp.data.local.database.mapper.toDbo
import com.architectcoders.rickandmortyapp.data.util.handleResponse
import com.architectcoders.rickandmortyapp.data.util.tryCall
import com.architectcoders.rickandmortyapp.domain.model.CharacterBo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface LocalDataSource {

    val characters: Flow<List<CharacterBo>>

    suspend fun isEmpty(): Boolean

    suspend fun saveCharacters(characters: List<CharacterBo>)

    fun findById(id: Long): Flow<CharacterBo>
}

class LocalDataSourceImpl @Inject constructor(
    private val characterDao: CharacterDao,
) : LocalDataSource {

    override val characters: Flow<List<CharacterBo>> = characterDao.getAllCharacters().map { it.toBo() }

    override suspend fun isEmpty(): Boolean = characterDao.getCount() == 0

    override suspend fun saveCharacters(characters: List<CharacterBo>) = tryCall {
        characterDao.saveCharacters(characters.toDbo())
    }.handleResponse {}

    override fun findById(id: Long): Flow<CharacterBo> = characterDao.findById(id).map { it.toBo() }
}