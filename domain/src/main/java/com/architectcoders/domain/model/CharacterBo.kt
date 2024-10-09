package com.architectcoders.domain.model

data class CharacterBo (
    val image: String,
    val gender: String,
    val species: String,
    val name: String,
    val locationName: String,
    val id: Long,
    val status: String
)