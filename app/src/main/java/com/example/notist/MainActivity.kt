package com.example.notist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.notist.ui.theme.NotistTheme
import com.example.notist.presentation.login.LoginPage
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : ComponentActivity() {
    private val viewModel : MainViewModel by viewModel<MainViewModel> ()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            viewModel.fetchCourses()
            //call the couroutines
            val courses by viewModel.courses.observeAsState(initial = emptyList())
            NotistApp()
            var foo = courses
        }

    }
}



@Composable
fun NotistApp() {
    NotistTheme {
        LoginPage()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotistApp()
}