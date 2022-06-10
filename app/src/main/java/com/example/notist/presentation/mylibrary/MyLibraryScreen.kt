package com.example.notist.presentation.mylibrary

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notist.navigation.Routes

@Composable
fun MyLibraryScreen(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.MyLibrary.route) {

//        composable(Routes.MyLibrary.route) {
//            MyLibrary(navController = navController)
//        }
//        composable(Routes.LinearAlgebra.route) {
//            LinearAlgebra(navController = navController)
//        }
//        composable(Routes.LogicDesign.route) {
//            LogicDesign(navController = navController)
//        }
//        composable(Routes.DataStructures.route) {
//            DataStructures(navController = navController)
//        }
    }
}