package com.example.ecoscan

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ecoscan.ui.theme.EcoScanTheme
import com.example.ecoscan.view.MainScreenHolder
import com.example.ecoscan.view.WelcomeScreenHolder

class EcoScanApp : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcoScanTheme {
                // A surface container using the 'background' color from the theme
                Surface (
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Kalau Mau Nampilin Splah Screen, Login Dan Register Screen
//                    startActivity(Intent(this,WelcomeActivity::class.java))
//                    WelcomeScreenHolder()


                    // Kalau Mau Nampilih Home Scan Profile Screen Sebagai Test Sebelum Ada Api
                    MainScreenHolder()
                }
            }
        }
    }
}

