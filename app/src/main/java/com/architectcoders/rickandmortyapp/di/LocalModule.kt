package com.architectcoders.rickandmortyapp.di

import android.app.Application
import androidx.room.Room
import com.architectcoders.rickandmortyapp.data.local.database.CharacterDatabase
import com.architectcoders.rickandmortyapp.data.local.database.datasource.LocalDataSource
import com.architectcoders.rickandmortyapp.data.local.database.datasource.LocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application) = Room.databaseBuilder(
        app,
        CharacterDatabase::class.java,
        "characters-db"
    ).build()

    @Provides
    @Singleton
    fun provideCharacterDao(db: CharacterDatabase) = db.characterDao()
}

@Module
@InstallIn(SingletonComponent::class)
abstract class AppLocalDataModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSource: LocalDataSourceImpl): LocalDataSource
}