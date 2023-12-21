package com.example.ecoscan.ui.screen.bookmark.fakedetail

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.ecoscan.R
import com.example.ecoscan.di.Injection
import com.example.ecoscan.ui.ViewModelFactory
import com.example.ecoscan.ui.common.UiState
import com.example.ecoscan.ui.component.TopBarBack

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun FakeDetailBookScreen(
    context: Context = LocalContext.current,
    viewModel: FakeDetailBookViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
    navigateBack: () -> Unit
) {

    Scaffold (
        topBar = { TopBarBack {
            navigateBack()
        }}
    ) {
        val dataDummyResult = viewModel.getResult().observeAsState()

        dataDummyResult.value?.apply {
            DetailBookmarkContent(
                url,
                foodName,
                emission,
                carbon,
                calcium,
                vitamin,
                protein,
                fat,
            )
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
