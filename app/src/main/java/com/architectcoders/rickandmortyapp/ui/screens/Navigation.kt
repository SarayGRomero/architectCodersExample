package com.architectcoders.rickandmortyapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.architectcoders.rickandmortyapp.ui.screens.home.Home

sealed class Screen(val route: String) {
    object Main : Screen("main")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Main.route) {
        // TODO Add navigation when the Detail screen has been implemented
        composable(Screen.Main.route) {
            Home(onCharacterClick = {})
        }
    }
}