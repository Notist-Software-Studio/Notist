package com.example.notist.presentation.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.*
import androidx.navigation.NavController
import com.example.notist.MainViewModel
import com.example.notist.PDFMainViewModel
import com.example.notist.ui.theme.PdfList
import com.pspdfkit.jetpack.compose.ExperimentalPSPDFKitApi
import com.example.notist.State

@OptIn(ExperimentalPSPDFKitApi::class, ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun MyLibrary(pdfViewModel: PDFMainViewModel ,navController: NavController) {

    val state by pdfViewModel.state.collectAsState(State())
    PdfList(state, pdfViewModel::loadPdfs, navController = navController)

}