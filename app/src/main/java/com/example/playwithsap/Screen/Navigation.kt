package com.example.playwithsap.Screen

import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.playwithsap.Screen.empl.EmplInfoViewModel
import com.example.playwithsap.Screen.login.LoginScreen
import com.example.playwithsap.Screen.login.LoginViewModel

@Composable
fun Navigation(
    loginViewModel: LoginViewModel,
    emplViewModel: EmplInfoViewModel
){
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = "Login"){
        composable("login"){
            LoginScreen(
                onNavigateToEmplScreen = {
                     navController.navigate("empl")
                },
                scope = scope,
                scaffoldState = scaffoldState
            )
        }

    }
}