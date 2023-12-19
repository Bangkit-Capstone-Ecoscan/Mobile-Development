package com.example.ecoscan.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.ecoscan.R
import com.example.ecoscan.ui.component.BottomBar
import com.example.ecoscan.ui.navigation.Screen
import com.example.ecoscan.ui.screen.bookmark.detail.DetailBookmarkScreen
import com.example.ecoscan.ui.screen.bookmark.fakedetail.FakeDetailBookScreen
import com.example.ecoscan.ui.screen.bookmark.main.BookmarkScreen
import com.example.ecoscan.ui.screen.detail.DetailScreen
import com.example.ecoscan.ui.screen.home.HomeScreen
import com.example.ecoscan.ui.screen.profile.ProfileScreen
import com.example.ecoscan.ui.screen.scan.ScanScreen
import com.example.ecoscan.ui.screen.settings.SettingScreen
import com.example.ecoscan.ui.screen.subscribe.SubscribeScreen
import com.example.ecoscan.ui.theme.Gold

@Composable
fun MainScreenHolder(
    navController: NavHostController = rememberNavController(),
) {

    val scaffoldState = rememberScaffoldState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val enabled by remember { mutableStateOf(true) }

    Scaffold(
        bottomBar = {
            if (Screen.useBottomBar.contains(currentRoute)) {
                BottomBar(navController = navController)
            }
        },
        floatingActionButton = {
            if (Screen.useBottomBar.contains(currentRoute)) {
                FloatingActionButton(
                    onClick = {
                        if (enabled) {
                            navController.navigate(Screen.Scan.route)
                        } else {
                            Color.Black
                        }
                    },
                    backgroundColor = Gold
                ) {
                    Icon(
                        modifier = Modifier
                            .size(25.dp),
                        painter = painterResource(id = R.drawable.scanicon),
                        contentDescription = "scan",
                        tint = Color.White
                    )
                }

            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        scaffoldState = scaffoldState
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(it)
        ) {

            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToSubscribe = {
                        navController.navigate(Screen.Subscribe.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    navigateToSetting = {
                        navController.navigate(Screen.Setting.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    navigateToDetail = { id->
                        navController.navigate(Screen.Detail.createRoute(id)){
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable(Screen.Scan.route) {
                ScanScreen(
                    navigateToResult = {
                        navController.navigate(Screen.FakeDetailBook.route) {
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable(Screen.Profile.route) {
                ProfileScreen(
                    navigateToBoorkmark = {navController.navigate(Screen.Bookmark.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                    } }
                )
            }

            composable(Screen.Subscribe.route) {
                SubscribeScreen(
                    backNavigation = { navController.navigateUp() }
                )
            }

            composable(Screen.Setting.route) {
                SettingScreen(
                    backNavigation = { navController.navigateUp() }
                )
            }

            composable(Screen.Bookmark.route) {
                BookmarkScreen(
                    backNavigation = { navController.navigate(Screen.Profile.route) },

                    navigateToDetail = { id->
                        navController.navigate(Screen.DetailBookmark.createRoute(id)){
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
//                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }

            composable(Screen.FakeDetailBook.route) {
                FakeDetailBookScreen()
            }

            composable(Screen.DetailBookmark.route) {dataId ->
                val id = dataId.arguments?.getString("id") ?: ""
                DetailBookmarkScreen(
                    id = id
                )
            }

            composable(Screen.Detail.route){itNv->
                val id = itNv.arguments?.getString("id") ?: ""
                DetailScreen(id = id)
            }

        }
    }
}