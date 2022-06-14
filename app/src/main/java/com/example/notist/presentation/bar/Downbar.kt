package com.example.notist.presentation.bar

import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.notist.navigation.NavRoutes

@Composable
fun bottomNavigation(modifier: Modifier = Modifier, navController: NavController) {

    val navItems = listOf(NavRoutes.Home, NavRoutes.Courses,  NavRoutes.Shop, NavRoutes.Profile)

    BottomNavigation(
        backgroundColor = Color(0xFF5C6BC0),
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navItems.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = "", modifier = Modifier.size(20.dp)) },
                label = { Text(text = stringResource(id = item.title), color = Color.White, fontSize = 8.sp) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) }
            )
        }
    }
}