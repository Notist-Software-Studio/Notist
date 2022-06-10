package com.example.notist.navigation

import android.graphics.drawable.Icon
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.notist.R

sealed class NavRoutes(
    val route: String,
    @StringRes val title: Int,
    @DrawableRes val icon: Int) {
    object Home: NavRoutes("home", R.string.bottom_navigation_home, R.drawable.home)
    object Courses: NavRoutes("courses", R.string.bottom_navigation_courses, R.drawable.course)
    object Profile: NavRoutes("profile", R.string.bottom_navigation_profile, R.drawable.shape)
    object MyLibrary: NavRoutes("myLibrary",
        R.string.bottom_navigation_myLibrary,
        R.drawable.library
    )
    object Shop: NavRoutes("shop", R.string.bottom_navigation_shop, R.drawable.shopping)
    object Test: NavRoutes("test", R.string.test1, R.drawable.course )
    object Test1: NavRoutes("test1", R.string.test2, R.drawable.course )
    object Test2: NavRoutes("test2", R.string.test, R.drawable.course )
}
