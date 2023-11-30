package com.example.ecoscan.ui.screen.home


import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecoscan.ui.component.ListItems
import com.example.ecoscan.ui.component.TopBarHome
import com.example.ecoscan.ui.theme.EcoScanTheme


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(){
    Scaffold (
        topBar = { TopBarHome() }
    ) {
        ScrollContent()
    }
}

@Composable
fun ScrollContent() {
    val range = 1..20

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = 60.dp, horizontal = 0.dp),
    ) {
        items(range.count()) { index ->
            ListItems(
                titleArticle = "Wayan Berdyanto",
                descArticle = "Lorem",
                photoUrl = "https://imgx.sonora.id/crop/0x0:0x0/700x465/photo/2020/02/17/2398193539.png",
                author = "wayan",
                year = "2023",
                modifier = Modifier.clickable {}
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBar() {
    EcoScanTheme {
        TopBarHome()
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    EcoScanTheme {
        HomeScreen()
    }
}