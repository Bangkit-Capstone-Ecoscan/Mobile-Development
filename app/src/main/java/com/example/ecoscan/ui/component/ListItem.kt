package com.example.ecoscan.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.ecoscan.ui.theme.EcoScanTheme

@Composable
fun ListItems(
    titleArticle: String,
    descArticle: String,
    photoUrl: String,
    author: String,
    year: String,
    modifier: Modifier = Modifier
) {
    androidx.compose.material.Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 12.dp)
            .shadow(
                10.dp,
                RoundedCornerShape(8.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .padding(horizontal = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 5.dp, horizontal = 0.dp)
            ) {
                Text(
                    text = titleArticle,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
                Text(
                    text = descArticle,
                    modifier = Modifier
                        .padding(vertical = 8.dp),
                    maxLines = 2,
                    style = MaterialTheme.typography.titleSmall,
                    overflow = TextOverflow.Ellipsis
                )
                Row {
                    Text(
                        text = author,
                        style = MaterialTheme.typography.titleSmall
                    )
                    Text(
                        text = " | ",
                        style = MaterialTheme.typography.titleSmall
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = year,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .padding(vertical = 8.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(16.dp))
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ListItemPreview() {
    EcoScanTheme {
        ListItems(
            titleArticle = "Menjaga Kesehatan",
            descArticle = "Lorem ipsum lokotre ipsum note messi ronaldo siu" +
                    "Lorem ipsum lokotre ipsum note messi ronaldo siu",
            photoUrl = "https://imgx.sonora.id/crop/0x0:0x0/700x465/photo/2020/02/17/2398193539.png",
            author = "Wayan",
            year = "2023"
        )
    }
}