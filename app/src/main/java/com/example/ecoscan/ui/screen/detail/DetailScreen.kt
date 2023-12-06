package com.example.ecoscan.ui.screen.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.ecoscan.R
import com.example.ecoscan.ui.component.TopBarScan
import com.example.ecoscan.ui.theme.EcoScanTheme
import com.example.ecoscan.ui.theme.Gold

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen() {
    Scaffold(
        topBar = {
            TopBarScan()
        }
    ) {
        DetailContent(
            titleArticle = "Kurangi Emisi Karbon dengan Konsumsi Bijak Selama Ramadan",
            descArticle = stringResource(id = R.string.stringDesc),
            photoUrl = "https://imgx.sonora.id/crop/0x0:0x0/700x465/photo/2020/02/17/2398193539.png",
            author = "Arif",
            year = "2024"
        )

    }
}

@Composable
fun DetailContent(
    titleArticle: String,
    descArticle: String,
    photoUrl: String,
    author: String,
    year: String,
    modifier: Modifier = Modifier
) {

    val openUrlLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) {  }

    val openUrl: () -> Unit = {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dw.com/id/kurangi-emisi-karbon-dengan-konsumsi-bijak-selama-ramadan/a-61393961"))
        openUrlLauncher.launch(intent)
    }

    Row(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
                .padding(vertical = 65.dp, horizontal = 10.dp)
        ) {
            AsyncImage(
                model = photoUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .height(300.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .fillMaxWidth()
            )

            Text(
                text = titleArticle,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
            )

            Box{
                Text(
                    text = descArticle,
                    modifier = modifier
                        .padding(top = 8.dp)
                )
                // Button at the bottom
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                        .zIndex(1f)
                        .align(Alignment.Center), // Align the button at the center horizontally
                    onClick = { openUrl },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(Gold)
                ) {
                    Text(
                        text = "Selengkapnya",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                    )
                }
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    EcoScanTheme {
        DetailScreen()
    }
}