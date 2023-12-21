package com.example.ecoscan.ui.screen.bookmark.detail

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ecoscan.R
import com.example.ecoscan.data.pref.UserIdData
import com.example.ecoscan.di.Injection
import com.example.ecoscan.ui.ViewModelFactory
import com.example.ecoscan.ui.common.UiState
import com.example.ecoscan.ui.component.TopBarBack
import com.example.ecoscan.ui.component.TopBarScan
import com.example.ecoscan.ui.theme.Gold

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailBookmarkScreen(
    id: String,
    context: Context = LocalContext.current,
    viewModel: DetailBookmarkViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    navigateBack: () -> Unit
) {
    Scaffold(
        topBar = { TopBarBack {
            navigateBack()
        } }
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
                    Log.d("checkUserId", "DetailBookmarkScreen: ${dataResult.userId}")
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
                .height(450.dp)
                .padding(bottom = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(top = 60.dp)
                    .fillMaxWidth()
            )

        }

        androidx.compose.material.Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            shape = RoundedCornerShape(topEnd = 100.dp),
            elevation = 20.dp
        ) {
            Column {
                Text(
                    text = foodName.toString(),
                    style = TextStyle.Default,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 32.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                )
                Text(
                    text = emission.toString(),
                    style = TextStyle.Default,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 32.dp),
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp,
                )
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.carb),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                    )

                    Image(
                        painter = painterResource(id = R.drawable.iconcalcium),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                        )

                    Image(
                        painter = painterResource(id = R.drawable.iconvitamins),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = carbohydrates.toString(),
                        style = TextStyle.Default,
                        modifier = Modifier,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                    )

                    Text(
                        text = calcium.toString(),
                        style = TextStyle.Default,
                        modifier = Modifier,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                    )

                    Text(
                        text = protein.toString(),
                        style = TextStyle.Default,
                        modifier = Modifier,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp,
                    )
                }

                Text(
                    text = stringResource(id = R.string.descripctions, vitamins.toString()),
                    style = TextStyle.Default,
                    modifier = Modifier
                        .padding(top = 16.dp, start = 32.dp),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                )

                Text(
                    text = stringResource(id = R.string.fat,fat.toString()),
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
    DetailBookmarkContent(
        imageUrl = "https://unsplash.com/photos/a-person-walking-on-a-beach-at-sunset-8hVAgHtYyVU",
        foodName = "Basreng",
        emission = "2.3 Kg Co2",
        carbohydrates = "300 Gram",
        calcium = "200 Gram",
        vitamins = "B1 And C1",
        protein = "500 Gram",
        fat = "100 Gram"
    )
}