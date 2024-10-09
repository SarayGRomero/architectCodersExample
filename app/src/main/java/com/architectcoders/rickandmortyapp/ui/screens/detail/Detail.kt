package com.architectcoders.rickandmortyapp.ui.screens.detail

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.hilt.navigation.compose.hiltViewModel
import com.architectcoders.rickandmortyapp.ui.screens.common.ErrorText
import com.architectcoders.rickandmortyapp.util.checkNull

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Detail(vm: DetailViewModel = hiltViewModel(), onBackClick: () -> Unit) {

    val state by vm.state.collectAsState()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        topBar = {
            DetailTopAppBar(
                title = state.character?.name.checkNull(),
                onBackClick = onBackClick,
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { padding ->
        state.character?.let {
            CharacterContent(
                character = it,
                modifier = Modifier.padding(padding)
            )
        }

        state.error?.let {
            ErrorText(
                error = it,
                modifier = Modifier.padding(padding)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailTopAppBar(
    title: String,
    onBackClick: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    LargeTopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back to home"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}