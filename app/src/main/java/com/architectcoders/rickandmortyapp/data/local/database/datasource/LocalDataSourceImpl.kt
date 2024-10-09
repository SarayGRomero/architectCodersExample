package com.architectcoders.rickandmortyapp.data.local.database.datasource

import com.architectcoders.data.datasource.LocalDataSource
import com.architectcoders.data.util.handleResponse
import com.architectcoders.data.util.tryCall
import com.architectcoders.domain.model.CharacterBo
import com.architectcoders.rickandmortyapp.data.local.database.CharacterDao
import com.architectcoders.rickandmortyapp.data.local.database.mapper.toBo
import com.architectcoders.rickandmortyapp.data.local.database.mapper.toDbo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

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