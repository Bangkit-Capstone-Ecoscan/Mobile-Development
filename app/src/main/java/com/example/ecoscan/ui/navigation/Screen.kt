package com.example.ecoscan.ui.navigation

sealed class Screen (val route: String) {
    object Home: Screen("home")

    object Scan: Screen("scan")

    object Profile: Screen("profile")

    object Register: Screen("register")

    object Login: Screen("login")

    object Splash: Screen("splash")

    object Subscribe: Screen("subscribe")

    object Bookmark: Screen("bookmark")

    object DetailBookmark: Screen("detailBookmark")
    object Setting: Screen("setting")

    object Detail: Screen("Detail/{id}"){
        fun createRoute(id: String) = "Detail/$id"
    }


}