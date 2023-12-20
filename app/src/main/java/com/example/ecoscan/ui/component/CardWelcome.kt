package com.example.ecoscan.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.compose.ui.unit.sp
import com.example.ecoscan.ui.theme.EcoScanTheme
import com.example.ecoscan.ui.theme.Gold

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardWelcome(
    username: String,
    currentValue: Int,
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
                    withStyle(style = SpanStyle(fontWeight = FontWeight.W900, fontSize = 20.sp, color = Color(0xFFEBB02D))
                    ) {
                        append(username)
                    }
                }
            )


            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 30.dp, top = 30.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Kuota Scan Anda : ",
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = Modifier
                        .padding(
                            top = 15.dp,
                            bottom = 30.dp
                        )
                )

                Card (
                    modifier = Modifier
                        .width(70.dp)
                        .height(70.dp),
                    shape = CircleShape,
                    backgroundColor = Gold,
                    elevation = 10.dp
                )
                {
                    Column (
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        androidx.compose.material3.Text(
                            text = currentValue.toString(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )

                    }
                }
            }

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
            username = "wayan",
            currentValue = 2,
        )
    }
}