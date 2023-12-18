package com.example.ecoscan.ui.screen.profile

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.ecoscan.R
import com.example.ecoscan.ui.ViewModelFactory
import com.example.ecoscan.ui.component.TopBarProfile
import com.example.ecoscan.ui.theme.EcoScanTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable

fun ProfileScreen(
    navigateToBoorkmark: () -> Unit
) {
    Scaffold(
        topBar = { TopBarProfile(
            navigateToBookmark = {navigateToBoorkmark()}
        )
        }
    ) {
        ProfileContent(
            image = "https://img.freepik.com/premium-vector/account-icon-user-icon-vector-graphics_292645-552.jpg",
            username = "EcoScanUser",
            subscribe = "Bronze",
            email = "EcoScan@gmail.com",
            password = "TestPassword"
        )
    }
}



@Composable
fun ProfileContent(
    image: String,
    username: String,
    subscribe: String,
    email: String,
    password: String,
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    viewModel: ProfileViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = ViewModelFactory.getInstance(context)
    ),
) {
    val context = LocalContext.current as? Activity
    var showPassword by remember { mutableStateOf(false) }

    Column(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .height(50.dp)
            )
            Box(
                modifier = Modifier
                    .size(250.dp) // Adjust the size as needed
                    .clip(CircleShape)
            ) {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .zIndex(0f)
                )
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(12.dp),

                ) {
                Text(
                    text = username,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    modifier = modifier.padding(8.dp)
                )
                Box(
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.onSecondary.copy(alpha = 0.8f),
                            shape = CircleShape
                        )
                        .clickable { }
                        .padding(vertical = 8.dp, horizontal = 15.dp)
                ) {
                    Text(
                        text = subscribe,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onPrimary
                        ),
                        modifier = Modifier.padding(vertical = 0.dp, horizontal = 10.dp)
                    )
                }
//              TextField Email
                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    androidx.compose.material.OutlinedTextField(
                        value = username,
                        onValueChange = {
                            //Do SomeThing
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
                        placeholder = {
                            Text(
                                text = "Masukan Email Anda ",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        },
                        shape = RoundedCornerShape(20.dp),
                        maxLines = 1,
                        enabled = false
                    )
                }
//              TextField Password
                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    androidx.compose.material.OutlinedTextField(
                        value = password,
                        onValueChange = {
                            /// Do SomeThing
                        },
                        modifier = Modifier
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(20.dp),
                            )
                            .align(Alignment.CenterVertically)
                            .height(50.dp),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "emailIcon"
                            )
                        },
                        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val (icon, iconColor) = if (showPassword) {
                                Pair(
                                    painterResource(id = R.drawable.eyepassword),
                                    colorResource(id = R.color.black)
                                )
                            } else {
                                Pair(
                                    painterResource(id = R.drawable.eyepassword),
                                    colorResource(id = R.color.black)
                                )
                            }
                            IconButton(onClick = { showPassword = !showPassword }) {
                                Icon(
                                    icon,
                                    contentDescription = "Visibility",
                                    tint = iconColor
                                )
                            }
                        },
                        placeholder = {
                            Text(
                                text = "Masukan Password Anda ",
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        },
                        shape = RoundedCornerShape(20.dp),
                        maxLines = 1,
                        enabled = false
                    )
                }
                Spacer(modifier = Modifier.height(90.dp))
                Box(
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.8f),
                            shape = CircleShape
                        )
                        .clickable {
                            viewModel.logoutSession()
                            context?.isDestroyed
                            context?.finish()
                        }
                        .padding(vertical = 8.dp, horizontal = 15.dp),
                ) {
                    Text(
                        text = "Sign Out",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onPrimary
                        ),
                        modifier = Modifier.padding(vertical = 0.dp, horizontal = 14.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun ProfileScreenPreview() {
    EcoScanTheme {
        ProfileScreen(
            navigateToBoorkmark = {}
        )
    }
}
