package com.example.ecoscan.ui.screen.register

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoscan.R
import com.example.ecoscan.di.Injection
import com.example.ecoscan.ui.ViewModelFactory
import com.example.ecoscan.ui.theme.EcoScanTheme
import com.example.ecoscan.ui.theme.Gold
import com.example.ecoscan.ui.theme.Green
//import androidx.compose.runtime.livedata.observeAsState

@Composable
fun RegisterScreen () {
    RegisterScreenLayout()
}


@Composable
fun RegisterScreenLayout(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: RegisterViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context))
    ),
) {
    var password by rememberSaveable { mutableStateOf("") }
    var username by rememberSaveable { mutableStateOf("") }
    var lastName by rememberSaveable { mutableStateOf("") }
    var firstName by rememberSaveable { mutableStateOf("") }


//    val createAccount by viewModel.createAccount.observeAsState()

    val ecoScanText = buildAnnotatedString {
        withStyle(style = SpanStyle(Green)) {
            append(stringResource(id = R.string.title_eco))
        }
        withStyle(style = SpanStyle(Gold)) {
            append(stringResource(id = R.string.title_scan))
        }
    }


    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxHeight(1f)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .padding(top = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                /// Top Image
                modifier = Modifier
                    .fillMaxWidth()
                    .size(360.dp, 250.dp)
                    .padding(top = 15.dp),
                painter = painterResource(id = R.drawable.loginimagevector),
                contentDescription = "loginImage",
            )
        }
        Column {
            Box(
                modifier = Modifier
                    .height(600.dp)
                    .fillMaxWidth(1f),
            ) {
                Image(                          //Image Background Left
                    modifier = Modifier
                        .align(Alignment.BottomStart),
                    imageVector = ImageVector.vectorResource(id = R.drawable.circle),
                    contentDescription = "backgroundImg"
                )

                Image(
                    // ImageBackground Right
                    modifier = Modifier
                        .padding(top = 50.dp)
                        .align(Alignment.TopEnd),
                    imageVector = ImageVector.vectorResource(id = R.drawable.elippse),
                    contentDescription = "backgroundImg",
                )
                Column(
                    modifier = Modifier
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(top = 20.dp),
                            text = ecoScanText,
                            fontWeight = FontWeight.Bold,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center

                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier
                                .padding(top = 40.dp),
                            text = "Create Your Account To Login",
                            textAlign = TextAlign.Justify
                        )
                    }

                    /*
                        UserName TextField
                     */
                    Row(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(1f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        OutlinedTextField(
                            value = username ,
                            onValueChange = { newUsername ->
                                username = newUsername
                            },
                            modifier = Modifier
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .height(50.dp),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "emailIcon"
                                )
                            },
                            placeholder = { Text(
                                text = "Masukan username anda ",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                            },
                            shape = RoundedCornerShape(20.dp),
                            maxLines = 1
                        )
                    }

                    /*
                        FirstName Text Field
                     */
                    Row(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(1f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        OutlinedTextField(
                            value = lastName,
                            onValueChange = { newLastName ->
                                lastName = newLastName
                            },
                            modifier = Modifier
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .height(50.dp),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = "emailIcon"
                                )
                            },
                            placeholder = { Text(
                                text = "Masukan Last Name anda ",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                            },
                            shape = RoundedCornerShape(20.dp),
                            maxLines = 1
                        )
                    }

                    /*
                        FirstName Text Field
                     */
                    Row(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(1f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        OutlinedTextField(
                            value = firstName,
                            onValueChange = { newFirstName ->
                                firstName = newFirstName
                            },
                            modifier = Modifier
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .height(50.dp),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = "emailIcon"
                                )
                            },
                            placeholder = { Text(
                                text = "Masukan FirstName anda ",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                            },
                            shape = RoundedCornerShape(20.dp),
                            maxLines = 1
                        )
                    }

                    /*
                        Password TextField
                     */
                    Row(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(1f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        OutlinedTextField(
                            value = password,
                            onValueChange = { newPasword ->
                                password = newPasword
                            },
                            modifier = Modifier
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .height(50.dp),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Lock,
                                    contentDescription = "emailIcon"
                                )
                            },
                            trailingIcon = {
                                Icon(
                                    modifier = Modifier
                                        .size(18.dp),
                                    painter = painterResource(id = R.drawable.eyepassword),
                                    contentDescription = "eyePassword"
                                )
                            },
                            placeholder = { Text(
                                text = "Masukan password anda ",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                            },
                            shape = RoundedCornerShape(20.dp),
                            maxLines = 1
                        )
                    }

                    /*
                        Button Sign Up
                     */
                    Row(
                        modifier = Modifier
                            .padding(top = 35.dp, start = 70.dp, end = 70.dp)
                            .fillMaxWidth()
                            .height(40.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth(1f),
                            onClick = {
                                viewModel.registerUser(username,firstName, lastName, password)
                            },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(Gold)
                        ) {
                            Text(
                                text = "Sign Up",
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
}

@SuppressLint("ComposableNaming")
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun layoutPreview () {
    EcoScanTheme {
        RegisterScreenLayout()
    }
}