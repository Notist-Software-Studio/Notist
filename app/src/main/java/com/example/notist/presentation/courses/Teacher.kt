package com.example.notist.presentation.courses

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.notist.R

data class TeacherStringPair(
    val teacher_name: String,
    val class_number: String
)
@Composable
fun Teacher( modifier: Modifier,class_name:String,navController: NavHostController){
    val teacher = TeacherStringPair("Chen,Yi-Shin","CS2020203")
    val data = remember { mutableStateListOf(teacher) }

    Column(
        modifier
            .padding(vertical = 16.dp)
    ) {
        SearchBar(Modifier.padding(horizontal = 16.dp))
        Box (modifier = Modifier.fillMaxSize()){
            AlignTeacherBar(modifier = Modifier,data = data, navController)
            Button(modifier = Modifier
                .padding(24.dp)
                .align(Alignment.BottomCenter),
                onClick = { data.add(TeacherStringPair("Shen,Chi-ya", "CS2020204"))}) {
                Text(text = "Add More")
            }
        }
    }
}

@Composable
fun AlignTeacherBar(
    modifier: Modifier = Modifier,
    data: List<TeacherStringPair>,
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
            TeacherBar(teacher_name = item.teacher_name, class_number = item.class_number,modifier=Modifier,navController)
        }
    }
}
@Composable
fun TeacherBar(
    teacher_name: String,
    class_number: String,
    modifier: Modifier = Modifier
    ,navController: NavHostController
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .background(colorResource(id = R.color.light_blue), shape = RoundedCornerShape(10.dp))
            .width(370.dp).clickable {navController.navigate("Files/$class_number") },
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = teacher_name,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(
                start = 16.dp
            )
        )
        Text(
            text = class_number,
            color = Color.White,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(
                start = 16.dp
            )
        )
    }
}