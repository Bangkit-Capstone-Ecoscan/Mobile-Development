package com.example.ecoscan.ui.screen.subscribe

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoscan.ui.component.ListPackage
import com.example.ecoscan.ui.component.TopBarScan
import com.example.ecoscan.ui.theme.EcoScanTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SubscribeScreen(){
    Scaffold {
        SubscribeContent()
        TopBarScan()
    }
}

@Composable
fun SubscribeContent(
    modifier: Modifier = Modifier
){
    val range = 1..3
    Text(
        text = "Rekomendasi Paket",
        modifier = modifier
            .padding(
                horizontal = 12.dp,
                vertical = 80.dp
            ),
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.titleLarge.copy(
            fontWeight = FontWeight.ExtraBold
        ),
        fontSize = 24.sp,
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(vertical = 130.dp, horizontal = 0.dp),
    ) {
        items(range.count()) { index ->
            ListPackage()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SubscribeScreenPreview() {
    EcoScanTheme {
        SubscribeScreen()
    }
}