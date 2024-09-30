package com.architectcoders.rickandmortyapp.usecase

import com.architectcoders.rickandmortyapp.domain.model.CharacterBo
import com.architectcoders.rickandmortyapp.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository
) {
    operator fun invoke(): Flow<List<CharacterBo>> = flow { emit(repository.getAllCharacters()) }
}