package com.example.ecoscan.ui.screen.bookmark.main

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecoscan.data.remote.response.GetResultResponseItem
import com.example.ecoscan.di.Injection
import com.example.ecoscan.ui.ViewModelFactory
import com.example.ecoscan.ui.common.UiState
import com.example.ecoscan.ui.component.ListItems
import com.example.ecoscan.ui.component.TopBarBack
import com.example.ecoscan.ui.component.TopBarScan

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookmarkScreen(
    backNavigation: () -> Unit,
    context: Context = LocalContext.current,
    viewModel: BookmarkViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    navigateToDetail: (String) -> Unit,
){
    Scaffold (
        topBar = { TopBarBack(
            backNavigation = {backNavigation()}
        ) }
    ) {

        viewModel.ListResults.observeAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.getResult()
                }

                is UiState.Success -> {
                    Log.d("BookmarkScreen", "Success state detected")
                    Log.d("BookMarkScreen", "${uiState.data}")
                    BookmarkScreenContent(
                        listScan = uiState.data,
                        navigateToDetail = navigateToDetail,
                    )
                }

                is UiState.Error -> {
                    Log.e("BookmarkScreen", "Error state detected: ${uiState.errorMessage}")
                    Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }
    }
}

@Composable
fun BookmarkScreenContent(
    listScan: List<GetResultResponseItem>,
    modifier: Modifier = Modifier,
    navigateToDetail: (String) -> Unit
){

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = 60.dp, horizontal = 0.dp)
    ) {
        items(listScan) {data ->
            data.apply {
                Log.d("BookMarkScreen", "Data ${data.imageUrl}")
                ListItems(
                    titleArticle = foodName,
                    descArticle = calcium,
                    photoUrl = imageUrl,
                    author = emission,
                    year = fat,
                    modifier = modifier.clickable {
                        navigateToDetail(data.dataId)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookmarkScreenPreview(){
    BookmarkScreen(
        backNavigation = {},
        navigateToDetail = {}
    )
}