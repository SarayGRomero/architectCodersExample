package com.architectcoders.rickandmortyapp.data.remote.datasource

import arrow.core.Either
import com.architectcoders.data.datasource.RemoteDataSource
import com.architectcoders.data.util.tryCall
import com.architectcoders.domain.model.CharacterBo
import com.architectcoders.rickandmortyapp.data.remote.RickAndMortyService
import com.architectcoders.rickandmortyapp.data.remote.mapper.toBo
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val rickAndMortyService: RickAndMortyService,
) : RemoteDataSource {
    override suspend fun getAllCharacters(): Either<Throwable, List<CharacterBo>> = tryCall {
        rickAndMortyService.getAllCharacters().characters.toBo()
    }
}