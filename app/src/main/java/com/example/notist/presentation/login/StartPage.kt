package com.example.notist.presentation.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notist.R
import com.example.notist.navigation.Routes

@Composable
fun StartPage(navController: NavHostController) {
    StartPageTop(navController)
}
@Composable
fun StartPageTop(navController: NavHostController){
    Scaffold(
        content = {
            Column(
                modifier = Modifier.fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Box(
                    modifier = Modifier
                        .size(400.dp, 450.dp)
                        .padding(10.dp, 0.dp, 10.dp, 0.dp)
                        .clip(
                            RoundedCornerShape(
                                topEnd = 30.dp,
                                topStart = 30.dp,
                                bottomEnd = 30.dp,
                                bottomStart = 30.dp
                            )
                        )
                        .align(alignment = Alignment.CenterHorizontally)
                        .background(Color(0xffffffff))
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.front),
                        contentDescription = "",
                        modifier = Modifier
                            .size(350.dp, 500.dp)
                            .padding(20.dp, 30.dp, 0.dp, 0.dp)
                    )
                }
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "NOTIST",
                    style = TextStyle(fontSize = 40.sp, fontFamily = FontFamily.SansSerif,color = Color(0xFF5C6BC0)),
                    modifier = Modifier.height(55.dp)
                )
                Text(
                    text = "Start sharing your notes with",
                    style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.SansSerif,color = Color(0xFF5C6BC0)),
                    modifier = Modifier.height(30.dp)
                )
                Text(
                    text = "others",
                    style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.SansSerif,color = Color(0xFF5C6BC0)),
                    modifier = Modifier.height(30.dp)
                )
                Spacer(modifier = Modifier.height(30.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(0.dp,alignment = Alignment.CenterHorizontally),
                    modifier = Modifier.fillMaxWidth().padding(10.dp)
                ) {
                    Button(
                        onClick = { navController.navigate(Routes.Login.route) },
                        shape = RoundedCornerShape(topStart = 10.dp,bottomStart = 10.dp),
                        modifier = Modifier
                            .width(150.dp)
                            .height(60.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF5C6BC0),
                            contentColor = Color.White)

                    ) {
                        ClickableText(
                            text = AnnotatedString("Login"),
                            onClick = { navController.navigate(Routes.Login.route) },
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontFamily = FontFamily.SansSerif,
                                color = Color.White
                            )
                        )
                    }
                    Button(
                        onClick = { navController.navigate(Routes.SignUp.route) },
                        shape = RoundedCornerShape(topEnd = 10.dp,bottomEnd = 10.dp),
                        modifier = Modifier
                            .width(150.dp)
                            .height(60.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White,
                            contentColor = Color(0xFF5C6BC0))

                    ) {
                        Text(
                            text = "Register",
                            style = TextStyle(fontSize = 20.sp, fontFamily = FontFamily.SansSerif)
                        )
                    }
                }



            }

        })
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    val navController = rememberNavController()
    StartPage(navController = navController)
}

