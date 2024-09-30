package com.architectcoders.rickandmortyapp.ui.screens.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.architectcoders.rickandmortyapp.R
import com.architectcoders.rickandmortyapp.domain.exceptions.NoDataFoundException

@Composable
fun ErrorText(error: Throwable, modifier: Modifier) {
    Box(modifier = modifier.fillMaxSize()) {
        Text(
            text = error.toUiString(),
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
private fun Throwable.toUiString() = when(this) {
    is NoDataFoundException -> stringResource(id = R.string.no_data_found_error)
    else -> stringResource(id = R.string.unknown_error)
}