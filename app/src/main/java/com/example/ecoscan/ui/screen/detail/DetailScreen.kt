package com.example.ecoscan.ui.screen.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.ecoscan.R
import com.example.ecoscan.di.Injection
import com.example.ecoscan.ui.ViewModelFactory
import com.example.ecoscan.ui.common.UiState
import com.example.ecoscan.ui.component.TopBarScan
import com.example.ecoscan.ui.screen.home.HomeViewModel
import com.example.ecoscan.ui.screen.home.ScrollContent
import com.example.ecoscan.ui.theme.EcoScanTheme
import com.example.ecoscan.ui.theme.Gold

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    titleArticle: String,
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
                    Log.d("DetailScreen", "Loading state detected")
                    viewModel.getDetailArticle(titleArticle)
                }

                is UiState.Success -> {
                    showDialog = true
                    Log.d("DetailScreen", "Success state detected")
                    Log.d("DetailScreen", "${uiState.data}")
                    val articleList = uiState.data
                    if (articleList.isNotEmpty()){
                        val article = articleList[0]
                        DetailContent(
                            titleArticle = article.data.title,
                            descArticle = article.data.desc.joinToString("\n"),
                            photoUrl = article.data.imgUrl,
                            author = article.data.author,
                            year = article.data.authorYear
                        )
                    }

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
    descArticle: String,
    photoUrl: String,
    author: String,
    year: String,
    modifier: Modifier = Modifier
) {

    val openUrlLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { }

    val openUrl: () -> Unit = {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://www.dw.com/id/kurangi-emisi-karbon-dengan-konsumsi-bijak-selama-ramadan/a-61393961")
        )
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

            Box {
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
        DetailScreen("Hello")
    }
}