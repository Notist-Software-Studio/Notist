package com.example.notist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.notist.presentation.courses.AlignCourseBar
import com.example.notist.ui.theme.NotistTheme
import com.example.notist.presentation.login.LoginPage
import com.example.notist.presentation.screens.Home
import com.example.notist.presentation.mylibrary.MyLibrary
import com.example.notist.presentation.profile.Profile
import com.example.notist.presentation.screens.Shop
import org.koin.androidx.viewmodel.ext.android.viewModel
//import com.example.notist.presentation.courses.MyCourseApp
//import com.example.notist.presentation.screens.Home
//import com.example.notist.presentation.screens.Courses
//import com.example.notist.presentation.screens.Profile
//import com.example.notist.presentation.screens.MyLibrary
//import com.example.notist.presentation.screens.Shop


class MainActivity : ComponentActivity() {
    private val viewModel : MainViewModel by viewModel<MainViewModel> ()
    private val pdfviewModel : PDFMainViewModel by viewModels<PDFMainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            //call the couroutines
            viewModel.fetchCourses()
//            AlignCourseBar(data = foo, navController = rememberNavController())
            NotistApp(viewModel, pdfviewModel)
        }

    }
}



@Composable
fun NotistApp(viewModel: MainViewModel, pdfViewModel: PDFMainViewModel) {
    NotistTheme {
        LoginPage(viewModel, pdfViewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

}