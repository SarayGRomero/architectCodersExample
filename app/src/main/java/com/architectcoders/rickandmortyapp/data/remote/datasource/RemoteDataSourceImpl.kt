package com.architectcoders.rickandmortyapp.data.remote.datasource

import arrow.core.Either
import com.architectcoders.rickandmortyapp.data.remote.RickAndMortyService
import com.architectcoders.rickandmortyapp.data.remote.model.CharacterDto
import com.architectcoders.rickandmortyapp.data.util.tryCall
import com.architectcoders.rickandmortyapp.domain.model.Error
import javax.inject.Inject

interface RemoteDataSource {
    suspend fun getAllCharacters(): Either<Throwable, List<CharacterDto>>
}

class RemoteDataSourceImpl @Inject constructor(
    private val rickAndMortyService: RickAndMortyService
) : RemoteDataSource {
    override suspend fun getAllCharacters(): Either<Throwable, List<CharacterDto>> = tryCall {
        rickAndMortyService.getAllCharacters().characters
    }
}