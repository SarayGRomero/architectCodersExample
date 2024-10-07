package com.architectcoders.rickandmortyapp.data.remote.repository

import com.architectcoders.rickandmortyapp.data.remote.datasource.RemoteDataSource
import com.architectcoders.rickandmortyapp.data.remote.mapper.toBo
import com.architectcoders.rickandmortyapp.data.util.handleResponse
import com.architectcoders.rickandmortyapp.domain.model.CharacterBo
import com.architectcoders.rickandmortyapp.domain.repository.CharactersRepository
import javax.inject.Inject

class CharactersRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : CharactersRepository {
    override suspend fun getAllCharacters(): List<CharacterBo> =
        remoteDataSource.getAllCharacters().handleResponse {
            it.toBo()
        }
}