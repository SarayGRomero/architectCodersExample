package com.architectcoders.rickandmortyapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.architectcoders.rickandmortyapp.domain.model.CharacterBo
import com.architectcoders.rickandmortyapp.usecase.GetCharactersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state: StateFlow<HomeState> = _state.asStateFlow()

    fun getCharacters() {
        viewModelScope.launch {
            showLoading()
            getCharactersUseCase()
                .catch { cause -> onError(cause) }
                .collect { data -> onSuccess(data) }
        }
    }

    private fun onSuccess(data: List<CharacterBo>) {
        _state.update {
            HomeState(
                characters = data,
                loading = false
            )
        }
    }

    private fun onError(cause: Throwable) {
        _state.update {
            HomeState(
                error = cause,
                loading = false
            )
        }
    }

    private fun showLoading() {
        _state.value = _state.value.copy(loading = true)
    }
}