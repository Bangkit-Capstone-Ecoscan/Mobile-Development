package com.example.ecoscan.ui.component

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.ecoscan.ui.theme.Gold
import com.example.ecoscan.ui.theme.Green

@Preview
@Composable
fun TopBarScan(

) {

    val ecoScanText = buildAnnotatedString {
        withStyle(style = SpanStyle(Green)) {
            append("ECO")
        }
        withStyle(style = SpanStyle(Gold)) {
            append("SCAN")
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
    )
}