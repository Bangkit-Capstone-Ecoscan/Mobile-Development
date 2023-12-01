package com.example.ecoscan.ui.navigation

sealed class Screen (val route: String) {
    object Home: Screen("home")

    object Scan: Screen("scan")

    object Profile: Screen("profile")

    object Register: Screen("register")

    object Login: Screen("login")

    object Splash: Screen("splash")

    object Subscribe: Screen("subscribe")


}