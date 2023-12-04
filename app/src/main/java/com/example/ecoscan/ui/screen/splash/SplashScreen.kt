package com.example.ecoscan.ui.screen.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.ecoscan.R
import com.example.ecoscan.ui.navigation.Screen
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavHostController,
) {

    val scale = remember {
        Animatable(0f)
    }

    // Animation
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            // tween Animation
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                })
        )
        // Customize the delay time
        delay(2000)
        navController.navigate(Screen.Login.route)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .size(270.dp),
                painter = painterResource(id = R.drawable.logoapp),
                contentDescription = "LogoApp",
            )
        }
    }
}