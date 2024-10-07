package com.architectcoders.rickandmortyapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.architectcoders.rickandmortyapp.data.local.database.model.CharacterDbo

@Database(entities = [CharacterDbo::class], version = 1, exportSchema = false)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}