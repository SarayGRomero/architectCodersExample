package com.architectcoders.rickandmortyapp.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.architectcoders.rickandmortyapp.data.local.database.model.CharacterDbo
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM CharacterDbo")
    fun getAllCharacters(): Flow<List<CharacterDbo>>

    @Query("SELECT * FROM CharacterDbo WHERE id = :id")
    fun findById(id: Long): Flow<CharacterDbo>

    @Query("SELECT COUNT(id) FROM CharacterDbo")
    suspend fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveCharacters(characters: List<CharacterDbo>)
}