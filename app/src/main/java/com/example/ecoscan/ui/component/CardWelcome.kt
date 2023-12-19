package com.example.ecoscan.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecoscan.data.models.PaketDataSource.paket
import com.example.ecoscan.ui.theme.EcoScanTheme
import com.example.ecoscan.ui.theme.Gold
import com.example.ecoscan.ui.theme.GraySubs
import com.example.ecoscan.ui.theme.Green
import com.example.ecoscan.ui.theme.Purple40
import com.example.ecoscan.ui.theme.Purple80
import com.example.ecoscan.ui.theme.PurpleGrey40

@Composable
fun CardWelcome(
    username: String,
    currentValue: Int,
    maxValue: Int,
    progressBackgroundColor: Color,
    progressIndicatorColor: Color,
    completedColor: Color,
    modifier: Modifier = Modifier,
){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .shadow(
                10.dp,
                RoundedCornerShape(8.dp)
            )
            .height(230.dp)
            .clickable { },
        elevation = 20.dp
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Text(
                buildAnnotatedString {
                    append("Selamat Datang ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900, color = Color(0xFFEBB02D))
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
            Text(
                text = "Total Scan",
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier
                    .padding(
                        top = 15.dp
                    )
            )
            AnimatedProgressIndicator(
                currentValue = currentValue,
                maxValue = maxValue,
                progressBackgroundColor = progressBackgroundColor,
                progressIndicatorColor = progressIndicatorColor,
                completedColor = completedColor,
                modifier = Modifier
                    .padding(vertical = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCardWelcome(){
    EcoScanTheme {
        CardWelcome(
            username = "wayan",
            currentValue = 2,
            maxValue = 20,
            progressBackgroundColor = Gold,
            progressIndicatorColor = Color.Black,
            completedColor = Color.Red,
        )
    }
}