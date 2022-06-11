package com.example.notist.presentation.profile

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notist.navigation.NavRoutes
import com.example.notist.navigation.Routes
import com.example.notist.presentation.mylibrary.MyLibrary

@Composable
fun ProfileScreen(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoutes.Profile.route) {

        composable(NavRoutes.Profile.route) {
            Profile(navController = navController)
        }
//
    }
}
