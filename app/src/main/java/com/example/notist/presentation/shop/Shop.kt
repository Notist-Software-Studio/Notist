package com.example.notist.presentation.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notist.R
import com.example.notist.presentation.shop.BuyFood
import com.example.notist.presentation.shop.FoodList

@Composable
fun Shop(
    money : MutableState<Int>
) {
    //var money = rememberSaveable { mutableStateOf(5780) }
    Scaffold(content = {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Text("Shop Screen", style = MaterialTheme.typography.h5)
            ShopDescription()
            Currency(money.value)
            Spacer(modifier = Modifier.height(20.dp))
            FoodGrid(money)
        }
    })
}

@Composable
fun Food(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    money : MutableState<Int>,
    //buy: Boolean,
    modifier: Modifier = Modifier
) {
    var isBuy = remember { mutableStateOf(false)}
    BuyFood(isBuy,money,drawable,text)
    Button(
        onClick = {isBuy.value = true},
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .width(100.dp)
            .height(100.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFC0C8D7),)
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(drawable),
                contentDescription = "",
                modifier = Modifier
                        .size(40.dp, 40.dp)
            )
            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .width(100.dp)
                    //.align(Alignment.Bottom)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color(0xFFE4E6E7))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    //modifier = Modifier.width(192.dp)
                ){
                    Spacer(modifier = Modifier.width(15.dp))
                    Image(
                        painter = painterResource(R.drawable.coin),
                        contentDescription = "",
                        modifier = Modifier
                            .size(20.dp, 20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = stringResource(text),
                        textAlign = TextAlign.End,
                        modifier = Modifier.size(20.dp),
                        color = Color.Black
                    )
                }
            }
        }
    }
}

private val foodList = listOf(
    R.drawable.food1 to R.string.five,
    R.drawable.food2 to R.string.five,
    R.drawable.food3 to R.string.five,
    R.drawable.food4 to R.string.ten,
    R.drawable.food5 to R.string.ten,
    R.drawable.food6 to R.string.ten,
    R.drawable.food7 to R.string.fifteen,
    R.drawable.food8 to R.string.fifteen,
    R.drawable.food9 to R.string.fifteen,
    R.drawable.food10 to R.string.twenty,
    R.drawable.food11 to R.string.twenty,
    R.drawable.food12 to R.string.twenty,
).map { FoodList(it.first, it.second) }


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun FoodGrid(
    money : MutableState<Int>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier.height(425.dp)
    ) {
        items(foodList) {
            item -> Food(
            item.drawable,
            item.text,
            money,
            //item.buy,
            Modifier)
        }
    }
}

@Composable
fun ShopDescription(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier.padding(20.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.panda),
            contentDescription = "",
            modifier = Modifier
                .size(60.dp, 60.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.chatbubble),
            contentDescription = "",
            modifier = Modifier
                .size(320.dp, 50.dp)
        )
    }
}

@Composable
fun Currency(
    //modifier: Modifier = Modifier,
    money: Int,
) {
    Row(){
        Spacer(modifier = Modifier.width(230.dp))
        Button(
            onClick = { },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .width(200.dp)
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
                text = "$money",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.End,
                modifier = Modifier.size(200.dp),
                color = Color.Black
            )
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview20() {
//    var money = remember { mutableStateOf(5780) }
//    Food(
//        text = R.string.five,
//        drawable = R.drawable.food1,
//        money,
//        //buy = false
//    )
//}

@Preview(showBackground = true)
@Composable
fun DefaultPreview21() {
    var money = rememberSaveable { mutableStateOf(5780) }
    FoodGrid(money)
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview22() {
    ShopDescription()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview23() {
    var money = remember { mutableStateOf(5780) }
    Currency(money.value)
}