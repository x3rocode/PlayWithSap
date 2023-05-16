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
import com.example.playwithsap.Screen.search_table.TableScreen
import com.example.playwithsap.Screen.search_table.TableViewModel
import com.example.playwithsap.Screen.splash.SplashViewModel


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Navigation(
    splashViewModel: SplashViewModel = hiltViewModel(),
    loginViewModel: LoginViewModel = hiltViewModel(),
    emplViewModel: EmplInfoViewModel = hiltViewModel() ,
    tableViewModel: TableViewModel = hiltViewModel()
){
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    NavHost(navController = navController, startDestination = "login"){
        composable("splash"){
            SplashScreen(
                splashViewModel = splashViewModel,
                onNavigateToLoginScreen = {
                    navController.navigate("login")
                }
            )
        }
        composable("login"){
            LoginScreen(
                loginViewModel= loginViewModel,
                onNavigateToEmplScreen = {
                     navController.navigate("table"){
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
        composable("table"){
            TableScreen(
                tableViewModel = tableViewModel,
                onNavigateToTableScreen = {
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

    }
}