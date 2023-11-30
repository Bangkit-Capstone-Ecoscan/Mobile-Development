package com.example.ecoscan.ui.screen.scan

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecoscan.R
import com.example.ecoscan.ui.component.ResultScan
import com.example.ecoscan.ui.component.TopBarScan
import com.example.ecoscan.ui.theme.EcoScanTheme
import com.example.ecoscan.ui.theme.Gold

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScanScreen(

) {
    Scaffold(
        topBar = { TopBarScan() }
    ) {
        ScanScreenContent(image = painterResource(id = R.drawable.placeholderfood))
    }
}

@Composable
fun ScanScreenContent(
    modifier: Modifier = Modifier,
    image: Painter,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
        Column(
            modifier = modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(
                    color = Color.White,
                )
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )
            /*
            Place Holder For Image After user taking a photo or scan
         */
            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .height(200.dp)
                    .padding(start = 20.dp, end = 20.dp)
                    .clip(RoundedCornerShape(20.dp)),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxSize(),
                    painter = image,
                    contentDescription = "LogoApp",
                    contentScale = ContentScale.FillBounds
                )
            }

            /*
            Button Gallery And Camera
         */
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp, top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {

                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.gallery),
                        contentDescription = "Gallery",
                        tint = Color.White
                    )
                }

                Button(
                    onClick = {

                    },
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.camera),
                        contentDescription = "Camera",
                        tint = Color.White
                    )
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {

                    },
                    modifier = Modifier
                        .width(150.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(Gold)
                ) {
                    Text(
                        text = "SCAN",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            ) {
                ResultScan(foodName = "Seblak")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScanScreenPreview() {
    EcoScanTheme {
        ScanScreenContent(image = painterResource(id = R.drawable.placeholderfood))
    }
}