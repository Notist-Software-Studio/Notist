package com.example.notist.presentation.courses

import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContentResolverCompat.query
import androidx.navigation.NavHostController
import com.example.notist.MainViewModel
import com.example.notist.R
import com.example.notist.data.dto.Course
import com.example.notist.data.dto.Pdf
import com.example.notist.ui.theme.NotistTheme
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.lang.Character.getType





@Composable
fun LibraryBar(
    courseId: String,
    class_name: String,
    major: String,
    modifier: Modifier = Modifier, navController: NavHostController
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .background(Color(0xFFC0C8D7), shape = RoundedCornerShape(10.dp))
            .width(370.dp)
            .height(60.dp)
            .clickable { navController.navigate("UploadScreen/$courseId") },
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = class_name,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(
                start = 16.dp
            )
        )
        Text(
            text = major,
            color = Color.White,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(
                start = 16.dp
            )
        )
    }
}

@Composable
fun AlignLibraryBar(
    modifier: Modifier = Modifier,
    data: List<Course>,
    navController: NavHostController
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .paddingFromBaseline(bottom = 300.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(data) { item ->
            LibraryBar(
                courseId = item.courseId,
                class_name = item.class_name,
                major = item.major,
                modifier = Modifier,
                navController
            )
        }
    }
}


@Composable
fun HomeLibraryScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: MainViewModel
) {
    val courses by viewModel.courses_saved.observeAsState(initial = emptyList())
    val context = LocalContext.current
    val openAdd = remember { mutableStateOf(false) }
    var inClassName by remember { mutableStateOf("") }
    var inMajor by remember { mutableStateOf("") }
    Column(
        modifier
            .padding(vertical = 16.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AlignLibraryBar(modifier = Modifier, data = courses, navController)
            Button(modifier = Modifier
                .padding(20.dp)
                .height(50.dp)
                .align(Alignment.BottomCenter),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF5C6BC0)
                ),
                shape = RoundedCornerShape(20.dp),
                onClick = {
//                    data.add(CourseStringPair("Data Structures", "CS"))
                    openAdd.value = !openAdd.value
                }) {
                Text(text = "ADD NEW COURSE",color = Color.White)
            }

            if (openAdd.value) {
                Dialog(
                    onDismissRequest = {
                        openAdd.value = false
                    }) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .padding(20.dp),
                        shape = RoundedCornerShape(30.dp),
                        //color = Color(0xFFC0C8D7)
                        color = Color.White
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            TextField(
                                modifier = Modifier
                                    .width(500.dp)
                                    .padding(25.dp),
                                value = inClassName,
                                onValueChange = { inClassName = it },
                                label = { Text("Input the class name") },
                                placeholder = { Text("Same as school system, duplicate is invalid") }
                            )

                            TextField(
                                modifier = Modifier
                                    .width(500.dp)
                                    .padding(25.dp),
                                value = inMajor,
                                onValueChange = { inMajor = it },
                                label = { Text("Input the major name") })
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {

                                Button(
                                    onClick = {
                                        openAdd.value = false
                                    },
                                    shape = RoundedCornerShape(30.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color(0xFF5C6BC0)
                                    )) {
                                    Text("CANCEL",color = Color.White)
                                }
                                Button(
                                    onClick = {

                                        var add_course = Course().apply {
                                            class_name = inClassName
                                            major = inMajor
                                        }
                                        viewModel.save(add_course)
                                        inClassName = ""
                                        inMajor = ""
//                                        Toast.makeText(
//                                            context,
//                                            "$inClassName $inMajor",
//                                            Toast.LENGTH_LONG
//                                        ).show()
                                        openAdd.value = false
                                    },
                                    shape = RoundedCornerShape(30.dp),
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = Color(0xFF5C6BC0)
                                    )) {
                                    Text("ADD COURSE",color = Color.White)
                                }
                            }

                        }
                    }
                }
            }



        }
    }
}


@Composable
fun MyLibrary(navController: NavHostController, viewModel: MainViewModel) {
    NotistTheme {
        Scaffold(
        ) { padding ->
            HomeLibraryScreen(Modifier.padding(padding), navController, viewModel)
        }
    }
}







