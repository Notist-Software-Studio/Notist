package com.example.notist.presentation.login
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notist.navigation.NavRoutes
import com.example.notist.navigation.Routes
import com.example.notist.presentation.screens.Home

@Composable
fun LoginPage(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.StartPage.route) {

        composable(Routes.StartPage.route) {
            StartPage(navController = navController)
        }
        composable(Routes.Login.route) {
            Login(navController = navController)
        }
        composable(Routes.SignUp.route) {
            SignUp(navController = navController)
        }
        composable(NavRoutes.Home.route) {
            Home()
        }
    }
}