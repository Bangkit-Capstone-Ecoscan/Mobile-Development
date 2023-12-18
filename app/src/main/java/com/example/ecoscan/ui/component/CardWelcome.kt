package com.example.ecoscan.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecoscan.ui.theme.EcoScanTheme
@Composable
fun CardWelcome(
    username: String
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 65.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                buildAnnotatedString {
                    append("Selamat Datang ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFF4552B8))
                    ) {
                        append(username)
                    }
                }
            )
            Text(
                buildAnnotatedString {
                    append("Mari Ketahui Berita Mengenai")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900)) {
                        append(" Emisi Carbon ")
                    }
                    append("Pada Artikel Dibawah")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardWelcome(){
    EcoScanTheme {
        CardWelcome(
            username = "wayan"
        )
    }
}