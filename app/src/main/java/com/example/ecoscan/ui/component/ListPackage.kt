package com.example.ecoscan.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoscan.R
import com.example.ecoscan.ui.theme.EcoScanTheme
import com.example.ecoscan.ui.theme.GraySubs
import com.example.ecoscan.ui.theme.RedSubs

@Composable
fun ListPackage(
    modifier: Modifier = Modifier,
    paket: String,
    price: String,
    desc: String
) {
    androidx.compose.material.Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .shadow(
                5.dp,
                RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = paket,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = modifier
                        .padding(
                            horizontal = 10.dp,
                            vertical = 10.dp
                        )
                )
                Text(
                    text = desc,
                    modifier = modifier
                        .padding(
                            horizontal = 10.dp,
                            vertical = 10.dp
                        ),
                    fontWeight = FontWeight.Medium
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(GraySubs)
                        .size(width = 0.dp, height = 55.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = price,
                        modifier = Modifier
                            .padding(
                                horizontal = 10.dp,
                                vertical = 10.dp
                            ),
                        fontWeight = FontWeight.Medium,
                        fontSize = 14.sp,
                    )
                    Button(
                        modifier = Modifier
                            .padding(
                                horizontal = 10.dp,
                                vertical = 10.dp

                            ),
                        onClick = {},
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(RedSubs)
                    ) {
                        Text(
                            text = stringResource(R.string.beli),
                            color = Color.White,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListPackagePreview() {
    EcoScanTheme {
        ListPackage(
            paket = "Bronze",
            price = "Rp 30.000",
            desc = "15 Scan / Bulan"
        )
    }
}