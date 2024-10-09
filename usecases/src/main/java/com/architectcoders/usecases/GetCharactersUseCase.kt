package com.architectcoders.usecases

import com.architectcoders.domain.model.CharacterBo
import com.architectcoders.domain.repository.CharactersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEmpty
import javax.inject.Inject

class GetCharactersUseCase @Inject constructor(
    private val repository: CharactersRepository,
) {
    operator fun invoke(): Flow<List<CharacterBo>> =
        repository.characters.onEmpty { repository.fetchAllCharacters() }
}