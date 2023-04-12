package com.example.playwithsap.Screen

import SplashScreen
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.example.playwithsap.Screen.empl.EmplInfoViewModel
import com.example.playwithsap.Screen.empl.info.EmplInfoScreen
import com.example.playwithsap.Screen.login.LoginScreen
import com.example.playwithsap.Screen.login.LoginViewModel


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    loginViewModel: LoginViewModel = hiltViewModel(),
    emplViewModel: EmplInfoViewModel = hiltViewModel()
){
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = "splash"){
        composable("login"){
            LoginScreen(
                loginViewModel= loginViewModel,
                onNavigateToEmplScreen = {
                     navController.navigate("empl"){
                         navOptions {
                             anim {
                                 enter = popEnter
                             }
                         }
                     }
                },
                scope = scope,
                scaffoldState = scaffoldState,
            )
        }
        composable("empl"){
            EmplInfoScreen(
                emplViewModel = emplViewModel,
                onNavigateToEmplScreen = {
                    navController.navigate("login"){
                        navOptions {
                            anim {

                            }
                        }
                    }
                },
                scope = scope,
                scaffoldState = scaffoldState
            )
        }
        composable("splash"){
            SplashScreen()
        }

    }
}