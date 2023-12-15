package com.example.ecoscan.ui.screen.bookmark.main

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecoscan.ui.component.ListItems
import com.example.ecoscan.ui.component.TopBarScan

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookmarkScreen(){
    Scaffold (
        topBar = { TopBarScan() }
    ) {
        BookmarkScreenContent()
    }
}

@Composable
fun BookmarkScreenContent(){

    val range = 1..10
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = 60.dp, horizontal = 0.dp)
    ) {
        items(range.count()) {index ->
            ListItems(
                titleArticle = "M ARIF SATRIA",
                descArticle = "Descripsi Article",
                photoUrl = "https://imgx.sonora.id/crop/0x0:0x0/700x465/photo/2020/02/17/2398193539.png",
                author = "arif",
                year = "2023"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookmarkScreenPreview(){
    BookmarkScreen()
}