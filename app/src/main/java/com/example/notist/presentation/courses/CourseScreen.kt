package com.example.notist.presentation.courses

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import android.provider.CalendarContract.Attendees.query
import android.provider.CalendarContract.EventDays.query
import android.provider.MediaStore
import android.provider.MediaStore.Images.Media.query
import android.provider.MediaStore.Video.query
import android.provider.OpenableColumns
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
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

val database = Firebase.database
val myRef = database.getReference("message")

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    // Implement composable here
    var className by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column() {
        TextField(
            value = className,
            onValueChange = { className = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface
            ),
            placeholder = {
                Text(stringResource(R.string.placeholder_search_for_courses))
            },
            modifier = modifier
                .fillMaxWidth()
                .heightIn(min = 56.dp)
                .clip(shape = RoundedCornerShape(10.dp))
        )
//        Button(
//            onClick = {
//                Toast.makeText(context, "$className", Toast.LENGTH_LONG).show()
//                myRef.setValue("Hello World")
//            },
//            content = { Text(text = "Save") }
//        )
    }

}

data class CourseStringPair(
    val class_name: String,
    val major: String
)


@Composable
fun CourseBar(
    courseId: String,
    class_name: String,
    major: String,
    modifier: Modifier = Modifier, navController: NavHostController
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .background(colorResource(id = R.color.light_blue), shape = RoundedCornerShape(10.dp))
            .width(370.dp)
            .clickable { navController.navigate("UploadScreen/$courseId") },
        horizontalAlignment = Alignment.Start
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
fun AlignCourseBar(
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
            CourseBar(
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
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    viewModel: MainViewModel
) {
    val courses by viewModel.courses.observeAsState(initial = emptyList())
    val context = LocalContext.current
    val openAdd = remember { mutableStateOf(false) }
    var inClassName by remember { mutableStateOf("") }
    var inMajor by remember { mutableStateOf("") }
    Column(
        modifier
            .padding(vertical = 16.dp)
    ) {
        SearchBar(Modifier.padding(horizontal = 16.dp))
        Box(modifier = Modifier.fillMaxSize()) {
            AlignCourseBar(modifier = Modifier, data = courses, navController)
            Button(modifier = Modifier
                .padding(24.dp)
                .align(Alignment.BottomCenter),
                onClick = {
//                    data.add(CourseStringPair("Data Structures", "CS"))
                    openAdd.value = !openAdd.value
                }) {
                Text(text = "Add More")
            }

            if (openAdd.value) {
                Dialog(
                    onDismissRequest = {
                        openAdd.value = false
                    }) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                            .padding(20.dp),
                        shape = RoundedCornerShape(5.dp),
                        color = Color(0xFFC0C8D7)
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

                                        var add_course = Course().apply {
                                            class_name = inClassName
                                            major = inMajor
                                        }
                                        viewModel.save(add_course)
                                        Toast.makeText(
                                            context,
                                            "$inClassName $inMajor",
                                            Toast.LENGTH_LONG
                                        ).show()
                                        openAdd.value = false
                                    }) {
                                    Text("Add Course")
                                }
                                Button(
                                    onClick = {
                                        openAdd.value = false
                                    }) {
                                    Text("Cancel")
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
fun MyCourseApp(navController: NavHostController, viewModel: MainViewModel) {
    NotistTheme {
        Scaffold(
        ) { padding ->
            HomeScreen(Modifier.padding(padding), navController, viewModel)
        }
    }
}







