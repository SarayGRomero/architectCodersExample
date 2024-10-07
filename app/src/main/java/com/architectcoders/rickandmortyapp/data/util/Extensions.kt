package com.architectcoders.rickandmortyapp.data.util

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.architectcoders.rickandmortyapp.domain.model.Error
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> tryCall(action: suspend () -> T): Either<Throwable, T> = try {
    action().right()
} catch (e: Exception) {
    e.left()
}

fun <DTO, BO> Either<Throwable, DTO>.handleResponse(
    onSuccess: (DTO) -> BO
): BO {
    return this.fold({
        throw it
    }, {
        onSuccess.invoke(it)
    })
}