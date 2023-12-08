package com.example.ecoscan.ui.screen.home


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.ecoscan.data.remote.response.ArticleResponseItem
import com.example.ecoscan.di.Injection
import com.example.ecoscan.ui.ViewModelFactory
import com.example.ecoscan.ui.component.ListItems
import com.example.ecoscan.ui.component.TopBarHome
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.ecoscan.ui.common.UiState
import com.example.ecoscan.ui.theme.EcoScanTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: HomeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    navigateToSubscribe: () -> Unit,
    navigateToSetting: () -> Unit
){
    var showDialog by remember {
        mutableStateOf(false)
    }

    var showLoading by remember {
        mutableStateOf(false)
    }

    Scaffold (
        topBar = {
            TopBarHome(
            navigateToSubscribe = { navigateToSubscribe() },
                navigateToSetting = { navigateToSetting() }
        ) }
    ) {
//        val getArticle by viewModel.getArticle.observeAsState()
        viewModel.getArticle.observeAsState(initial = UiState.Loading).value.let{uiState->
            when(uiState){
                is UiState.Loading -> {
                    showLoading = true
                    Log.d("HomeScreen", "Loading state detected")
                    viewModel.getAllArticle()
                }
                is UiState.Success -> {
                    showDialog = true
                    Log.d("HomeScreen", "Success state detected")
                    Log.d("HomeScreen", "${uiState.data}")

                    ScrollContent(
                        article = uiState.data
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

@Composable
fun ScrollContent(
    article:List<ArticleResponseItem>,
    modifier: Modifier = Modifier,
    ) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = 60.dp, horizontal = 0.dp),
    ) {
        items(article) { data ->
            ListItems(
                titleArticle = data.title,
                descArticle = data.desc.joinToString("\n")?: "",
                photoUrl = data.imgUrl,
                author = data.author,
                year = data.authorYear,
                modifier = Modifier.clickable {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    EcoScanTheme {
        HomeScreen(navigateToSubscribe = {}, navigateToSetting = {})
    }
}