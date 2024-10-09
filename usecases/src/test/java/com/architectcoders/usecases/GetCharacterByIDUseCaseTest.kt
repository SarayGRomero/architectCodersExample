package com.architectcoders.usecases

import com.architectcoders.testshared.sampleCharacter
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class GetCharacterByIDUseCaseTest {

    @Test
    fun invoke(): Unit = runBlocking {
        val character = flowOf(sampleCharacter.copy(id = 1))
        val getCharacterByIdUseCase = GetCharacterByIDUseCase(
            mock {
                on { findById(1) } doReturn character
            }
        )

        val result = getCharacterByIdUseCase(1)

        assertEquals(character, result)
    }
}