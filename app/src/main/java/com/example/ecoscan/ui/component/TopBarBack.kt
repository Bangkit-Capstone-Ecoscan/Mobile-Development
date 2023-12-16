package com.example.ecoscan.ui.component

import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
fun TopBarBack(
    backNavigation: () -> Unit
){
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
        navigationIcon = {
            androidx.compose.material3.IconButton(
                onClick = {
                    backNavigation()
                },
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription ="Back" )
            }
        },

        backgroundColor = Color.White,
    )
}

