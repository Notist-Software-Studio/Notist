package com.example.notist.navigation

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
    object MyLibrary: NavRoutes("myLibrary", R.string.bottom_navigation_myLibrary, R.drawable.library)
    object Shop: NavRoutes("shop", R.string.bottom_navigation_shop, R.drawable.shopping)
}
sealed class Routes(val route: String) {
    object StartPage : Routes("StartPage")
    object SignUp : Routes("SignUp")
    object ForgotPassword : Routes("ForgotPassword")
    object Login : Routes("Login")
    object MainScreen : Routes("MainScreen")
    object ProfileSettings : Routes("ProfileSettings")
    object Teacher: Routes("Teacher/{class_name}",)
    object AddCourse: Routes("AddClass")
}
