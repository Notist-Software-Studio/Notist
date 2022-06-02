package com.example.notist.presentation.bar

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.notist.R

@Composable
fun bottomNavigation(modifier: Modifier = Modifier) {
    BottomNavigation(
        backgroundColor = Color(0xFF5C6BC0),
        modifier = modifier
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = { Text(stringResource(R.string.bottom_navigation_home),
                color = Color.White,
                fontSize = 11.sp) },
            selected = true,
            onClick = {}

        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null
                )
            },
            label = { Text(stringResource(R.string.bottom_navigation_courses),
                color = Color.White,
                fontSize = 11.sp) },
            selected = false,
            onClick = {}
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null
                )
            },
            label = { Text(stringResource(R.string.bottom_navigation_myLibrary),
                color = Color.White,
                fontSize = 11.sp) },
            selected = false,
            onClick = {}
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = null
                )
            },
            label = { Text(stringResource(R.string.bottom_navigation_shop),
                color = Color.White,
                fontSize = 11.sp) },
            selected = false,
            onClick = {}
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null
                )
            },
            label = { Text(stringResource(R.string.bottom_navigation_profile),
                color = Color.White,
                fontSize = 11.sp) },
            selected = false,
            onClick = {}
        )
    }
}