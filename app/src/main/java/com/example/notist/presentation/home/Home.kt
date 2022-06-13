package com.example.notist.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notist.R
import com.example.notist.UserViewModel
import com.example.notist.navigation.NavRoutes


@Composable
fun ProgressBar() {
    Column() {
        Text(
            text = "10 of 31",
            color = Color(0xFF5C6BC0),
            fontSize = 10.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.size(190.dp,20.dp)
        )
        LinearProgressIndicator(
            progress = 0.35f,//percentage 70%
            modifier = Modifier
                .height(8.dp)
                .clip(RoundedCornerShape(10.dp)),
            color = Color(0xFF5C6BC0),
            backgroundColor = Color.White,
        )
    }

}
@Composable
fun hpbox(shape: Shape){
    Column(modifier = Modifier
        .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .size(170.dp, 30.dp)
                .clip(shape)
                .background(Color(0xFFC0C8D7))
        )
    }
}
@Composable
fun objectives(shape: Shape,navController: NavHostController){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .size(350.dp, 160.dp)
                .clip(shape)
                .background(Color(0xFFC0C8D7))
        ) {

        }
    }
}
@Composable
fun progress(shape: Shape,navController: NavHostController){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.Center)
    ) {
        Box(
            modifier = Modifier
                .size(350.dp, 120.dp)
                .clip(shape)
                .background(Color(0xFFC0C8D7))
        ) {

        }
    }
}


@Composable
fun hpbar(brapa : Int)
{
    Box(modifier = Modifier.fillMaxSize())
    {
        Column(
            modifier = Modifier.padding(22.dp,20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            LazyRow{
                items(brapa) { index ->
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        painter = painterResource(id = R.drawable.pentunganayam),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp, 30.dp)
                    )
                }
            }
        }
    }
}
@Composable
fun Circle(shape: Shape){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.Center)) {
        Box(
            modifier = Modifier
                .size(250.dp)
                .clip(shape)
                .background(Color(0xFFC0C8D7))
        )
    }
}

@Composable
fun Home(Hunger: Int, navController: NavHostController) {
    Box(modifier = Modifier.fillMaxSize())
    {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            //Spacer(modifier = Modifier.height(0.dp))
            hpbox(shape = RoundedCornerShape(20.dp))
            Spacer(modifier = Modifier.height(5.dp))
            Circle(shape = CircleShape)
            Spacer(modifier = Modifier.height(5.dp))
            objectives(shape = RoundedCornerShape(30.dp),navController)
            Spacer(modifier = Modifier.height(5.dp))
            progress(shape = RoundedCornerShape(30.dp),navController)
        }
    }
    Box(modifier = Modifier.fillMaxSize())
    {
        Column(
            modifier = Modifier.padding(100.dp,30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            Image(
                painter = painterResource(id = R.drawable.panda),
                contentDescription = "",
                modifier = Modifier
                    .size(200.dp, 300.dp)
            )
        }
        hpbar(brapa = Hunger)
    }
    Box(modifier = Modifier.fillMaxSize())
    {
        Column(
            modifier = Modifier.padding(50.dp,0.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ){
            Spacer(modifier = Modifier.height(315.dp))
            Text(
                text = "Daily Objectives",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(5.dp))
            Button(
                onClick = { navController.navigate(NavRoutes.Profile.route)},
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Recommend apps to a friend ",
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.coin),
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp, 25.dp)
                        .padding(0.dp, 0.dp, 0.dp, 0.dp)
                )
                Text(
                    text = "+20",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(
                onClick = {},
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.White
                ),
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Share a note",
                    color = Color.Black,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.size(190.dp,15.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Image(
                    painter = painterResource(id = R.drawable.coin),
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp, 25.dp)
                        .padding(0.dp, 0.dp, 0.dp, 0.dp)
                )
                Text(
                    text = "+20",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "My Progress",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(2.dp))
            Button(
                onClick = {},
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.LightGray
                ),
                modifier = Modifier
                    .width(300.dp)
                    .height(65.dp)
            ) {
                Column() {
                    Text(
                        text = "Ch1 notes",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.size(190.dp,30.dp)
                    )
                    Row() {
                        Image(
                            painter = painterResource(id = R.drawable.gibby),
                            contentDescription = "",
                            modifier = Modifier
                                .size(20.dp, 20.dp)
                                .padding(0.dp, 0.dp, 0.dp, 0.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp).height(5.dp))
                        Text(
                            text = "Gibby",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.size(100.dp,25.dp)
                        )
                    }
                }
                ProgressBar()
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview11() {
    val vm by remember { mutableStateOf(UserViewModel()) }
    val navController = rememberNavController()
    Home(Hunger = vm.hunger.value, navController =navController )
}