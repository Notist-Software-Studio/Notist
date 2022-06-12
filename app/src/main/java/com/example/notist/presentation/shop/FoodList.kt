package com.example.notist.presentation.shop

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.notist.R

data class FoodList(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int,
    //var initialbuy: Boolean =  false
)
//){
//    var buy: Boolean by mutableStateOf(initialbuy)
//}





