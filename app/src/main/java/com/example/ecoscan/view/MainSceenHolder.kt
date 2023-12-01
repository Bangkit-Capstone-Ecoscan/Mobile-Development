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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ecoscan.R
import com.example.ecoscan.ui.component.BottomBar
import com.example.ecoscan.ui.navigation.Screen
import com.example.ecoscan.ui.screen.home.HomeScreen
import com.example.ecoscan.ui.screen.profile.ProfileScreen
import com.example.ecoscan.ui.screen.scan.ScanScreen
import com.example.ecoscan.ui.screen.subscribe.SubscribeScreen
import com.example.ecoscan.ui.theme.Gold

@Composable
fun MainScreenHolder(
    navController: NavHostController = rememberNavController(),
) {

    val scaffoldState = rememberScaffoldState()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    var enabled by remember { mutableStateOf(true) }

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.Subscribe.route)
            BottomBar(navController = navController)
        },
        floatingActionButton = {
            if (currentRoute != Screen.Subscribe.route)
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
                        navController.navigate(Screen.Subscribe.route)
                    }
                )
            }

            composable(Screen.Scan.route) {
                ScanScreen()
            }

            composable(Screen.Profile.route) {
                ProfileScreen()
            }

            composable(Screen.Subscribe.route) {
                SubscribeScreen()
            }
        }
    }
}