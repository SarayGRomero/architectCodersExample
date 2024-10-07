package com.architectcoders.rickandmortyapp.data.repository

import com.architectcoders.rickandmortyapp.data.local.database.datasource.LocalDataSource
import com.architectcoders.rickandmortyapp.data.remote.datasource.RemoteDataSource
import com.architectcoders.rickandmortyapp.data.remote.mapper.toBo
import com.architectcoders.rickandmortyapp.data.util.handleResponse
import com.architectcoders.rickandmortyapp.domain.model.CharacterBo
import com.architectcoders.rickandmortyapp.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : CharactersRepository {

    override val characters: Flow<List<CharacterBo>>
        get() = localDataSource.characters

    override suspend fun fetchAllCharacters() {
        if(localDataSource.isEmpty()) {
            val characters = remoteDataSource.getAllCharacters().handleResponse {
                localDataSource.saveCharacters(it)
            }
        }
    }

    override fun findById(id: Long): Flow<CharacterBo> = localDataSource.findById(id)
}