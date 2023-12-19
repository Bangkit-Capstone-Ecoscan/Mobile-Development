package com.example.ecoscan.ui.screen.scan

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.example.ecoscan.R
import com.example.ecoscan.data.pref.DataResultScan
import com.example.ecoscan.data.utils.getImageUri
import com.example.ecoscan.data.utils.reduceFileImage
import com.example.ecoscan.data.utils.uriToFile
import com.example.ecoscan.di.Injection
import com.example.ecoscan.ui.ViewModelFactory
import com.example.ecoscan.ui.common.UiState
import com.example.ecoscan.ui.component.TopBarScan
import com.example.ecoscan.ui.theme.EcoScanTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ScanScreen(
    navigateToResult: () -> Unit,
) {
    Scaffold(
        topBar = { TopBarScan() }
    ) {
        ScanScreenContent(
            navigateToResult = {navigateToResult()}
        )
    }
}

@Composable
fun ScanScreenContent(
    modifier: Modifier = Modifier,
    navigateToResult: () -> Unit,
    context: Context = LocalContext.current,
    viewModel: ScanViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),

    ) {

    var showDialog by remember {
        mutableStateOf(false)
    }

    var showLoading by remember {
        mutableStateOf(false)
    }


    var currentImageUri: Uri? = null

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }

    val storeResult by viewModel.storeResults.observeAsState()
    val storeImages by viewModel.storeImages.observeAsState()
    val getResults by viewModel.getResult().observeAsState()
    val upload by viewModel.upload.observeAsState()


    when (val uistate = upload) {
        is UiState.Loading -> {
            showLoading = true
        }

        is UiState.Success -> {

            showLoading = true
            when (val storeImage = storeImages) {
                is UiState.Success -> {
                    showDialog = true
                    uistate.data.let {
                        when (storeResult) {
                            is UiState.Success -> {
                                navigateToResult()
                            }

                            else -> {}
                        }
                        viewModel.saveResult(
                            DataResultScan (
                                storeImage.data.url,
                                it.calcium.toString(),
                                it.carbohydrates.toString(),
                                it.emission.toString(),
                                it.fat.toString(),
                                it.foodName.toString(),
                                it.protein.toString(),
                                it.vitamins.toString(),
                            )
                        )

                    }
                    Log.d("Check Url", "ScanScreenContent: ${storeImage.data.url}")
                }

                else -> {}
            }

        }


        is UiState.Error -> {
//            Toast.makeText(
//                context,
//                (upload as UiState.Error).errorMessage.toString(),
//                Toast.LENGTH_SHORT
//            ).show()
        }

        else -> {}
    }


    val launcherCamera =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture()) {
            capturedImageUri = currentImageUri!!
        }

    val launcherGallery = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            capturedImageUri = currentImageUri!!
        } else {
            Log.d("Photo Picker", "No Media Selected")
        }

    }


    fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    fun startCamera() {
        currentImageUri = context.getImageUri(context)
        launcherCamera.launch(currentImageUri)
    }

    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) {
        if (it) {
            startCamera()
        }
    }




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
            Box(modifier = Modifier) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                        .padding(bottom = 16.dp, start = 16.dp, end = 16.dp),
                    elevation = 10.dp
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .fillMaxHeight()
                            .padding(start = 20.dp, end = 20.dp)
                            .clip(RoundedCornerShape(20.dp)),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (capturedImageUri.path?.isNotEmpty() == true) {
                            AsyncImage(
                                model = capturedImageUri,
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentDescription = "LogoApp",
                                contentScale = ContentScale.Fit
                            )
                        } else if (capturedImageUri.path?.isEmpty() == true) {
                            Image(
                                painter = painterResource(id = R.drawable.logoapp),
                                contentDescription = "",
                                contentScale = ContentScale.FillBounds
                            )
                        }
                    }
                }

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    if (showLoading) {
                        val infiniteTransition = rememberInfiniteTransition()

                        val boxSize = 420.dp
                        val animationDuration = 5000

                        val heightAnimation by infiniteTransition.animateValue(
                            initialValue = 0.dp,
                            targetValue = boxSize,
                            typeConverter = Dp.VectorConverter,
                            animationSpec = infiniteRepeatable(
                                animation = keyframes {
                                    durationMillis = animationDuration
                                    0.dp at 0 // ms
                                    boxSize at animationDuration / 2
                                    0.dp at animationDuration using FastOutSlowInEasing
                                }
                                // Use the default RepeatMode.Restart to start from 0.dp after each iteration
                            )
                        )
                        Spacer(modifier = Modifier.height(heightAnimation))
                        Divider(
                            thickness = 4.dp, color = Color.Black,
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(start = 16.dp, end = 16.dp, top = 16.dp)
                        )
                    }
                }

            }



            if (showDialog) {

                androidx.compose.material3.AlertDialog(
                    onDismissRequest = {
                        showDialog = false
                    },
                    title = {
                        Text(text = "Scan Berhasil")
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = "checkCircle"
                        )
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                getResults?.apply {
                                    viewModel.storeResult(
                                        calcium,
                                        carbon,
                                        emission,
                                        fat,
                                        foodName,
                                        protein,
                                        vitamin,
                                        url
                                    )
                                    Log.d("checkUrl", "ScanScreenContent: $url")
                                }

                            },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(
                                MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(
                                text = "Yes",
                                color = Color.Black
                            )
                        }
                    }
                )
            }


            /*
                Button Gallery And Camera
            */
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, end = 40.dp, top = 20.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        startGallery()
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
                        val permissionCheckResult =
                            ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)
                        if (permissionCheckResult == PackageManager.PERMISSION_GRANTED) {
                            startCamera()
                        } else {
                            // Request a permission
                            permissionLauncher.launch(Manifest.permission.CAMERA)
                        }
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
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        val imageFile =
                            context.uriToFile(capturedImageUri, context).reduceFileImage()
                        viewModel.uploadImage(imageFile)
                        viewModel.storeImage(imageFile)
//
                    },
                    modifier = Modifier
                        .width(150.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
                ) {
                    if (showLoading) {
                        androidx.compose.material.CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.Gray
                        )
                    } else {
                        Text(
                            text = "Scan",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                        )
                    }
                }
            }

        }
    }

}


@Preview(showBackground = true)
@Composable
fun ScanScreenPreview() {
    EcoScanTheme {
        ScanScreenContent(
            navigateToResult = {}
        )
    }
}
