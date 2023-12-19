package com.example.ecoscan.ui.screen.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.ecoscan.di.Injection
import com.example.ecoscan.ui.ViewModelFactory
import com.example.ecoscan.ui.common.UiState
import com.example.ecoscan.ui.component.TopBarScan
import com.example.ecoscan.ui.theme.EcoScanTheme
import com.example.ecoscan.ui.theme.Gold

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    id: String,
    context: Context = LocalContext.current,
    viewModel: DetailViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),

    ) {

    var showDialog by remember {
        mutableStateOf(false)
    }

    var showLoading by remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopBarScan()
        }
    ) {
        viewModel.getDetailArticle.observeAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    showLoading = true
                    Log.d("DetailScreen", "Loading state detected $id")
                    viewModel.getDetailArticle(id)

                }

                is UiState.Success -> {
                    showDialog = true
                    Log.d("DetailScreen", "Success state detected")
                    Log.d("DetailScreen", "${uiState.data}")
                    val data = uiState.data
                    DetailContent(
                        titleArticle = data.title,
                        descArticle = data.desc,
                        photoUrl = data.imgUrl,
                        author = data.author,
                        year = data.authorYear,
                        linkUrl = data.articleUrl
                    )

                }

                is UiState.Error -> {
                    showLoading = false
                    Log.e("DetailScreen", "Error state detected: ${uiState.errorMessage}")
                    Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Log.d("DetailScreen", "Unhandled state detected")
                }
            }
        }
    }
}

@Composable
fun DetailContent(
    titleArticle: String,
    descArticle: List<String>,
    photoUrl: String,
    author: String?,
    year: String,
    linkUrl: String,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
) {
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
                    .border(2.dp, Color.Gray, shape = RoundedCornerShape(16.dp))
            )
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
            ) {
                if (author != null) {
                    Text(
                        text = author,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
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
            Text(
                text = titleArticle,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 8.dp)
            )

            Text(
                text = descArticle.joinToString("\n"),
                modifier = modifier
                    .padding(top = 8.dp),
                textAlign = TextAlign.Justify
            )

            // Button at the bottom
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
                    .zIndex(1f)
                    .align(Alignment.CenterHorizontally), // Align the button at the center horizontally
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(linkUrl))
                    context.startActivity(intent)
                },
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

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    EcoScanTheme {
        DetailContent(
            titleArticle = "Emisi Karbon: Penyebab, Dampak dan Cara Mengurangi",
            descArticle = listOf(
                "\"Apa pengertian dari misi karbon atau carbon emission? Emisi berkaitan dengan proses perpindahan suatu zat atau benda. Umumnya kata emisi digunakan untuk emisi panas, emisi cahaya, maupun emisi karbon. Berdasarkan penjelasan di atas, pengertian emisi karbon atau carbon emission adalah gas yang dikeluarkan dari hasil pembakaran segala senyawa yang mengandung karbon seperti CO2, solar, bensin, LPG, serta bahan bakar lainnya. Fenomena emisi karbon merupakan proses pelepasan karbon ke lapisan atmosfer bumi.\",\n" +
                        "                \"Saat ini, emisi karbon menjadi salah satu penyumbang terjadinya perubahan iklim dan pemanasan bersamaan dengan emisi gas rumah kaca. Keduanya menyebabkan naiknya suhu bumi atau efek rumah kaca. Untuk menghitung besaran emisi yang dihasilkan, perlu dilakukan pengukuran jejak karbon (carbon footprint). Jejak karbon adalah jumlah emisi CO2 dan zat-zat rumah kaca yang berhubungan dengan segala jenis aktivitas seseorang ataupun entitas lain seperti bangunan, sebuah perusahaan, negara, dan lainnya. Satuan yang digunakan untuk menghitung kuantitas emisi karbon dihitung dengan satuan ton ekuivalen karbon dioksida (CO2).\",\n" +
                        "                \"Contohnya, Budi yang menggunakan kendaraan pribadi berupa sepeda motor di Jakarta menghasilkan jejak karbon sejumlah 4,82 kg CO2 setiap harinya.\""
            ),
            photoUrl = "https://lindungihutan.com/blog/wp-content/uploads/2022/03/Emisi-Karbon-Lengkap-Cover-Image-Blog-LindungiHutan.png",
            author = "LindungiHutan",
            year = "2022",
            linkUrl = "https://lindungihutan.com/blog/emisi-karbon/"

        )
    }
}