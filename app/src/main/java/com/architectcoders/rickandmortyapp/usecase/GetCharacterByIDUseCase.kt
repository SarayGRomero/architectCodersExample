package com.architectcoders.rickandmortyapp.usecase

import com.architectcoders.rickandmortyapp.domain.repository.CharactersRepository
import javax.inject.Inject

class GetCharacterByIDUseCase @Inject constructor(
    private val charactersRepository: CharactersRepository,
) {
    operator fun invoke(id: Long) = charactersRepository.findById(id)
}