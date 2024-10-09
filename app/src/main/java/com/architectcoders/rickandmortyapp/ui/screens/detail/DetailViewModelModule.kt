package com.architectcoders.rickandmortyapp.ui.screens.detail

import androidx.lifecycle.SavedStateHandle
import com.architectcoders.rickandmortyapp.di.CharacterId
import com.architectcoders.rickandmortyapp.ui.screens.NavArgs
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DetailViewModelModule {

    @Provides
    @ViewModelScoped
    @CharacterId
    fun provideCharacterId(savedStateHandle: SavedStateHandle): Long =
        savedStateHandle[NavArgs.ItemId.key] ?: -1
}