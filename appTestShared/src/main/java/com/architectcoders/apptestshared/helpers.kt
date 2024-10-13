package com.architectcoders.apptestshared

import com.architectcoders.data.repository.CharactersRepositoryImpl
import com.architectcoders.rickandmortyapp.data.local.database.datasource.LocalDataSourceImpl
import com.architectcoders.rickandmortyapp.data.local.database.model.CharacterDbo
import com.architectcoders.rickandmortyapp.data.remote.datasource.RemoteDataSourceImpl
import com.architectcoders.rickandmortyapp.data.remote.model.CharacterDto

fun buildRemoteCharacters(vararg id: Int) = id.map {
    CharacterDto(
        image = "",
        gender = "Male",
        species = "Human",
        origin = null,
        name = "Rick Sanchez $it",
        locationDto = null,
        id = it.toLong(),
        status = "Alive",
        episodes = emptyList()
    )
}

fun buildLocalCharacters(vararg id: Int) = id.map {
    CharacterDbo(
        image = "",
        gender = "Male",
        species = "Human",
        name = "Rick Sanchez $it",
        id = it.toLong(),
        status = "Alive",
        locationName = "Earth"
    )
}

fun buildRepositoryWith(
    localData: List<CharacterDbo> = emptyList(),
    remoteData: List<CharacterDto> = emptyList(),
): CharactersRepositoryImpl {
    val localDataSource = LocalDataSourceImpl(FakeCharacterDao(localData))
    val remoteDataSource = RemoteDataSourceImpl(FakeRickAndMortyService(remoteData))
    return CharactersRepositoryImpl(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource
    )
}