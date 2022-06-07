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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.notist.R
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
        Button(
            onClick = {
                Toast.makeText(context, "$className", Toast.LENGTH_LONG).show()
                myRef.setValue("Hello World")
            },
            content = { Text(text = "Save") }
        )
    }

}

data class CourseStringPair(
    val class_name: String,
    val major: String
)

//private val alignYourBodyDataAll = mutableListOf(
//    "Introduction to Programming 1" to "CS",
//    "Introduction to Programming 2" to "CS",
//    "Linear Algebra" to "EECS",
//    "Electronics" to "EE",
//    ).map { StringPair(it.first, it.second) }


@Composable
fun CourseBar(
    class_name: String,
    major: String,
    modifier: Modifier = Modifier
,navController: NavHostController
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .background(colorResource(id = R.color.light_blue), shape = RoundedCornerShape(10.dp))
            .width(370.dp).clickable {navController.navigate("Teacher/$class_name") },
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
    data: List<CourseStringPair>,
    navController: NavHostController
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .paddingFromBaseline(top = 16.dp, bottom = 16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(data) { item ->
            CourseBar(class_name = item.class_name, major = item.major,modifier=Modifier,navController)
        }
    }
}



@Composable
fun HomeScreen(modifier: Modifier = Modifier,navController: NavHostController) {
    val course = CourseStringPair("Data Structures", "CS")
    val data = remember { mutableStateListOf(course) }
    val context = LocalContext.current
    Column(
        modifier
            .padding(vertical = 16.dp)
    ) {
        SearchBar(Modifier.padding(horizontal = 16.dp))
        Box (modifier = Modifier.fillMaxSize()){
            AlignCourseBar(modifier = Modifier,data = data, navController)
            Button(modifier = Modifier
                .padding(24.dp)
                .align(Alignment.BottomCenter),
                onClick = { data.add(CourseStringPair("Data Structures", "CS"))}) {
                Text(text = "Add More")
            }

        }
//        val uri = Uri.parse("file:///android_asset/my-document.pdf")
//        val config = PdfActivityConfiguration.Builder(context).build()
//        Surface {
//            val documentUri = remember { getDocumentUri() }
//            DocumentView(
//                documentUri = uri,
//                modifier = Modifier
//                    .height(100.dp)
//                    .padding(16.dp)
//            )
//        }
    }
}

@Composable
fun MyCourseApp(navController: NavHostController) {
    NotistTheme {
        Scaffold(
        ) { padding ->
            HomeScreen(Modifier.padding(padding),navController)
        }
    }
}







