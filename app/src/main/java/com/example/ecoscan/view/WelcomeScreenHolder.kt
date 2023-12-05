package com.example.ecoscan.view

import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ecoscan.MainActivity
import com.example.ecoscan.ui.navigation.Screen
import com.example.ecoscan.ui.screen.login.LoginScreen
import com.example.ecoscan.ui.screen.register.RegisterScreen
import com.example.ecoscan.ui.screen.splash.SplashScreen

@Composable
fun WelcomeScreenHolder(
    navController: NavHostController = rememberNavController(),
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(Screen.Login.route) {
            LoginScreen(
                navigateToRegister = {
                             navController.navigate(Screen.Register.route)
                },
                navigateToHome = {
//                    context.startActivity(Intent(context, MainActivity::class.java))
//                    (context as? ComponentActivity)?.finish()
                }
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen()
        }
    }
}