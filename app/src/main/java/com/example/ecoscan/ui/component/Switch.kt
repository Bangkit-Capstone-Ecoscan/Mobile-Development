package com.example.ecoscan.ui.component

import androidx.compose.material.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.ecoscan.ui.theme.EcoScanTheme

@Composable
fun SwitchMinimal() {
    var checked by remember { mutableStateOf(false) }

    Switch(
        checked = checked,
        onCheckedChange = {
            checked = it
        }
    )
}

@Preview(showBackground = true)
@Composable
fun SwitchMinimalPreview(){
    EcoScanTheme {
        SwitchMinimal()
    }
}