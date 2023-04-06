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
    val userTextState = remember {
        mutableStateOf("")
    }
    val passwordTextState = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column() {
        TextField(value = userTextState.value, onValueChange = { userTextState.value = it })
        TextField(value = passwordTextState.value, onValueChange = { passwordTextState.value = it })
        Button(modifier = Modifier,
            onClick = {
            loginViewModel.login(userTextState.value, passwordTextState.value, context)
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