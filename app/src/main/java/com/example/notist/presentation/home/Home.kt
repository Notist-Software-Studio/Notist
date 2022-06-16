package com.example.notist.presentation.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
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
                .size(350.dp, 165.dp)
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
                .size(350.dp, 110.dp)
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
                .size(240.dp)
                .clip(shape)
                .background(Color(0xFFC0C8D7))
        )
    }
}

@Composable
fun Home(Hunger: Int, navController: NavHostController,ticks: MutableState<Int>) {
    @DrawableRes var chat : Int = 0
    @DrawableRes var panda : Int = 0
    when (Hunger) {
        0 -> chat = R.drawable.hungrychat
        1 -> chat =  R.drawable.lackfoodchat
        2 -> chat =  R.drawable.workingchat
        3 -> chat =  R.drawable.bamboochat
        4 -> chat =  R.drawable.studychat
        5 -> chat =  R.drawable.examchat
    };
    if(ticks.value%2 ==0){
        when (Hunger) {
            0 -> panda = R.drawable.sadpanda
            1 -> panda =  R.drawable.panda
            2 -> panda =  R.drawable.panda
            3 -> panda =  R.drawable.panda
            4 -> panda =  R.drawable.panda
            5 -> panda =  R.drawable.happypanda
        };
    }
    else panda =  R.drawable.closeeyepanda
    Scaffold(content = {
        Box(modifier = Modifier.fillMaxSize())
        {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                //Spacer(modifier = Modifier.height(0.dp))
                hpbox(shape = RoundedCornerShape(20.dp))
                Spacer(modifier = Modifier.height(10.dp))
                Circle(shape = CircleShape)
                Spacer(modifier = Modifier.height(10.dp))
                objectives(shape = RoundedCornerShape(30.dp), navController)
                Spacer(modifier = Modifier.height(10.dp))
                progress(shape = RoundedCornerShape(30.dp), navController)
            }
        }
        Box(modifier = Modifier.fillMaxSize())
        {
            Column(
                modifier = Modifier.padding(100.dp, 30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(10.dp))
                Image(
                    painter = painterResource(panda),
                    contentDescription = "",
                    modifier = Modifier
                        .size(200.dp, 300.dp)
                )
            }
            Spacer(modifier = Modifier.width(50.dp))
            Row(){
                Spacer(modifier = Modifier.width(200.dp))
                Image(
                    painter = painterResource(id = chat),
                    contentDescription = "",
                    modifier = Modifier
                        .size(180.dp, 170.dp)
                )
            }
            hpbar(brapa = Hunger)
        }
        Box(modifier = Modifier.fillMaxSize())
        {
            Column(
                modifier = Modifier.padding(50.dp, 0.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Spacer(modifier = Modifier.height(315.dp))
                Text(
                    text = "Objectives",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(7.dp))
                Button(
                    onClick = { navController.navigate(NavRoutes.Profile.route) },
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
                    onClick = {navController.navigate(NavRoutes.Courses.route)},
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
                        modifier = Modifier.size(190.dp, 15.dp)
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
                Spacer(modifier = Modifier.height(30.dp))
                Text(
                    text = "My Progress",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(5.dp))
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.LightGray
                    ),
                    modifier = Modifier
                        .width(300.dp)
                        .height(60.dp)
                ) {
                    Column() {
                        Text(
                            text = "Ch1 notes",
                            color = Color.Black,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            //modifier = Modifier.size(190.dp, 30.dp)
                        )
                        Row() {
                            Image(
                                painter = painterResource(id = R.drawable.gibby),
                                contentDescription = "",
                                modifier = Modifier
                                    .size(15.dp, 15.dp)
                                    .padding(0.dp, 0.dp, 0.dp, 0.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp).height(5.dp))
                            Text(
                                text = "Gibby",
                                color = Color.Black,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.size(100.dp, 25.dp)
                            )
                        }
                    }
                    ProgressBar()
                }
            }
        }
    })
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview11() {
    val vm by remember { mutableStateOf(UserViewModel()) }
    val navController = rememberNavController()
    var ticks = rememberSaveable{ mutableStateOf(0) }
    Home(Hunger = vm.hunger.value, navController =navController,ticks)
}