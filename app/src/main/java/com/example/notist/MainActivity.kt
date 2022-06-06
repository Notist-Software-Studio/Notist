package com.example.notist

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.notist.navigation.NavRoutes
import com.example.notist.navigation.Routes
import com.example.notist.ui.theme.NotistTheme
import com.example.notist.presentation.bar.bottomNavigation
import com.example.notist.presentation.bar.upNavigation
import com.example.notist.presentation.courses.MyCourseApp
import com.example.notist.presentation.courses.myRef
import com.example.notist.presentation.login.LoginPage
import com.example.notist.presentation.screens.Home
import com.example.notist.presentation.screens.MyLibrary
import com.example.notist.presentation.screens.Profile
import com.example.notist.presentation.screens.Shop

//import com.example.notist.presentation.courses.MyCourseApp
//import com.example.notist.presentation.screens.Home
//import com.example.notist.presentation.screens.Courses
//import com.example.notist.presentation.screens.Profile
//import com.example.notist.presentation.screens.MyLibrary
//import com.example.notist.presentation.screens.Shop
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotistApp()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotistApp()
}

@Composable
fun NotistApp() {
    NotistTheme {
        LoginPage()
    }
}

