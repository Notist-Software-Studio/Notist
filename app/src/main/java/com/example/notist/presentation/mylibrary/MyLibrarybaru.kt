package com.example.notist.presentation.mylibrary

import android.widget.Toast
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
//import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
//import com.example.notist.MainViewModel
import com.example.notist.R
//import com.example.notist.data.dto.Course
import com.example.notist.ui.theme.NotistTheme
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

//val database = Firebase.database
//val myRef = database.getReference("message")

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

