package com.example.ecoscan.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.ecoscan.R
import com.example.ecoscan.ui.theme.EcoScanTheme
import com.example.ecoscan.ui.theme.Gold
import com.example.ecoscan.ui.theme.Green

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    val ecoScanText = buildAnnotatedString {
        withStyle(style = SpanStyle(Green)) {
            append(stringResource(id = R.string.title_eco))
        }
        withStyle(style = SpanStyle(Gold)) {
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
        modifier = modifier,
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.White,
        ),
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
        scrollBehavior = scrollBehavior,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBar() {
    EcoScanTheme {
        TopBar()
    }
}