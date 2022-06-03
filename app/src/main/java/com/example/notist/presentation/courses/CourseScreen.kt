package com.example.notist.presentation.courses

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.notist.R
import com.example.notist.presentation.bar.bottomNavigation
import com.example.notist.presentation.bar.upNavigation
import com.example.notist.ui.theme.NotistTheme

@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    // Implement composable here

    TextField(
        value = "",
        onValueChange = {},
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
}
private data class StringPair(
    @StringRes val class_name: Int,
    @StringRes val major: Int
)

private val alignYourBodyDataAll = listOf(
    R.string.introduction_to_programming_1 to R.string.cs,
    R.string.introduction_to_programming_2 to R.string.cs,
    R.string.linear_algebra to R.string.eecs,
    R.string.electronics to R.string.ee,
    ).map { StringPair(it.first, it.second) }






@Composable
fun CourseBar(
    @StringRes class_name: Int,
    @StringRes major: Int,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = modifier
            .background(colorResource(id = R.color.light_blue), shape = RoundedCornerShape(10.dp))
            .width(370.dp),
        horizontalAlignment = Alignment.Start
    ){
        Text(
            text = stringResource(class_name),
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(
                start = 16.dp
            )
        )
        Text(
            text = stringResource(major),
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
    modifier: Modifier = Modifier
){
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .paddingFromBaseline(top = 16.dp, bottom = 16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        items(alignYourBodyDataAll){
            item-> CourseBar(class_name = item.class_name, major = item.major)
        }
    }
}
@Composable
fun HomeSection(
    @StringRes title: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Column(modifier) {
        Text(
            text = stringResource(title),
            modifier = Modifier
                .paddingFromBaseline(top = 40.dp, bottom = 8.dp)
                .padding(horizontal = 16.dp)
        )
        content()
    }
}



@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .padding(vertical = 16.dp)
    ) {
       SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.all) {
            AlignCourseBar()
        }
    }
}
@Composable
fun MyCourseApp(){

    val navController = rememberNavController()

    NotistTheme {
        Scaffold(
            topBar = { upNavigation() },
            bottomBar = { bottomNavigation(navController = navController) }
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
        CourseBar(class_name= R.string.introduction_to_programming_1,major=R.string.cs)
    }
}


@Preview(showBackground = true)
@Composable
fun AlignCourseBarPreview() {
    NotistTheme {
        AlignCourseBar()
    }
}

@Preview(showBackground = true)
@Composable
fun MyCoursePreview() {
    NotistTheme {
        MyCourseApp()
    }
}