package com.example.ecoscan

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.ecoscan.ui.theme.EcoScanTheme
import com.example.ecoscan.view.MainScreenHolder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EcoScanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ){
                    MainScreenHolder()
                }
            }
        }
    }
}