package com.architectcoders.rickandmortyapp.ui.screens.detail

import com.architectcoders.domain.model.CharacterBo


data class DetailState(
    val character: CharacterBo? = null,
    val error: Throwable? = null,
)