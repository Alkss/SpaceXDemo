package com.mindera.rocketscience.featue_demo.util

sealed class Screen(val route: String) {
    data object HomeScreen : Screen("home_screen")
}
