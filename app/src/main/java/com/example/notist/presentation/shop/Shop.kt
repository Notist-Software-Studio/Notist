package com.example.notist.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notist.UserViewModel
import com.example.notist.navigation.NavRoutes
import com.example.notist.presentation.login.Login

@Composable
fun Shop() {
    val vm by remember { mutableStateOf(UserViewModel()) }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("Shop Screen", style = MaterialTheme.typography.h5)
        Button(
            onClick = { vm.hunger.value++ },
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF5C6BC0)
            ),
            modifier = Modifier
                .width(100.dp)
                .height(50.dp)
        ) {
            Text(
                text = "${vm.hunger.value}",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }

}
@Preview(showBackground = true)
@Composable
fun DefaultPreview20() {
    val navController = rememberNavController()
    Shop()
}