package com.example.ecoscan.ui.component

import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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


@Composable
fun TopBarProfile(

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
                    imageVector = Icons.Default.Bookmarks,
                    contentDescription = "Localized description",
                    tint = Color.Black
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBarProfile() {
    EcoScanTheme {
        TopBarProfile()
    }
}