package com.example.ecoscan.ui.screen.settings

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecoscan.R
import com.example.ecoscan.ui.component.SwitchMinimal
import com.example.ecoscan.ui.component.TopBarScan
import com.example.ecoscan.ui.theme.EcoScanTheme
import com.example.ecoscan.ui.theme.Gold

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SettingScreen() {
    Scaffold(
        topBar = {
            TopBarScan()
        },
    ) {
        SettingsContent()
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SettingsContent() {
    Text(
        text = "Settings",
        modifier = Modifier
            .padding(vertical = 70.dp, horizontal = 10.dp),
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.ExtraBold
        ),
    )
//    Pengaturan Tema
    androidx.compose.material.Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(vertical = 110.dp, horizontal = 10.dp)
            .fillMaxWidth()
            .shadow(
                10.dp,
                RoundedCornerShape(8.dp)
            ),
        onClick = {}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .size(width = 0.dp, height = 80.dp)
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(
                    text = "Tema",
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 10.dp),
                    fontWeight = FontWeight.SemiBold
                )
            }
            SwitchMinimal()
        }

    }
//   Pengaturan Bahasa
    androidx.compose.material.Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(vertical = 210.dp, horizontal = 10.dp)
            .fillMaxWidth()
            .shadow(
                10.dp,
                RoundedCornerShape(8.dp)
            ),
        onClick = {}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .size(width = 0.dp, height = 80.dp)
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(
                    text = "Bahasa",
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 10.dp),
                    fontWeight = FontWeight.SemiBold
                )
            }
            Icon(
                painter = painterResource(R.drawable.baseline_language_24),
                contentDescription = "Localized description",
                tint = Gold,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )
        }
    }
//    Pengaturan Versi Aplikasi
    androidx.compose.material.Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(vertical = 310.dp, horizontal = 10.dp)
            .fillMaxWidth()
            .shadow(
                10.dp,
                RoundedCornerShape(8.dp)
            ),
        onClick = {}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .size(width = 0.dp, height = 80.dp)
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                Text(
                    text = "Versi",
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 10.dp),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "1.2.0",
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 10.dp),
                )
            }
            Icon(
                painter = painterResource(R.drawable.baseline_android_24),
                contentDescription = "Localized description",
                tint = Color.Green,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SettingScreenPreview() {
    EcoScanTheme {
        SettingScreen()
    }
}