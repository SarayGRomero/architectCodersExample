package com.architectcoders.rickandmortyapp.di

import com.architectcoders.data.repository.CharactersRepositoryImpl
import com.architectcoders.domain.repository.CharactersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppDataModule {
    @Binds
    abstract fun bindCharactersRepository(charactersRepository: CharactersRepositoryImpl): CharactersRepository
}
