package com.architectcoders.rickandmortyapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.architectcoders.rickandmortyapp.ui.screens.detail.Detail
import com.architectcoders.rickandmortyapp.ui.screens.home.Home

sealed class Screen(val route: String) {
    data object Main : Screen("main")

    data object Detail : Screen("detail/{${NavArgs.ItemId.key}}") {
        fun createRoute(characterId: Long) = "detail/$characterId"
    }
}

enum class NavArgs(val key: String) {
    ItemId("itemId")
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Main.route) {
        composable(Screen.Main.route) {
            Home(onCharacterClick = { character ->
                navController.navigate(Screen.Detail.createRoute(character.id))
            })
        }

        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument(NavArgs.ItemId.key) { type = NavType.LongType })
        ) {
            Detail(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}