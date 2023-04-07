package com.example.playwithsap.Screen.login


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.playwithsap.Screen.empl.info.EmplInfoScreen
import com.example.playwithsap.domain.datastore.StoreSavedToken
import com.example.playwithsap.domain.util.MyResult

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel()
){
    val sharedPref = StoreSavedToken
    val loginResult by loginViewModel.loginState.collectAsState()
    val userTextState = remember {
        mutableStateOf("")
    }
    val passwordTextState = remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val isloginBtnClick = remember {
        mutableStateOf(false)
    }
    Column() {
        OutlinedTextField(value = userTextState.value, onValueChange = { userTextState.value = it })
        OutlinedTextField(value = passwordTextState.value,
            onValueChange = { passwordTextState.value = it },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Password))
        Button(modifier = Modifier, onClick = {
            isloginBtnClick.value = !isloginBtnClick.value
            sharedPref.setAuthToken(context, userTextState.value, passwordTextState.value)
            loginViewModel.login()
        }){
            Text("login")
        }
        if(isloginBtnClick.value){
            when(loginResult){
                is MyResult.Success -> {
                    Text(loginResult.data!!.login)
                    EmplInfoScreen()
                }
                is MyResult.Loading -> {
                    Text("loading..")
                }
                else -> {
                    Text("fail")
                }
            }
        }


    }


}