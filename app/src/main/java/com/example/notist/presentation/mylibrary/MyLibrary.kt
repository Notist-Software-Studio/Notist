package com.example.notist.presentation.screens

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHost
import com.example.notist.PDFMainViewModel
import com.example.notist.ui.theme.PdfList
import com.pspdfkit.jetpack.compose.ExperimentalPSPDFKitApi
import com.example.notist.data.dto.State
import com.example.notist.navigation.NavRoutes
import com.example.notist.presentation.mylibrary.pdfList

@OptIn(ExperimentalPSPDFKitApi::class, ExperimentalAnimationApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun MyLibrary(navController: NavController) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(24.dp)
    ) {
        Button(onClick = { navController.navigate(NavRoutes.PdfList.route) }) {
            Text(
                text = "course name",
                textAlign = TextAlign.Center
            )
        }
    }



}