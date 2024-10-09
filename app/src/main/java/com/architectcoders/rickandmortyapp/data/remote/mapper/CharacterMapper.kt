package com.architectcoders.rickandmortyapp.data.remote.mapper

import com.architectcoders.domain.model.CharacterBo
import com.architectcoders.rickandmortyapp.data.remote.model.CharacterDto
import com.architectcoders.rickandmortyapp.util.checkNull

fun List<CharacterDto>.toBo(): List<CharacterBo> = this.map { it.toBo() }

fun CharacterDto.toBo(): CharacterBo =
    CharacterBo(
        image = image.checkNull(),
        gender = gender.checkNull(),
        species = species.checkNull(),
        name = name.checkNull(),
        locationName = locationDto.takeIf { it != null }?.name.checkNull(),
        id = id,
        status = status.checkNull()
    )