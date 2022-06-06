package com.example.notist.presentation.courses

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.background
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
import com.example.notist.R
import com.example.notist.ui.theme.NotistTheme
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

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

data class StringPair(
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
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(colorResource(id = R.color.light_blue), shape = RoundedCornerShape(10.dp))
            .width(370.dp),
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
    data: List<StringPair>
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .paddingFromBaseline(top = 16.dp, bottom = 16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(data) { item ->
            CourseBar(class_name = item.class_name, major = item.major)
        }
    }
}

@Composable
fun HomeSection(

    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
//        Text(
//            modifier = Modifier
//                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
//                .padding(horizontal = 16.dp)
//        )
        content()
    }
}


@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val course = StringPair("Data Structures", "CS")
    val data = remember { mutableStateListOf(course) }
    Column(
        modifier
            .padding(vertical = 16.dp)
    ) {
        SearchBar(Modifier.padding(horizontal = 16.dp))
        Box (modifier = Modifier.fillMaxSize()){
            AlignCourseBar(data = data)
            Button(modifier = Modifier
                .padding(24.dp)
                .align(Alignment.BottomCenter),
                onClick = { data.add(StringPair("Data Structures", "CS"))}) {
                Text(text = "Add More")
            }
        }


    }
}

@Composable
fun MyCourseApp() {
    NotistTheme {
        Scaffold(
        ) { padding ->
            HomeScreen(Modifier.padding(padding))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchBarPreview() {
    NotistTheme {
        SearchBar()
    }
}

@Preview(showBackground = true)
@Composable
fun CourseBarPreview() {
    NotistTheme {
        CourseBar(class_name = "Introduction to Programming 1", major = "CS")
    }
}


@Preview(showBackground = true)
@Composable
fun AlignCourseBarPreview() {
    NotistTheme {
        AlignCourseBar(data = listOf())
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MyCoursePreview() {
//    NotistTheme {
//        MyCourseApp()
//    }
//}