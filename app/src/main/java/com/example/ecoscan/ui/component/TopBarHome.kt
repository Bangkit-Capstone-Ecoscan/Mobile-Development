package com.example.ecoscan.ui.component

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.ecoscan.R
import com.example.ecoscan.ui.theme.Gold
import com.example.ecoscan.ui.theme.Green

@Composable
fun TopBarHome(

) {
    val ecoScanText = buildAnnotatedString {
        withStyle(style = SpanStyle(MaterialTheme.colorScheme.secondary)) {
            append(stringResource(id = R.string.title_eco))
        }
        withStyle(style = SpanStyle(MaterialTheme.colorScheme.primary)) {
            append(stringResource(id = R.string.title_scan))
        }
    }

    TopAppBar(
        title = {
            Text(
                text = ecoScanText,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        },
        backgroundColor = Color.White,
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    painter = painterResource(R.drawable.crown_solid),
                    contentDescription = "Localized description",
                    tint = Gold
                )
            }
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Localized description",
                    tint = Gold
                )
            }
        },
    )
}