package com.example.notist.presentation.bar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun upNavigation(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {Text("Notist")},
        backgroundColor = Color(0xFF5C6BC0),
        contentColor = Color.White
    )
}

@Composable
fun CustomTopAppBar(navController: NavHostController, title: String, showBackIcon : Boolean) {
    TopAppBar(
        backgroundColor = Color(0xFF5C6BC0),
        title = {
            Text(text = title,color = Color.White,fontWeight = FontWeight.Bold,fontSize = 20.sp)
        },
        navigationIcon = if (showBackIcon && navController.previousBackStackEntry != null) {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = "Back"
                    )
                }
            }
        } else {
            null
        }
    )
}
@Composable
fun CustomTopAppBar2(navController: NavHostController, title: String, showBackIcon : Boolean) {
    TopAppBar(
        backgroundColor = Color(0xc4ccd4ff),
        title = {
            Text(text = title,color = Color.White)
        },
        navigationIcon = if (showBackIcon && navController.previousBackStackEntry != null) {
            {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        tint = Color.White,
                        contentDescription = "Back"
                    )
                }
            }
        } else {
            null
        }
    )
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    val navController = rememberNavController()
    CustomTopAppBar(navController = navController,"Notist",true)
    //Spacer(modifier = Modifier.height(20.dp))
    CustomTopAppBar2(navController = navController," ",true)
}

