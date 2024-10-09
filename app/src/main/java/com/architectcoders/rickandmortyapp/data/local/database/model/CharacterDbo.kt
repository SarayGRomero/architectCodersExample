package com.architectcoders.rickandmortyapp.data.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterDbo(
    @PrimaryKey val id: Long,
    val image: String,
    val gender: String,
    val species: String,
    val name: String,
    val locationName: String,
    val status: String,
)