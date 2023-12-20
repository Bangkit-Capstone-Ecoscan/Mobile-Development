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
import androidx.compose.ui.window.DialogProperties
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.example.ecoscan.R
import com.example.ecoscan.data.pref.DataResultScan
import com.example.ecoscan.data.pref.GetImageUrl
import com.example.ecoscan.data.pref.UserModel
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
    navigateToSubsScreen: () -> Unit
) {
    Scaffold(
        topBar = { TopBarScan() }
    ) {
        ScanScreenContent(
            navigateToResult = { navigateToResult() },
            navigateToSubsScreen = { navigateToSubsScreen() }
        )
    }
}

@Composable
fun ScanScreenContent(
    modifier: Modifier = Modifier,
    navigateToResult: () -> Unit,
    navigateToSubsScreen: () -> Unit,
    context: Context = LocalContext.current,
    viewModel: ScanViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),

    ) {

    var showDialog by remember {
        mutableStateOf(false)
    }

    var showDialogToDetail by remember {
        mutableStateOf(false)
    }

    var showLoading by remember {
        mutableStateOf(false)
    }

    var showLoadingScan by remember {
        mutableStateOf(false)
    }

    var showAlertSubs by remember {
        mutableStateOf(false)
    }

    var showLoadingDialogToDetail by remember {
        mutableStateOf(false)
    }

    var currentImageUri: Uri? = null

    var capturedImageUri by remember {
        mutableStateOf<Uri>(Uri.EMPTY)
    }


    val getQuota by viewModel.getQuota().observeAsState()
    val imageUrl by viewModel.getImageUrl().observeAsState()
    val storeResult by viewModel.storeResults.observeAsState()
    val storeImages by viewModel.storeImages.observeAsState()
    val getResults by viewModel.getResult().observeAsState()
    val upload by viewModel.upload.observeAsState()


    when (val uistate = storeImages) {
        is UiState.Loading -> {
            showLoading = true
        }

        is UiState.Success -> {
            showDialog = true
//            Log.d("checkUrl", "ScanScreenContent: ${uistate.data.url}")
            viewModel.saveImageUrl(
                GetImageUrl(
                    uistate.data.url
                )
            )
            when (val scan = upload) {
                is UiState.Loading -> {
                    showLoadingScan = true
                }

                is UiState.Success -> {
                    showLoadingScan = false
                    showDialogToDetail = true
                    showLoading = true
                    scan.data.let {
                        when (storeResult) {
                            is UiState.Loading -> {
                                showLoadingDialogToDetail = true
                            }

                            is UiState.Success -> {
                                navigateToResult()
                            }

                            else -> {

                            }
                        }
                        viewModel.saveResult(
                            DataResultScan(
                                uistate.data.url,
                                it.modelResponse.calcium,
                                it.modelResponse.carbohydrates,
                                it.modelResponse.emission,
                                it.modelResponse.fat,
                                it.modelResponse.foodName,
                                it.modelResponse.protein,
                                it.modelResponse.vitamins,
                            )
                        )
                        viewModel.saveSession(
                            UserModel(
                                it.user.username,
                                it.token,
                                it.user.quota
                            )
                        )
//                        Log.d("checkToken", "ScanScreenContent: ${it.token}")
                    }

                }

                else -> {
                }
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
            if (showDialogToDetail) {
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
                                getResults.let {
                                    if (it != null) {
                                        viewModel.storeResult(
                                            it.calcium.toString(),
                                            it.carbon.toString(),
                                            it.emission.toString(),
                                            it.fat.toString(),
                                            it.foodName.toString(),
                                            it.protein.toString(),
                                            it.vitamin.toString(),
                                            it.url.toString()
                                        )
                                    }
                                }
                            },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(
                                MaterialTheme.colorScheme.primary
                            )
                        ) {
                            if (showLoadingDialogToDetail) {
                                androidx.compose.material.CircularProgressIndicator(
                                    modifier = Modifier.size(24.dp),
                                    color = Color.Gray
                                )
                            } else {
                                Text(
                                    text = "Ok",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp,
                                )
                            }
                        }
                    }
                )
            }

            if (showDialog) {
                androidx.compose.material3.AlertDialog(
                    onDismissRequest = {
                        showDialog = false
                    },
                    title = {
                        Text(text = "Scan This Food ?")
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                imageUrl?.let { viewModel.scanImage(it.imageUrl) }
                            },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(
                                MaterialTheme.colorScheme.primary
                            )
                        ) {
                            if (showLoadingScan) {
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
                    },
                    properties = DialogProperties(
                        dismissOnClickOutside = true,
                        dismissOnBackPress = true
                    )
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

            if (showAlertSubs) {
                androidx.compose.material3.AlertDialog(
                    onDismissRequest = {
                        showAlertSubs = false
                    },
                    icon = {
                        Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = "checkCircle"
                    )
                    },
                    title = {
                        Text(text = "Your Quota Has Run Out")
                    },
                    text = {
                           Text(text = "Buy Subscription")
                    },
                    confirmButton = {
                        Button(
                            onClick = {
                                navigateToSubsScreen()
                            },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(
                                MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Text(
                                text = "Buy",
                                 color = Color.White,
                                fontWeight = FontWeight.Normal,
                                fontSize = 20.sp,
                            )
                        }
                    },
                    dismissButton = {
                      Button(
                          onClick = {
                              showAlertSubs = false
                          },
                          shape = RoundedCornerShape(20.dp),
                          colors = ButtonDefaults.buttonColors(
                              MaterialTheme.colorScheme.primary
                          )
                      ) {
                          Text(
                              text = "No",
                              color = Color.White,
                              fontWeight = FontWeight.Normal,
                              fontSize = 20.sp

                          )
                      }
                    },
                    properties = DialogProperties(
                        dismissOnClickOutside = showAlertSubs,
                        dismissOnBackPress = showAlertSubs
                    )
                )
            }

//            val imageFile =
//                context.uriToFile(capturedImageUri, context).reduceFileImage()

            Row(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 8.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {
                        if (getQuota?.quota!! < 1) {
                            showAlertSubs = true
                        } else {
                            val imageFile =
                                context.uriToFile(capturedImageUri, context).reduceFileImage()
                            viewModel.storeImage(imageFile)
                        }
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
            navigateToResult = {},
            navigateToSubsScreen = {}
        )
    }
}
