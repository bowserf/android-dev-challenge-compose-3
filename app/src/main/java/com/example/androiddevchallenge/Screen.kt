package com.example.androiddevchallenge

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object LogIn : Screen("login")
    object Home : Screen("home")
}
