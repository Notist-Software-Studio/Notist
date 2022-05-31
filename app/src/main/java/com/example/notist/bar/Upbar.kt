package com.example.notist.bar

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun upNavigation(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {Text("Notist")},
        backgroundColor = Color(0xFF5C6BC0),
        contentColor = Color.White
    )
}

