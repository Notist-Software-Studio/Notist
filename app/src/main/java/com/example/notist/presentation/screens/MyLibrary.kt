package com.example.notist.presentation.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notist.MainViewModel
import com.example.notist.ui.theme.PdfList
import com.pspdfkit.jetpack.compose.ExperimentalPSPDFKitApi
import com.example.notist.State

@OptIn(ExperimentalPSPDFKitApi::class, ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun MyLibrary(viewModel: MainViewModel, navController: NavController) {

    val state by viewModel.state.collectAsState(State())
    PdfList(state, viewModel::loadPdfs, viewModel::closeDocument, navController = navController)

}


