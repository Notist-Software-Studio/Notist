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
fun NoMoney(
    isBuy: MutableState<Boolean>,
    cannotBuy: MutableState<Boolean>,
) {

    if ((isBuy.value) && (cannotBuy.value)) {
        Dialog(onDismissRequest = {
            isBuy.value = false
            cannotBuy.value = false
        }) {
            Surface(
                modifier = Modifier
                    .width(300.dp)
                    .height(180.dp),
                shape = RoundedCornerShape(30.dp),
                color = Color(0xFFC0C8D7)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Lack of coins to buy",
                        style = TextStyle(
                            fontSize = 25.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Gets coins from completeing objectives !",
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF000000)
                        )
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview26() {
    val isBuy = remember { mutableStateOf(true) }
    val cannotBuy = remember { mutableStateOf(true) }
    NoMoney(isBuy,cannotBuy)
}