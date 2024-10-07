package com.architectcoders.rickandmortyapp.data.local.database.mapper

import com.architectcoders.rickandmortyapp.data.local.database.model.CharacterDbo
import com.architectcoders.rickandmortyapp.domain.model.CharacterBo

fun CharacterDbo.toBo() = CharacterBo(
    id = id,
    name = name,
    status = status,
    species = species,
    gender = gender,
    locationName = locationName,
    image = image
)

fun CharacterBo.toDbo() = CharacterDbo(
    id = id,
    name = name,
    status = status,
    species = species,
    gender = gender,
    locationName = locationName,
    image = image
)

fun List<CharacterDbo>.toBo() = map { it.toBo() }

fun List<CharacterBo>.toDbo() = map { it.toDbo() }