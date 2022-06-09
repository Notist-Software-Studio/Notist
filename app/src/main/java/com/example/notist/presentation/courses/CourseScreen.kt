package com.example.notist.presentation.courses

import android.net.Uri
import android.provider.MediaStore.getDocumentUri
import android.widget.Toast
import androidx.annotation.StringRes
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.notist.MainViewModel
import com.example.notist.R
import com.example.notist.data.dto.Course
import com.example.notist.navigation.Routes
import com.example.notist.ui.theme.NotistTheme
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.pspdfkit.configuration.activity.PdfActivityConfiguration
import com.pspdfkit.internal.views.document.DocumentView
import com.pspdfkit.ui.PdfActivity

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
    class_name: String,
    major: String,
    modifier: Modifier = Modifier, navController: NavHostController
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .background(colorResource(id = R.color.light_blue), shape = RoundedCornerShape(10.dp))
            .width(370.dp)
            .clickable { navController.navigate("Teacher/$class_name") },
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
            .paddingFromBaseline(top = 300.dp, bottom = 300.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(data) { item ->
            CourseBar(
                class_name = item.class_name,
                major = item.major,
                modifier = Modifier,
                navController
            )
        }
    }
}


@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavHostController,viewModel: MainViewModel) {
    val courses by viewModel.courses.observeAsState(initial = emptyList())
    var data = courses
//    val course = Course(1,"Data Structures", "CS")
//    val data = remember { mutableStateListOf(course) }
    val context = LocalContext.current
    val openAdd = remember { mutableStateOf(false) }
    var class_name by remember { mutableStateOf("") }
    var major by remember { mutableStateOf("") }
    Column(
        modifier
            .padding(vertical = 16.dp)
    ) {
        SearchBar(Modifier.padding(horizontal = 16.dp))
        Box(modifier = Modifier.fillMaxSize()) {
            AlignCourseBar(modifier = Modifier, data = data, navController)
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
                                value = class_name,
                                onValueChange = { class_name = it },
                                label = { Text("Input the class name") },
                                placeholder = {Text("Same as school system, duplicate is invalid")}
                                )

                            TextField(
                                modifier = Modifier
                                    .width(500.dp)
                                    .padding(25.dp),
                                value = major,
                                onValueChange = { major = it },
                                label = { Text("Input the major name") })
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                Button(
                                    onClick = {
                                        courses.apply {
                                            class_name = class_name
                                            major = major
                                        }
                                        Toast.makeText(
                                            context,
                                            "$class_name $major",
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
fun MyCourseApp(navController: NavHostController,viewModel: MainViewModel) {
    NotistTheme {
        Scaffold(
        ) { padding ->
            HomeScreen(Modifier.padding(padding), navController,viewModel)
        }
    }
}







