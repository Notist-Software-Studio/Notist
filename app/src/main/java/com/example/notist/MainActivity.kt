package com.example.notist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.notist.navigation.NavRoutes
import com.example.notist.ui.theme.NotistTheme
import com.example.notist.presentation.bar.bottomNavigation
import com.example.notist.presentation.bar.upNavigation
import com.example.notist.presentation.screens.*
import com.pspdfkit.jetpack.compose.ExperimentalPSPDFKitApi

@ExperimentalPSPDFKitApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotistApp(viewModel)
        }
    }

    override fun onBackPressed() {
        if (viewModel.state.value.selectedDocumentUri != null) {
            viewModel.closeDocument()
        } else {
            super.onBackPressed()
        }
    }

}
@ExperimentalFoundationApi
@Composable
fun NotistApp(viewModel: MainViewModel) {

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
                composable(NavRoutes.Courses.route) { Courses() }
                composable(NavRoutes.Profile.route) { Profile() }
                composable(NavRoutes.MyLibrary.route) { MyLibrary(viewModel, navController = navController) }
                composable(NavRoutes.Shop.route) { Shop() }
                composable(NavRoutes.Test.route) { test(pdfResId = R.raw.sample) }
                composable(NavRoutes.Test1.route) { test1() }
                composable(NavRoutes.Test2.route) { test2() }
            }
        }
    }
}