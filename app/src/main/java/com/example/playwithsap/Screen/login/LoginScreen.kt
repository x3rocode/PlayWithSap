package com.example.playwithsap.Screen.login


import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.playwithsap.domain.util.MyResult

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel()
){
    val loginResult by loginViewModel.loginState.collectAsState()
    val userTextState by remember {
        mutableStateOf("")
    }
    val passwordTextState by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column() {
        TextField(value = userTextState, onValueChange = { userTextState })
        TextField(value = passwordTextState, onValueChange = { passwordTextState })
        Button(modifier = Modifier,
            onClick = {
            loginViewModel.login(userTextState, passwordTextState, context)
        }){
            Text("login")
        }
    }

    when(loginResult){
        is MyResult.Success -> { 
            Text(loginResult.data!!.login)
        }
        else -> {}
    }
}