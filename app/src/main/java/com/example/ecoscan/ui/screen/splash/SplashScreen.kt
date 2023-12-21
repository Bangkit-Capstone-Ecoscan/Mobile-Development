package com.example.ecoscan.ui.screen.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ecoscan.R
import com.example.ecoscan.ui.navigation.Screen
import com.example.ecoscan.ui.theme.EcoScanTheme
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    navController: NavHostController
) {

    val scale = remember {
        Animatable(0f)
    }

    val visible by remember {
        mutableStateOf(true)
    }

    val density = LocalDensity.current

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
        navController.popBackStack()
        navController.navigate(Screen.Login.route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
        }

    }


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.White)
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = slideInVertically {
                    with(density) { -40.dp.roundToPx()}
                } + expandVertically(
                    // Expand from the top.
                    expandFrom = Alignment.Top
                ),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        modifier = Modifier
                            .size(300.dp),
                        painter = painterResource(id = R.drawable.logosplash),
                        contentDescription = "LogoApp",
                    )
                }
            }
        }

}
