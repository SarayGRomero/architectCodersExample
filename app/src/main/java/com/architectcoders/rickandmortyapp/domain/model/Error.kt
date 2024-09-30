package com.architectcoders.rickandmortyapp.domain.model

sealed interface Error {
    class Server(val code: Int) : Error, Throwable()
    data object Connectivity : Error, Throwable()
    class Unknown(override val message: String) : Error, Throwable()
}