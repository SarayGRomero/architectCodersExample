package com.architectcoders.rickandmortyapp.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.architectcoders.domain.model.CharacterBo
import com.architectcoders.rickandmortyapp.di.CharacterId
import com.architectcoders.usecases.GetCharacterByIDUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    @CharacterId private val characterId: Long,
    private val getCharacterByIDUseCase: GetCharacterByIDUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state: StateFlow<DetailState> = _state.asStateFlow()

    init {
        getCharacterByID(characterId)
    }

    private fun getCharacterByID(characterId: Long) {
        viewModelScope.launch {
            getCharacterByIDUseCase(characterId)
                .catch { error -> onError(error) }
                .collect { character -> onSuccess(character) }
        }
    }

    private fun onSuccess(character: CharacterBo) {
        _state.value = DetailState(character = character)
    }

    private fun onError(error: Throwable) {
        _state.value = DetailState(error = error)
    }
}