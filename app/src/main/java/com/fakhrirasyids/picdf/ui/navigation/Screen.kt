package com.fakhrirasyids.picdf.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Starter : Screen("starter")
    object Home : Screen("home")
}