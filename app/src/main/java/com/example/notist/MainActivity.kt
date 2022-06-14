package com.example.notist

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.notist.presentation.courses.AlignCourseBar
import com.example.notist.ui.theme.NotistTheme
import com.example.notist.presentation.login.LoginPage
import com.example.notist.presentation.screens.Home
import com.example.notist.presentation.profile.Profile
import com.example.notist.presentation.screens.Shop
import com.pspdfkit.internal.vm
import kotlinx.coroutines.delay
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.w3c.dom.Text
import kotlin.time.Duration.Companion.seconds
import kotlin.time.ExperimentalTime

//import com.example.notist.presentation.courses.MyCourseApp
//import com.example.notist.presentation.screens.Home
//import com.example.notist.presentation.screens.Courses
//import com.example.notist.presentation.screens.Profile
//import com.example.notist.presentation.screens.MyLibrary
//import com.example.notist.presentation.screens.Shop


class MainActivity : ComponentActivity() {
    private val viewModel : MainViewModel by viewModel<MainViewModel> ()
    private val pdfviewModel : PDFMainViewModel by viewModels<PDFMainViewModel>()
    private val vm = UserViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //call the couroutines
            var ticks = rememberSaveable{ mutableStateOf(0) }
            viewModel.fetchCourses()
            viewModel.fetchCoursesSaved()
//            AlignCourseBar(data = foo, navController = rememberNavController())
            NotistApp(viewModel, pdfviewModel,vm.hunger,ticks)
            timer(ticks,vm.hunger)
        }

    }
}



@Composable
fun NotistApp(viewModel: MainViewModel, pdfViewModel: PDFMainViewModel,hunger : MutableState<Int>,ticks: MutableState<Int>) {
    NotistTheme {
        LoginPage(viewModel, pdfViewModel,hunger,ticks)
    }
}

@OptIn(ExperimentalTime::class)
@Composable
fun timer(
    ticks: MutableState<Int>,
    hunger : MutableState<Int>
){
    //var ticks by rememberSaveable{ mutableStateOf(0) }
    LaunchedEffect(Unit) {
        while(true) {
            delay(1.seconds)
            ticks.value++
            if(ticks.value%10 == 0){
                ticks.value = 0
                if(hunger.value > 0) hunger.value --
            }
        }
    }
    Text(text = "${ticks.value}")
}



