package com.architectcoders.data.datasource

import arrow.core.Either
import com.architectcoders.domain.model.CharacterBo

fun interface RemoteDataSource {
    suspend fun getAllCharacters(): Either<Throwable, List<CharacterBo>>
}