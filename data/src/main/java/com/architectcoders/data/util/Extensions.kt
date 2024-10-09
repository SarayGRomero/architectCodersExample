package com.architectcoders.data.util

import arrow.core.Either
import arrow.core.left
import arrow.core.right

suspend fun <T> tryCall(action: suspend () -> T): Either<Throwable, T> = try {
    action().right()
} catch (e: Exception) {
    e.left()
}

suspend fun <DTO, BO> Either<Throwable, DTO>.handleResponse(
    onSuccess: suspend (DTO) -> BO,
): BO {
    return this.fold({
        throw it
    }, {
        onSuccess.invoke(it)
    })
}