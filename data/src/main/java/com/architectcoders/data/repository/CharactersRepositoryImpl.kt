package com.architectcoders.data.repository

import com.architectcoders.domain.model.CharacterBo
import com.architectcoders.domain.repository.CharactersRepository
import com.architectcoders.data.datasource.LocalDataSource
import com.architectcoders.data.datasource.RemoteDataSource
import com.architectcoders.data.util.handleResponse
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : CharactersRepository {

    override val characters: Flow<List<CharacterBo>>
        get() = localDataSource.characters

    override suspend fun fetchAllCharacters() {
        if (localDataSource.isEmpty()) {
            remoteDataSource.getAllCharacters().handleResponse {
                localDataSource.saveCharacters(it)
            }
        }
    }

    override fun findById(id: Long): Flow<CharacterBo> = localDataSource.findById(id)
}