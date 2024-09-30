package com.architectcoders.rickandmortyapp.data.remote.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersResultDto(
    @Json(name = "results")
    val characters: List<CharacterDto>,
    val info: InfoDto?
)

@JsonClass(generateAdapter = true)
data class InfoDto(
    val count: Long?,
    val pages: Long?,
    val next: String?,
    val prev: String?
)

@JsonClass(generateAdapter = true)
data class CharacterDto(
    val image: String?,
    val gender: String?,
    val species: String?,
    val origin: LocationDto?,
    val name: String?,
    val locationDto: LocationDto?,
    val id: Long,
    val status: String?,
    @Json(name = "episode")
    val episodes: List<String>?
)

@JsonClass(generateAdapter = true)
data class LocationDto(
    val name: String?,
    val url: String?
)