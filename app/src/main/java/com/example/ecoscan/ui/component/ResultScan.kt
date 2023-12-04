package com.example.ecoscan.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoscan.R
import com.example.ecoscan.ui.theme.EcoScanTheme

@Composable
fun ResultScan(
    foodName: String
) {

    androidx.compose.material.Card (
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxHeight()
            .padding(20.dp),
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, Color.Black)
    ) {
        Column (
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth()
                    .fillMaxHeight()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = foodName,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(top = 20.dp, start = 16.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = stringResource(id = R.string.foodName, foodName)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ResultScanPreview() {
    EcoScanTheme {
        ResultScan(foodName = "Seblak")
    }
}