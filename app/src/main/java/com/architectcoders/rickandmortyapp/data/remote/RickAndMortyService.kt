package com.architectcoders.rickandmortyapp.data.remote

import com.architectcoders.rickandmortyapp.data.remote.model.CharactersResultDto
import retrofit2.http.GET

interface RickAndMortyService {
    @GET("api/character")
    suspend fun getAllCharacters(): CharactersResultDto
}