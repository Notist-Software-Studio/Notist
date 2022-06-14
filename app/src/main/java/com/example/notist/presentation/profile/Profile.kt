package com.example.notist.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.notist.R
import com.example.notist.navigation.Routes
import com.example.notist.presentation.bar.upNavigation

@Composable
fun Profile(navController: NavHostController,money : MutableState<Int>,) {
    Box(modifier = Modifier.fillMaxSize()) {
        ScaffoldWithTopBarProfile(navController,money)
    }
}

@Composable
fun referral(shape: Shape){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.Center)) {
        Box(
            modifier = Modifier
                .size(300.dp, 150.dp)
                .clip(shape)
                .background(color = Color(0xFFC0C8D7))
        )
    }
}
@Composable
fun myreferral(shape: Shape){
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.Center)) {
        Box(
            modifier = Modifier
                .size(240.dp, 45.dp)
                .clip(shape)
                .background(color = Color.White)
        )
    }
}


@Composable
fun ScaffoldWithTopBarProfile(navController: NavHostController,money : MutableState<Int>,) {
    Scaffold(
        topBar = {

        }, content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ) {
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = { },
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .width(165.dp)
                        .height(40.dp)
                        .padding(vertical = 0.dp, horizontal = 30.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFE4E6E7),
                    )
                ) {
                    Image(
                        painter = painterResource(R.drawable.coin),
                        contentDescription = "",
                        modifier = Modifier
                            .size(25.dp, 25.dp)
                    )
                    Text(
                        text = "${money.value}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.End,
                        modifier = Modifier.size(200.dp),
                        color = Color.Black
                    )
                }
//                Button(
//                    onClick = { },
//                    shape = RoundedCornerShape(10.dp),
//                    modifier = Modifier
//                        .width(160.dp)
//                        .height(35.dp)
//                        .padding(vertical = 0.dp, horizontal = 30.dp),
//                    colors = ButtonDefaults.buttonColors(
//                        backgroundColor = Color(0xFFE4E6E7),
//
//                    )
//
//                    //.padding(vertical = 12.dp, horizontal = 20.dp)
//                ) {
//                    Image(
//                        painter = painterResource(id = R.drawable.coin),
//                        contentDescription = "",
//                        modifier = Modifier
//                            .size(20.dp, 30.dp)
//                            .padding(0.dp, 0.dp, 0.dp, 0.dp)
//
//                    )
//                    Text(
//                        text = "100",
//                        textAlign = TextAlign.End,
//                        modifier = Modifier.size(35.dp, 60.dp),
//                        color = Color.Black
//                    )
//
//                    //Spacer(modifier = Modifier.height(20.dp))
//
//                }
            }
            Row(
                modifier = Modifier.height(95.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.Top
            ){
                Image(
                    painter = painterResource(id = R.drawable.avatar),
                    contentDescription = "",
                    modifier = Modifier
                        .size(90.dp, 100.dp)
                        .padding(40.dp, 0.dp, 0.dp, 0.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "Sky", fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Left,
                    modifier = Modifier
                        .size(75.dp, 20.dp)
                        .align(Alignment.CenterVertically),
                    color = Color.Black
                )
                val isDialogOpen = remember { mutableStateOf(false)}
                profilesettings(isDialogOpen)
                IconButton(onClick = { isDialogOpen.value = true },modifier = Modifier.size(30.dp, 210.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_action_pencil),
                        contentDescription = "Localized description",
                        modifier = Modifier
                            .size(40.dp, 25.dp)
                            .align(Alignment.CenterVertically),
                        tint = Color(0xB8B7B3B9)
                    )
                }
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(120.dp))
                referral(shape = RoundedCornerShape(30.dp))
                Spacer(modifier = Modifier.height(20.dp))
                referral(shape = RoundedCornerShape(30.dp))
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(150.dp))
                val referralcode  = remember { mutableStateOf(TextFieldValue()) }
                TextField(
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.White
                    ),
                    shape = RoundedCornerShape(10.dp),
                    label = { Text(text = "Your Friend's Referral Code", fontSize = 14.sp, textAlign = TextAlign.Center,modifier = Modifier.size(200.dp,50.dp), fontWeight = FontWeight.ExtraBold) },
                    value = referralcode.value,
                    onValueChange = { referralcode.value = it },
                    modifier = Modifier.size(240.dp,50.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                val isDialogEnter = remember { mutableStateOf(false)}
                profileenter(isDialogEnter,money)
                Button(
                    onClick = {isDialogEnter.value = true},
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .height(35.dp)
                        .padding(vertical = 0.dp, horizontal = 30.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFEBD594),

                    )

                    //.padding(vertical = 12.dp, horizontal = 20.dp)
                ) {
                    Text(
                        text = "ENTER",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.size(50.dp, 60.dp),
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Image(
                        painter = painterResource(id = R.drawable.coin),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp, 30.dp)
                            .padding(0.dp, 0.dp, 0.dp, 0.dp)

                    )
                    Text(
                        text = "+20",
                        textAlign = TextAlign.Start,
                        modifier = Modifier.size(35.dp, 60.dp),
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(60.dp))
                myreferral(shape = RoundedCornerShape(10.dp))

            }
            Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(330.dp))
                Row(
                    modifier = Modifier.height(35.dp),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top
                ){
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "YOUR CODE",fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.size(140.dp, 20.dp),
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    IconButton(onClick = {  }, modifier = Modifier.size(30.dp, 20.dp)) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_action_copy),
                            contentDescription = "Localized description",
                            modifier = Modifier
                                .size(50.dp, 20.dp),
                            tint = Color.Black
                        )
                    }
                }
                Spacer(modifier = Modifier.height(15.dp))
                val isDialogShare = remember { mutableStateOf(false)}
                profileshare(isDialogShare,money)
                Button(
                    onClick = {isDialogShare.value = true},
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .width(200.dp)
                        .height(35.dp)
                        .padding(vertical = 0.dp, horizontal = 30.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFEBD594),

                    )

                    //.padding(vertical = 12.dp, horizontal = 20.dp)
                ) {
                    Text(
                        text = "SHARE",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.size(50.dp, 60.dp),
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(0.dp))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_action_share),
                        contentDescription = "Localized description",
                        modifier = Modifier
                            .size(20.dp, 13.dp)
                            .align(Alignment.CenterVertically),
                        tint = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(50.dp))
                Text(
                    text = "Share the apps to your friends to get coins!",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.size(300.dp, 20.dp),
                    color = Color.Black,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.height(30.dp))
                Button(
                    onClick = {navController.navigate(Routes.StartPage.route)},
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .width(240.dp)
                        .height(35.dp)
                        .padding(vertical = 0.dp, horizontal = 30.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF5C6BC0),
                    )

                    //.padding(vertical = 12.dp, horizontal = 20.dp)
                ) {
                    Text(
                        text = "LOGOUT",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.size(100.dp, 60.dp),
                        color = Color.White
                    )
                }
            }
        })
}
@Preview(showBackground = true)
@Composable
fun DefaultPreview9() {
    val navController = rememberNavController()
    var money = remember { mutableStateOf(5780) }
    Profile(navController = navController,money)
}