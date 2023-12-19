package com.example.ecoscan.ui.screen.bookmark.detail

import android.annotation.SuppressLint
import android.content.Context
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
import com.example.ecoscan.data.pref.UserIdData
import com.example.ecoscan.di.Injection
import com.example.ecoscan.ui.ViewModelFactory
import com.example.ecoscan.ui.common.UiState
import com.example.ecoscan.ui.component.TopBarScan

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailBookmarkScreen(
    id: String,
    context: Context = LocalContext.current,
    viewModel: DetailBookmarkViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
) {
    Scaffold(
        topBar = { TopBarScan() }
    ) {
        viewModel.getDetailById.observeAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    viewModel.detailResultById(id)
                }

                is UiState.Success -> {
                    val dataResult = uiState.data
                    DetailBookmarkContent(
                        imageUrl = dataResult.imageUrl,
                        foodName = dataResult.foodName,
                        emission = dataResult.emission,
                        carbohydrates = dataResult.carbohydrates,
                        calcium = dataResult.calcium,
                        vitamins = dataResult.vitamins,
                        protein = dataResult.protein,
                        fat = dataResult.fat
                    )
                    viewModel.saveResult(
                        UserIdData(
                            dataResult.userId.toString()
                        )
                    )
                }

                is UiState.Error -> {

                }

                else -> {}
            }
        }
    }
}

@Composable
fun DetailBookmarkContent(
    imageUrl: String? = null,
    foodName: String? = null,
    emission: String? = null,
    carbohydrates: String? = null,
    calcium: String? = null,
    vitamins: String? = null,
    protein: String? = null,
    fat: String? = null,
) {


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
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.padding(top = 60.dp)
            )

        }

        Text(
            text = foodName.toString(),
            style = TextStyle.Default,
            modifier = Modifier
                .padding(top = 16.dp, start = 32.dp),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )



        Text(
            text = emission.toString(),
            style = TextStyle.Default,
            modifier = Modifier
                .padding(top = 16.dp, start = 32.dp),
            fontWeight = FontWeight.Medium,
            fontSize = 20.sp,
        )


        Text(
            text = carbohydrates.toString(),
            style = TextStyle.Default,
            modifier = Modifier
                .padding(top = 16.dp, start = 32.dp),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        )




        Text(
            text = calcium.toString(),
            style = TextStyle.Default,
            modifier = Modifier
                .padding(top = 16.dp, start = 32.dp),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        )




        Text(
            text = protein.toString(),
            style = TextStyle.Default,
            modifier = Modifier
                .padding(top = 16.dp, start = 32.dp),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        )




        Text(
            text = vitamins.toString(),
            style = TextStyle.Default,
            modifier = Modifier
                .padding(top = 16.dp, start = 32.dp),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        )




        Text(
            text = fat.toString(),
            style = TextStyle.Default,
            modifier = Modifier
                .padding(top = 16.dp, start = 32.dp),
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        )


    }


}

@Preview(showBackground = true)
@Composable
fun DetailBookmarkPreview() {
    DetailBookmarkContent(

    )
}