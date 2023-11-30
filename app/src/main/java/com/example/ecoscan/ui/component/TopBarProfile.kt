package com.example.ecoscan.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ecoscan.R
import com.example.ecoscan.ui.theme.EcoScanTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarProfile(
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    TopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Row(
                modifier = Modifier
                    .padding(vertical = 20.dp, horizontal = 0.dp)
            ) {
                Text(
                    text = stringResource(R.string.title_eco),
                )
                Text(
                    text = stringResource(R.string.title_scan)
                )
            }
        },
        actions = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.Default.Bookmarks,
                    contentDescription = "Localized description",
                    tint = Color.Black
                )
            }
        },
        scrollBehavior = scrollBehavior,
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBarProfile() {
    EcoScanTheme {
        TopBarProfile()
    }
}