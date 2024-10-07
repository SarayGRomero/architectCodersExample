package com.architectcoders.rickandmortyapp.ui.screens.home

import com.architectcoders.rickandmortyapp.domain.model.CharacterBo

data class HomeState(
    val loading: Boolean = false,
    val characters: List<CharacterBo>? = null,
    val error: Throwable? = null
)