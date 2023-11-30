package com.example.ecoscan.ui.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecoscan.R
import com.example.ecoscan.ui.theme.EcoScanTheme
import com.example.ecoscan.ui.theme.Gold
import com.example.ecoscan.ui.theme.Green

@Composable
fun LoginScreen(
    onClickBtn: () -> Unit,
    navigateToHome: () -> Unit,
) {
    LoginScreenLayout(
        onClickBtn =  onClickBtn ,
        navigateToHome = navigateToHome
    )
}

@Composable
fun LoginScreenLayout(
    modifier: Modifier = Modifier,
    onClickBtn: () -> Unit,
    navigateToHome: () -> Unit,
) {

    var text by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }

    val ecoScanText = buildAnnotatedString {
        withStyle(style = SpanStyle(Green)) {
            append("ECO")
        }
        withStyle(style = SpanStyle(Gold)) {
            append("SCAN")
        }
    }

    val loginText = "Register"
    val registerText = buildAnnotatedString {
        append("Don't have account?  ")
        withStyle(
            style = SpanStyle(
                color = Color.Blue,
                textDecoration = TextDecoration.Underline
            )
        ) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxHeight(1f),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Row(
            modifier = Modifier
                .padding(top = 40.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                /// Top Image
                modifier = Modifier
                    .size(360.dp,250.dp),
                painter = painterResource(id = R.drawable.loginimagevector),
                contentDescription = "loginImage",
            )
        }
        Column(
            modifier = Modifier
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
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
                        .fillMaxHeight(1f)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            modifier = Modifier,
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
                                .padding(top = 20.dp),
                            text = "Sign In Now to Continue",
                            textAlign = TextAlign.Justify
                        )
                    }

                    /*
                    Email Text Field
                 */
                    Row(
                        modifier = Modifier
                            .padding(top = 20.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        OutlinedTextField(
                            value = text,
                            onValueChange = { newEmail ->
                                text = newEmail
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
                            placeholder = {
                                Text(
                                    text = "Masukan Email Anda ",
                                    fontSize = 12.sp,
                                    color = Color.Gray
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
                            .fillMaxWidth(),
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
                            maxLines = 1
                        )
                    }

                    /*
                    Button Sign in
                 */
                    Row(
                        modifier = Modifier
                            .padding(top = 35.dp, start = 70.dp, end = 70.dp)
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .height(40.dp),
                            onClick = {
                                navigateToHome()
                            },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(Gold)
                        ) {
                            Text(
                                text = "Sign In",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                            )
                        }
                    }

                    /*
                    Clickable Text go to Register Screen
                 */
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(1f)
                            .fillMaxWidth()
                            .padding(top = 15.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ClickableText(
                            text = registerText,
                            style = TextStyle.Default,
                            onClick = { offset ->
                                registerText.getStringAnnotations(offset, offset)
                                    .firstOrNull()?.let { span ->
                                        onClickBtn()
                                    }

                            })
                    }
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    EcoScanTheme{
        LoginScreen(onClickBtn = { /*TODO*/ },
            navigateToHome = {}
        )
    }
}

