package com.architectcoders.rickandmortyapp.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.architectcoders.rickandmortyapp.ui.theme.RickAndMortyAppTheme

@Composable
fun Screen(content: @Composable () -> Unit) {
    RickAndMortyAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = content
        )
    }
}