package com.example.notist.presentation.login

import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.notist.navigation.NavRoutes
import com.example.notist.navigation.Routes
import com.example.notist.presentation.bar.bottomNavigation
import com.example.notist.presentation.bar.upNavigation
import com.example.notist.presentation.courses.MyCourseApp
import com.example.notist.presentation.screens.Home
import com.example.notist.presentation.screens.MyLibrary
import com.example.notist.presentation.screens.Profile
import com.example.notist.presentation.screens.Shop

@Composable
fun LoginPage() {

    val navController = rememberNavController()
    var showBar by rememberSaveable { mutableStateOf(true) }
    val context = LocalContext.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    showBar = when (navBackStackEntry?.destination?.route) {
        "MainScreen" -> false
        "StartPage" -> false
        "SignUp" -> false
        "Login" -> false
        else -> true
    }
    Toast.makeText(context, "$showBar", Toast.LENGTH_LONG).show()
    Scaffold(
        topBar = {
            if (showBar) {
                upNavigation()
            }
        },
        bottomBar = {
            if (showBar) {
                bottomNavigation(navController = navController)
            }
        }
    ) { padding ->
        20.dp
        NavHost(navController = navController, startDestination = Routes.StartPage.route) {

            composable(Routes.StartPage.route) { StartPage(navController = navController) }
            composable(Routes.Login.route) { Login(navController = navController)}
            composable(Routes.SignUp.route) {SignUp(navController = navController)}
            composable(NavRoutes.Home.route) {Home()}
            composable(Routes.MainScreen.route) { LoginPage() }
            composable(NavRoutes.Home.route) { Home() }
            composable(NavRoutes.Courses.route) { MyCourseApp() }
            composable(NavRoutes.Profile.route) { Profile() }
            composable(NavRoutes.MyLibrary.route) { MyLibrary() }
            composable(NavRoutes.Shop.route) { Shop() }
        }
    }

}

