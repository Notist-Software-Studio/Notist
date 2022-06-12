package com.example.notist.presentation.shop

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.notist.R
import com.example.notist.presentation.screens.Currency

@Composable
fun BuyFood(
    isBuy: MutableState<Boolean>,
    money: MutableState<Int>,
    @DrawableRes drawable: Int,
    @StringRes text: Int,
) {
    var count : Int = 0
    var cost : Int = 0
    when (text) {
        R.string.five -> count = 1
        R.string.ten -> count = 2
        R.string.fifteen -> count = 3
        R.string.twenty -> count = 4
    };
    when (text) {
        R.string.five -> cost = 5
        R.string.ten -> cost = 10
        R.string.fifteen -> cost = 15
        R.string.twenty -> cost = 20
    };

    if (isBuy.value) {
        Dialog(onDismissRequest = { isBuy.value = false }) {
            Surface(
                modifier = Modifier
                    .width(300.dp)
                    .height(230.dp),
                shape = RoundedCornerShape(30.dp),
                color = Color(0xFFC0C8D7)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    //verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(
                        text = "Are your sure to buy this ?",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    )
                    Spacer(modifier = Modifier.height(18.dp))
                    Image(
                        painter = painterResource(drawable),
                        contentDescription = "",
                        modifier = Modifier
                            .size(70.dp, 70.dp)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ){
                        Spacer(modifier = Modifier.width(18.dp))
                        Image(
                            painter = painterResource(R.drawable.coin),
                            contentDescription = "",
                            modifier = Modifier
                                .size(25.dp, 25.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = stringResource(text),
                            modifier = Modifier.size(20.dp),
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "=",
                            modifier = Modifier.size(20.dp),
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Image(
                            painter = painterResource(R.drawable.chicken),
                            contentDescription = "",
                            modifier = Modifier
                                .size(20.dp, 20.dp)
                        )
                        Text(
                            text = "  x ",
                            modifier = Modifier.size(20.dp),
                            color = Color.Black
                        )
                        Text(
                            text = "$count",
                            modifier = Modifier.size(20.dp),
                            color = Color.Black
                        )
                    }
                }
                Row(
                    verticalAlignment = Alignment.Bottom,
                ){
                    Button(
                        onClick = {
                            isBuy.value = false
                        },
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .height(60.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color(0xFF5C6BC0)
                        )
                    ){
                        Text(
                            text = "NO",
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.White,
                            fontSize = 20.sp
                        )
                    }
                    Button(
                        onClick = {
                            isBuy.value = false
                            money.value = money.value - cost
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        shape = RoundedCornerShape(30.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.White
                        )
                    ){
                        Text(
                            text = "YES",
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xB85C6BC0),
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview24() {
    val isBuy = remember { mutableStateOf(true) }
    var money = remember { mutableStateOf(5780) }
   BuyFood(isBuy,money,text = R.string.five,
       drawable = R.drawable.food1,)
}