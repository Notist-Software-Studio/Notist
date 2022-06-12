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
fun Shop(
    money : MutableState<Int>
) {
    //var money = rememberSaveable { mutableStateOf(5780) }
    Scaffold(content = {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Text("Shop Screen", style = MaterialTheme.typography.h5)
            ShopDescription()
            Currency(money.value)
            Spacer(modifier = Modifier.height(20.dp))
            FoodGrid(money)
        }
    })
}

