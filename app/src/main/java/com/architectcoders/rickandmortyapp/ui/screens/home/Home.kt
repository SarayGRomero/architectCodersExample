package com.architectcoders.rickandmortyapp.ui.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.architectcoders.domain.model.CharacterBo
import com.architectcoders.rickandmortyapp.R
import com.architectcoders.rickandmortyapp.ui.screens.Screen
import com.architectcoders.rickandmortyapp.ui.screens.common.ErrorText
import com.architectcoders.rickandmortyapp.ui.screens.common.Loading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(vm: HomeViewModel = hiltViewModel(), onCharacterClick: (CharacterBo) -> Unit) {

    val state by vm.state.collectAsState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Screen {
        Scaffold(
            topBar = { HomeTopAppBar(scrollBehavior = scrollBehavior) },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { padding ->

            if (state.loading) Loading(modifier = Modifier.padding(padding))

            state.characters?.let {
                CharactersGrid(
                    characters = it,
                    onClick = onCharacterClick,
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
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopAppBar(scrollBehavior: TopAppBarScrollBehavior) {
    TopAppBar(
        title = { Text(stringResource(id = R.string.app_name)) },
        scrollBehavior = scrollBehavior
    )
}