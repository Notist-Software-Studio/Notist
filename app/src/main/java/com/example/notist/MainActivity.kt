package com.example.notist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notist.navigation.NavRoutes
import com.example.notist.ui.theme.NotistTheme
import com.example.notist.presentation.bar.bottomNavigation
import com.example.notist.presentation.bar.upNavigation
import com.example.notist.presentation.courses.MyCourseApp
import com.example.notist.presentation.screens.Home
import com.example.notist.presentation.screens.Courses
import com.example.notist.presentation.screens.Profile
import com.example.notist.presentation.screens.MyLibrary
import com.example.notist.presentation.screens.Shop

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

    val navController = rememberNavController()

    NotistTheme {
        Scaffold(
            topBar = { upNavigation() },
            bottomBar = { bottomNavigation(navController = navController) }
        ) { padding -> 20.dp
            NavHost(
                navController = navController,
                startDestination = NavRoutes.Home.route
            ) {
                composable(NavRoutes.Home.route) { Home() }
                composable(NavRoutes.Courses.route) { MyCourseApp() }
                composable(NavRoutes.Profile.route) { Profile() }
                composable(NavRoutes.MyLibrary.route) { MyLibrary() }
                composable(NavRoutes.Shop.route) { Shop() }
            }
        }
    }


}