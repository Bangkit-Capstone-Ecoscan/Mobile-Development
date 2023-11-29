package com.example.ecoscan.ui.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.ecoscan.R
import com.example.ecoscan.ui.navigation.NavigationItem
import com.example.ecoscan.ui.navigation.Screen
import com.example.ecoscan.ui.theme.Gold

@Composable
fun BottomBar(
    navController: NavHostController,
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val navigationItems = listOf(
        NavigationItem(
            title = "home",
            image = ImageVector.vectorResource(id = R.drawable.homeicon),
            screen = Screen.Home
        ),
        NavigationItem(
            title = "profile",
            image = ImageVector.vectorResource(id = R.drawable.profile_icon),
            screen = Screen.Profile
        ),
    )

    androidx.compose.material.BottomAppBar(
        cutoutShape = CircleShape,
        contentColor = Color.Black,
        elevation = 50.dp,
        backgroundColor = Gold
    ) {
        navigationItems.forEachIndexed { index, item ->
            if (index == 1) {
                Spacer(modifier = Modifier.width(130.dp))
            }
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = item.image,
                        contentDescription = item.title,
                        tint = Color.White
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Black,
                    indicatorColor = Color.Black
                )

            )
        }
    }
}