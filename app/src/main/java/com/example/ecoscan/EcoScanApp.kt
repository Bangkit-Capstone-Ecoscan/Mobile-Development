package com.example.ecoscan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.ecoscan.ui.ViewModelFactory
import com.example.ecoscan.ui.theme.EcoScanTheme
import com.example.ecoscan.view.MainScreenHolder
import com.example.ecoscan.view.WelcomeScreenHolder

class EcoScanApp : ComponentActivity() {

    private val viewModel by viewModels<EcoScanViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getSession().observe(this) { user ->
            if (!user.isLogin) {
                setContent {
                    EcoScanTheme {
                        WelcomeScreenHolder()
                    }
                }
            }
        }
        setContent {
            EcoScanTheme {
                MainScreenHolder()
            }
        }

    }
}

