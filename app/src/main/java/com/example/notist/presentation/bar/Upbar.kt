package com.example.notist.presentation.bar

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun upNavigation(modifier: Modifier = Modifier,section:String) {
    TopAppBar(
        title = {Text(text = section)},
        backgroundColor = Color(0xFF5C6BC0),
        contentColor = Color.White
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
   upNavigation(section = "Notist")
}

