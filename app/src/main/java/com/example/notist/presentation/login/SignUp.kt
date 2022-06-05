package com.example.notist.presentation.login

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notist.R
import com.example.notist.navigation.NavRoutes
import com.example.notist.presentation.bar.CustomTopAppBar
import com.example.notist.presentation.bar.CustomTopAppBar2
import com.example.notist.navigation.Routes
import com.example.notist.ui.theme.Purple700

@Composable
fun SignUp(navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .drawBehind {
                drawRect(
                    color = Color(0xc4ccd4ff),
                    topLeft = Offset(x = 0f, y = 0.dp.toPx()),
                    size = Size(1000.dp.toPx(), 2000.dp.toPx())
                )
            }
    )
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            val onBack: () -> Unit = {
                navController.popBackStack()
            }
            IconButton(onClick = { onBack() }, modifier = Modifier.size(30.dp, 20.dp)) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_action_back),
                    contentDescription = "Localized description",
                    tint = Color.White,
                )
            }
        }}
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val username = remember { mutableStateOf(TextFieldValue()) }
                val email = remember { mutableStateOf(TextFieldValue()) }
                val password = remember { mutableStateOf(TextFieldValue()) }
                Spacer(modifier = Modifier.height(35.dp))
                Text(
                    text = "Welcome",
                    style = TextStyle(
                        fontSize = 30.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color(0xFF5C6BC0),
                        fontWeight = FontWeight.ExtraBold
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Are you ready to start sharing your",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color(0xFF5C6BC0)
                    )
                )
                Text(
                    text = " notes with others?",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily.SansSerif,
                        color = Color(0xFF5C6BC0)
                    )
                )
                Spacer(modifier = Modifier.height(30.dp))
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    label = { Text(text = "Username") },
                    value = username.value,
                    onValueChange = { username.value = it })
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    label = { Text(text = "Email") },
                    value = email.value,
                    onValueChange = { email.value = it })

                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    label = { Text(text = "Password") },
                    value = password.value,
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = { password.value = it })

                Spacer(modifier = Modifier.height(50.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = { navController.navigate(NavRoutes.Home.route) },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF5C6BC0)
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .align(alignment = Alignment.Center)
                    ) {
                        Text(
                            text = "Register",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                }

            }
        }
    }



@Preview(showBackground = true)
@Composable
fun DefaultPreview4() {
    val navController = rememberNavController()
    SignUp(navController = navController)
}