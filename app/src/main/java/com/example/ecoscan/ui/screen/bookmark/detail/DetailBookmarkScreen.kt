package com.example.ecoscan.ui.screen.bookmark.detail

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ecoscan.ui.component.TopBarScan

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailBookmarkScreen() {
    Scaffold (
        topBar = { TopBarScan() }
    ) {
        DetailBookmarkContent(
            image = "https://lindungihutan.com/blog/wp-content/uploads/2022/03/Emisi-Karbon-Lengkap-Cover-Image-Blog-LindungiHutan.png",
            title = "Nama Makanan :",
            carbon = "320 Emission Carbon",
            result = "Hasil"
        )
    }
}

@Composable
fun DetailBookmarkContent(
    image: String,
    title: String,
    carbon: String,
    result: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = image,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.padding(top = 60.dp)
            )
        }
        Text(
            text = title,
            style = TextStyle.Default,
            modifier = Modifier
                .padding(top = 16.dp, start = 32.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        Text(
            text = carbon,
            style = TextStyle.Default,
            modifier = Modifier
                .padding(top = 16.dp, start = 32.dp),
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
        )
        Text(
            text = result,
            style = TextStyle.Default,
            modifier = Modifier
                .padding(top = 16.dp, start = 32.dp),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DetailBookmarkPreview() {
    DetailBookmarkScreen()
}