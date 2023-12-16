package com.example.ecoscan.ui.screen.bookmark.detail

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ecoscan.di.Injection
import com.example.ecoscan.ui.ViewModelFactory
import com.example.ecoscan.ui.component.TopBarScan

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailBookmarkScreen() {
    Scaffold(
        topBar = { TopBarScan() }
    ) {
        DetailBookmarkContent(

        )
    }
}

@Composable
fun DetailBookmarkContent(
    context: Context = LocalContext.current,
    viewModel: DetailBookmarkViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
) {


    val results = viewModel.getResult().observeAsState()

    if (results != null) {
        Log.d("detail", "DetailBookmarkContent: ${results.value?.url}")

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = results.value?.url,
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.padding(top = 60.dp)
                )
            }
            results.value?.let {
                Text(
                    text = it.foodName,
                    style = TextStyle.Default,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 32.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )
            }
            results.value?.let {
                Text(
                    text = it.carbon,
                    style = TextStyle.Default,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 32.dp),
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                )
            }
            results.value?.let {
                Text(
                    text = it.emission,
                    style = TextStyle.Default,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 32.dp),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                )
            }
            results.value?.let {
                Text(
                    text = it.fat,
                    style = TextStyle.Default,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 32.dp),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                )
            }
            results.value?.let {
                Text(
                    text = it.calcium,
                    style = TextStyle.Default,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 32.dp),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                )
            }
            results.value?.let {
                Text(
                    text = it.vitamin,
                    style = TextStyle.Default,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 32.dp),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                )
            }
            results.value?.let {
                Text(
                    text = it.protein,
                    style = TextStyle.Default,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 32.dp),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DetailBookmarkPreview() {
    DetailBookmarkScreen()
}