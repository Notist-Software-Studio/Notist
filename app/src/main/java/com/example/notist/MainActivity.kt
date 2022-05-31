package com.example.notist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notist.ui.theme.NotistTheme
import com.example.notist.bar.bottomNavigation
import com.example.notist.bar.upNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotistTheme {
                NotistApp()
            }
        }
    }
}

@Composable
fun NotistApp() {
    NotistTheme {
        Scaffold(
            topBar = { upNavigation() },
            bottomBar = { bottomNavigation() }
        ) { padding -> 20.dp }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotistApp()
}