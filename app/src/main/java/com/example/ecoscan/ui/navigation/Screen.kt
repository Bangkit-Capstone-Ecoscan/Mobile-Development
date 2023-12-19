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

    object FakeDetailBook: Screen("fakeDetailBook")

    object DetailBookmark: Screen("DetailBookmark/{id}") {
        fun createRoute(id: String) = "DetailBookmark/$id"
    }
    object Setting: Screen("setting")

    object Detail: Screen("Detail/{id}"){
        fun createRoute(id: String) = "Detail/$id"
    }

    companion object {
        val useBottomBar = listOf(
            Home.route,
            Scan.route,
            Profile.route
        )
    }


}