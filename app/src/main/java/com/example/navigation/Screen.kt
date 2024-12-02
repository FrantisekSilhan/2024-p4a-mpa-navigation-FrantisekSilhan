package com.example.navigation

sealed class Screen(val route: String) {
    object Main: Screen("main_screen")
    object Categories: Screen("categories_screen")
    object Detail: Screen("detail_screen")
}