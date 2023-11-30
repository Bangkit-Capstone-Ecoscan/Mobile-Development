package com.example.ecoscan.ui.screen.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil.compose.AsyncImage
import com.example.ecoscan.ui.component.TopBarProfile
import com.example.ecoscan.ui.theme.EcoScanTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileScreen(){
    Scaffold (
        topBar = { TopBarProfile() }
    ) {
        ProfileContent(
            image = "https://imgx.sonora.id/crop/0x0:0x0/700x465/photo/2020/02/17/2398193539.png",
            username = "EcoScanUser",
            subscribe = "Langganan",
            email = "EcoScan@gmail.com",
            password = "************"
        )
        TopBarProfile()
    }
}

@Composable
fun ProfileContent(
    image: String,
    username: String,
    subscribe: String,
    email: String,
    password: String,
    modifier: Modifier = Modifier

){
    Column(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .aspectRatio(1f)
                    .size(80.dp) // Adjust the size as needed
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
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
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
                        modifier = Modifier.padding(vertical = 0.dp,horizontal = 10.dp)
                    )
                }
//              TextField Email
                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email -> },
                        modifier = Modifier
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(20.dp)
                            ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Mail,
                                contentDescription = "emailIcon"
                            )
                        }
                    )
                }
//              TextField Password
                Row(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .fillMaxWidth(1f),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedTextField(
                        value = password,
                        onValueChange = { newPassword -> },
                        modifier = Modifier
                            .background(
                                color = Color.White,
                                shape = RoundedCornerShape(20.dp)
                            ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "emailIcon"
                            )
                        },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Check,
                                contentDescription = "checkEye"
                            )
                        }
                    )
                }
                Spacer(modifier = Modifier.height(130.dp))
                Box(
                    modifier = Modifier
                        .background(
                            MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                            shape = CircleShape
                        )
                        .clickable { }
                        .padding(vertical = 8.dp, horizontal = 15.dp),
                ) {
                    Text(
                        text = "Sign Out",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodySmall.copy(
                            fontWeight = FontWeight.ExtraBold,
                            color = MaterialTheme.colorScheme.onPrimary
                        ),
                        modifier = Modifier.padding(vertical = 0.dp,horizontal = 14.dp)
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
        ProfileScreen()
    }
}
