package com.example.playwithsap.Screen.login


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.playwithsap.domain.datastore.StoreSavedToken
import com.example.playwithsap.domain.util.MyResult
import kotlinx.coroutines.CoroutineScope

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    onNavigateToEmplScreen: () -> Unit,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
){
    val sharedPref = StoreSavedToken
    val loginResult by loginViewModel.loginState.collectAsState()
    val userTextState = remember { mutableStateOf("") }
    val passwordTextState = remember { mutableStateOf("") }
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
            sharedPref.setAuthToken(context, userTextState.value, passwordTextState.value)
            loginViewModel.login()

        }){
            Text("login")
        }
        when(loginResult){
            is MyResult.Success -> {
                Text("success")
                onNavigateToEmplScreen()
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