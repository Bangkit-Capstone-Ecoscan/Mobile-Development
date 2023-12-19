package com.example.ecoscan.ui.screen.subscribe

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoscan.data.models.Paket
import com.example.ecoscan.data.pref.UserModel
import com.example.ecoscan.di.Injection
import com.example.ecoscan.ui.ViewModelFactory
import com.example.ecoscan.ui.common.UiState
import com.example.ecoscan.ui.component.AlertDialogPaket
import com.example.ecoscan.ui.component.ListPackage
import com.example.ecoscan.ui.component.TopBarBack
import com.example.ecoscan.ui.theme.EcoScanTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SubscribeScreen(
    backNavigation: () -> Unit,
    context: Context = LocalContext.current,
    viewModel: SubscribeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
) {
    var showDialog by remember {
        mutableStateOf(false)
    }

    var showDialogSuccess by remember {
        mutableStateOf(false)
    }

    var showLoading by remember {
        mutableStateOf(false)
    }

    var selectedPackage by remember {
        mutableStateOf<Paket?>(null)
    }

    val paket by viewModel.paket.observeAsState()

    Scaffold(
        topBar = {
            TopBarBack(
                backNavigation = { backNavigation() }
            )
        }
    ) {
        viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    showLoading = true
                    if (showLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(24.dp),
                            color = Color.Gray
                        )
                    }
                    viewModel.getAllPaket()
                }

                is UiState.Success -> {
                    SubscribeContent(
                        data = uiState.data,
                        showDialog = showDialog,
                        onItemClick = { packageData ->
                            showDialog = true
                            selectedPackage = packageData
                        }
                    )
                    if (showDialog && selectedPackage != null) {
                        AlertDialogPaket(
                            onDismissRequest = {
                                showDialog = false
                                selectedPackage = null
                            },
                            onConfirmation = {
                                // Handle confirmation logic here
                                Log.d("SubscribeScreen", "${selectedPackage?.paket}")
                                viewModel.paketQuota(selectedPackage?.paket ?: "")
                                showDialog = false
                                selectedPackage = null
                            },
                            dialogTitle = "Yakin Membeli Kuota",
                            dialogText = "Paket ${selectedPackage?.paket} dengan keuntungan ${selectedPackage?.desc}",
                            icon = Icons.Default.Info
                        )
                    }
                }

                is UiState.Error -> {
                    showLoading = false
                    Toast.makeText(context, "An error occurred", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@Composable
fun SubscribeContent(
    modifier: Modifier = Modifier,
    data: List<Paket>,
    showDialog: Boolean,
    onItemClick: (Paket) -> Unit
) {
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
        items(data) { data ->
            ListPackage(
                paket = data.paket,
                price = "Rp ${data.price}",
                desc = data.desc,
                onClick = { onItemClick(data) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SubscribeScreenPreview() {
    EcoScanTheme {
        SubscribeScreen(backNavigation = {})
    }
}