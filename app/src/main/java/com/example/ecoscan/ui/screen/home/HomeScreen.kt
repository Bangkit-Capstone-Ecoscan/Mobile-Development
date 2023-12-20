package com.example.ecoscan.ui.screen.home


import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecoscan.data.remote.response.ArticleResponseItem
import com.example.ecoscan.di.Injection
import com.example.ecoscan.ui.ViewModelFactory
import com.example.ecoscan.ui.common.UiState
import com.example.ecoscan.ui.component.CardWelcome
import com.example.ecoscan.ui.component.ListItems
import com.example.ecoscan.ui.component.TopBarHome
import com.example.ecoscan.ui.theme.EcoScanTheme
import com.example.ecoscan.ui.theme.Gold

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    navigateToSubscribe: () -> Unit,
    navigateToSetting: () -> Unit,
    navigateToDetail: (String) -> Unit,
) {
    var showDialog by remember {
        mutableStateOf(false)
    }

    var showLoading by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopBarHome(
                navigateToSubscribe = { navigateToSubscribe() },
                navigateToSetting = { navigateToSetting() }
            )
        }
    ) {
//        val getArticle by viewModel.getArticle.observeAsState()
        val quota by viewModel.getQuota().observeAsState()
        val userData by viewModel.getUserData().observeAsState()
        viewModel.getArticle.observeAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    showLoading = true
                    if (showLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(24.dp),
                            color = Color.Gray
                        )
                    }
                    Log.d("HomeScreen", "Loading state detected ")
                    viewModel.getAllArticle()
                }

                is UiState.Success -> {
                    showDialog = true
//                    showLoading = false
                    Log.d("HomeScreen", "Success state detected")
                    Log.d("HomeScreen", "${uiState.data}")


                    ScrollContent(
                        article = uiState.data,
                        navigateToDetail = navigateToDetail,
                        username = userData?.email ?: "",
                        currentQuota = userData?.quota ?: 0,
                    )
                }

                is UiState.Error -> {
                    showLoading = false
                    Log.e("HomeScreen", "Error state detected: ${uiState.errorMessage}")
                    Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
                }

                else -> {
                    Log.d("HomeScreen", "Unhandled state detected")
                }
            }
        }
    }


}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ScrollContent(
    article: List<ArticleResponseItem>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit,
    username: String,
    currentQuota: Int,
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 60.dp, horizontal = 0.dp),
    ) {
        item {
            CardWelcome(
                username = username,
                currentValue = currentQuota,
            )
        }
        items(article) { data ->
            data.apply {
                Log.d("HomeScreen", "Data ${data.articleUrl}")
                ListItems(
                    titleArticle = title,
                    descArticle = desc.joinToString("\n"),
                    photoUrl = imgUrl,
                    author = author,
                    year = authorYear,
                    modifier = Modifier.clickable {
                        navigateToDetail(data.id)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    val sampleArticle = listOf(
        ArticleResponseItem(
            id = "1",
            title = "Sample Title",
            desc = listOf("Sample Description Line 1", "Sample Description Line 2"),
            imgUrl = "https://example.com/sample-image.jpg",
            author = "John Doe",
            authorYear = "2023",
            articleUrl = "https://example.com/sample-article"
        ),
        // Add more sample articles as needed
    )
    EcoScanTheme {
        ScrollContent(article = sampleArticle, navigateToDetail = {}, username = "", currentQuota = 1)
    }
}