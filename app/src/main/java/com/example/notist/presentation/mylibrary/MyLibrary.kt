package com.example.notist.presentation.mylibrary

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notist.R
import com.example.notist.navigation.NavRoutes
import com.example.notist.presentation.bar.upNavigation
import com.example.notist.presentation.login.StartPage

@Composable
fun MyLibrary(navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize()) {
        ScaffoldWithTopBarMyLibrary(navController)
    }
}


@Composable
fun ScaffoldWithTopBarMyLibrary(navController: NavHostController) {
    Scaffold(
        topBar = {
            upNavigation(section = "        MyLibrary")

            Icon(
                painter = painterResource(id = R.drawable.ic_action_library),
                contentDescription = "Localized description",
                modifier = Modifier.padding(vertical = 15.dp, horizontal = 20.dp) ,
                tint = Color.White
            )
        }, content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(30.dp))
                Box(modifier = Modifier.padding(20.dp, 0.dp, 20.dp, 0.dp)) {

                    Button(
                        onClick = { },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = (Color.White),
                            contentColor = Color.Gray
                        )

                        //.padding(vertical = 12.dp, horizontal = 20.dp)
                    ) {
                        Text(text = "Search for notes",textAlign = TextAlign.Left, modifier = Modifier.size(250.dp,20.dp),
                            color = Color(0xB8B7B3B9)
                        )
                        //Spacer(modifier = Modifier.height(20.dp))

                    }
                    Icon(
                        painter = painterResource(id = R.drawable.ic_action_search),
                        contentDescription = "Localized description",
                        modifier = Modifier.padding(vertical = 13.dp, horizontal = 20.dp) ,
                        tint = Color(0xB8B7B3B9)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                       onClick = { },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                            .padding(vertical = 0.dp, horizontal = 60.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = (Color(0xE0DCDCDC)),
                            contentColor = Color.White

                        )
                        //CS  EECS  EE
                    ) {

                        Text(text = "All",fontSize = 12.sp,textAlign = TextAlign.Left)
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "CS",fontSize = 12.sp,textAlign = TextAlign.Left)
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "EECS",fontSize = 12.sp,textAlign = TextAlign.Left)
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "EE",fontSize = 12.sp,textAlign = TextAlign.Left)
                    }
                }
//                Spacer(modifier = Modifier.height(20.dp))
//                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
//                    Button(
//                        onClick = { navController.navigate(Routes.LinearAlgebra.route) },
//                        shape = RoundedCornerShape(10.dp),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(55.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            backgroundColor = (Color(0x74828B9B))
//                        )
//                    ) {
//                        Column(horizontalAlignment = Alignment.Start,modifier = Modifier.size(300.dp,50.dp)){
//                            Text(text = "Linear Algebra",fontSize = 16.sp,textAlign = TextAlign.Left,fontWeight = FontWeight.Bold)
//                            Text(text = "EECS",fontSize = 12.sp,textAlign = TextAlign.Left,color = Color.White)
//                        }
//                    }
//                }
//                Spacer(modifier = Modifier.height(20.dp))
//                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
//                    Button(
//                        onClick = { navController.navigate(Routes.LogicDesign.route) },
//                        shape = RoundedCornerShape(10.dp),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(55.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            backgroundColor = (Color(0x74828B9B))
//                        )
//                    ) {
//                        Column(horizontalAlignment = Alignment.Start,modifier = Modifier.size(300.dp,50.dp)){
//                            Text(text = "Logic Design",fontSize = 16.sp,textAlign = TextAlign.Left,fontWeight = FontWeight.Bold)
//                            Text(text = "EECS",fontSize = 12.sp,textAlign = TextAlign.Left,color = Color.White)
//                        }
//                    }
//                }
//                Spacer(modifier = Modifier.height(20.dp))
//                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
//                    Button(
//                        onClick = { navController.navigate(Routes.DataStructures.route) },
//                        shape = RoundedCornerShape(10.dp),
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(55.dp),
//                        colors = ButtonDefaults.buttonColors(
//                            backgroundColor = (Color(0x74828B9B))
//                        )
//                    ) {
//                        Column(horizontalAlignment = Alignment.Start,modifier = Modifier.size(300.dp,50.dp)){
//                            Text(text = "Data Structures",fontSize = 16.sp,textAlign = TextAlign.Left,fontWeight = FontWeight.Bold)
//                            Text(text = "EECS",fontSize = 12.sp,textAlign = TextAlign.Left,color = Color.White)
//                        }
//                    }
//                }

                /*Spacer(modifier = Modifier.height(20.dp))
                ClickableText(
                    text = AnnotatedString("Forgot password?"),
                    onClick = { navController.navigate(Routes.ForgotPassword.route) },
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily.Default
                    )
                )*/
            }

        })
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview1() {
    val navController = rememberNavController()
    MyLibrary(navController = navController)
}