package com.architectcoders.rickandmortyapp.domain.repository

import com.architectcoders.rickandmortyapp.domain.model.CharacterBo

interface CharactersRepository {
    suspend fun getAllCharacters(): List<CharacterBo>
}