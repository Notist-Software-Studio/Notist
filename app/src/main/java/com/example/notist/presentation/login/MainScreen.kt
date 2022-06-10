package com.example.notist.presentation.login

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notist.MainViewModel
import com.example.notist.navigation.NavRoutes
import com.example.notist.navigation.Routes
import com.example.notist.presentation.bar.bottomNavigation
import com.example.notist.presentation.bar.upNavigation
import com.example.notist.presentation.courses.MyCourseApp
import com.example.notist.presentation.courses.Teacher
import com.example.notist.presentation.screens.Home
import com.example.notist.presentation.mylibrary.MyLibrary
import com.example.notist.presentation.profile.Profile
import com.example.notist.presentation.profile.profilesettings
import com.example.notist.presentation.screens.Shop

@Composable
fun LoginPage(viewModel: MainViewModel) {

    val navController = rememberNavController()
    var showTopBar by rememberSaveable { mutableStateOf(true) }
    var showBotBar by rememberSaveable { mutableStateOf(true) }
    val context = LocalContext.current
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    showTopBar = when (navBackStackEntry?.destination?.route) {
        "MainScreen" -> false
        "StartPage" -> false
        "SignUp" -> false
        "Login" -> false
        else -> true
    }
    showBotBar = when (navBackStackEntry?.destination?.route) {
        "MainScreen" -> false
        "StartPage" -> false
        "SignUp" -> false
        "Login" -> false
        else -> true
    }
    Scaffold(
        topBar = {
            if (showTopBar) {
                var current = ""
                current = when (navBackStackEntry?.destination?.route) {
                    "shop"-> "Shop"
                    "myLibrary"-> "MyLibrary"
                    "courses"-> "Courses"
                    "profile"-> "Profile"
                    "home"-> "Notist"
                    else->""
                }
                upNavigation(section = current)
            }
        },
        bottomBar = {
            if (showBotBar) {
                bottomNavigation(navController = navController)
            }
        }
//    ) { padding ->
//        20.dp
//        NavHost(navController = navController, startDestination = Routes.StartPage.route) {
//
//            composable(Routes.StartPage.route) { StartPage(navController = navController) }
//            composable(Routes.Login.route) { Login(navController = navController) }
//            composable(Routes.SignUp.route) { SignUp(navController = navController) }
//            composable(NavRoutes.Home.route) { Home() }
//            composable(Routes.MainScreen.route) { LoginPage() }
//            composable(NavRoutes.Home.route) { Home() }
//            composable(NavRoutes.Courses.route) { MyCourseApp() }
//            composable(NavRoutes.Profile.route) { Profile(navController) }
//            composable(NavRoutes.MyLibrary.route) { MyLibrary(navController) }
//            composable(NavRoutes.Shop.route) { Shop() }
//            //composable(Routes.ProfileSettings.route){ profilesettings(isDialogOpen = )}
//        }
        ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding))
        {
            NavHost(navController = navController, startDestination = Routes.StartPage.route) {
                composable(NavRoutes.Home.route) { Home() }
                composable(Routes.MainScreen.route) { LoginPage(viewModel) }
                composable(NavRoutes.Home.route) { Home() }
                composable(NavRoutes.Courses.route) { MyCourseApp(navController, viewModel) }
                composable(NavRoutes.Profile.route) { Profile(navController) }
                composable(NavRoutes.MyLibrary.route) { MyLibrary(navController) }
                composable(NavRoutes.Shop.route) { Shop() }
                composable(Routes.StartPage.route) { StartPage(navController = navController) }
                composable(Routes.Login.route) { Login(navController = navController) }
                composable(Routes.SignUp.route) { SignUp(navController = navController) }
                composable(Routes.Teacher.route, arguments = listOf(navArgument("class_name") {
                    type = NavType.StringType
                })) {
                    val class_name = it.arguments?.getString("class_name").orEmpty()
                    Teacher(
                        modifier = Modifier,
                        class_name = class_name,
                        navController = navController
                    )
                }
            }
        }
    }

    }

