package com.example.notist.presentation.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import com.example.notist.UserViewModel
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.notist.MainViewModel
import com.example.notist.PDFMainViewModel
import com.example.notist.R
import com.example.notist.navigation.NavRoutes
import com.example.notist.navigation.Routes
import com.example.notist.presentation.bar.bottomNavigation
import com.example.notist.presentation.bar.upNavigation
import com.example.notist.presentation.courses.MyCourseApp
import com.example.notist.presentation.courses.MyLibrary
import com.example.notist.presentation.screens.*
import com.example.notist.presentation.courses.UploadScreen
import com.example.notist.presentation.screens.Home
import com.example.notist.presentation.mylibrary.*
import com.example.notist.presentation.profile.Profile
import com.example.notist.presentation.screens.Shop
import com.pspdfkit.internal.vm
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime


@Composable
fun LoginPage(viewModel: MainViewModel, pdfViewModel: PDFMainViewModel,hunger : MutableState<Int>,ticks: MutableState<Int>) {
    var money = rememberSaveable { mutableStateOf(0) }
    //val vm by remember { mutableStateOf(UserViewModel()) }
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
        ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding))
        {
            NavHost(navController = navController, startDestination = Routes.StartPage.route) {
                composable(NavRoutes.Home.route) { Home(Hunger = hunger.value,navController = navController,ticks) }
                composable(Routes.MainScreen.route) { LoginPage(viewModel, pdfViewModel,hunger,ticks) }
                composable(NavRoutes.Courses.route) { MyCourseApp(navController, viewModel) }
                composable(NavRoutes.Profile.route) { Profile(navController,money) }
                composable(NavRoutes.MyLibrary.route) { MyLibrary(navController,viewModel) }
                composable(NavRoutes.Shop.route) { Shop(money,hunger) }
                composable(Routes.StartPage.route) { StartPage(navController = navController) }
                composable(Routes.Login.route) { Login(navController = navController) }
                composable(Routes.SignUp.route) { SignUp(navController = navController) }
                composable(Routes.UploadScreen.route, arguments = listOf(
                    navArgument(name = "courseId") {
                    type = NavType.StringType
                })) {
                    val courseId = it.arguments?.getString("courseId").orEmpty()
                    val class_name = it.arguments?.getString("class_name").orEmpty()
                    val major = it.arguments?.getString("major").orEmpty()

                    UploadScreen(
                        modifier = Modifier,
                        incourseId = courseId,
                        inclass_name = class_name,
                        inmajor = major,
                        navController = navController,
                        viewModel = MainViewModel(),money = money)}

            }
        }
    }

}


