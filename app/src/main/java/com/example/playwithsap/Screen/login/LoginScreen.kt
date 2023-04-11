package com.example.playwithsap.Screen.login


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.playwithsap.domain.datastore.StoreSavedToken
import com.example.playwithsap.domain.util.MyResult
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalComposeUiApi::class)
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
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column() {
        OutlinedTextField(
            value = userTextState.value,
            onValueChange = { userTextState.value = it },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            ),
            label = {
                Text("user")
            }
        )
        OutlinedTextField(
            value = passwordTextState.value,
            onValueChange = { passwordTextState.value = it },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            label = {
                Text("password")
            }
        )
        Button(modifier = Modifier,
            onClick = {
            sharedPref.setAuthToken(context, userTextState.value, passwordTextState.value)
            loginViewModel.login() },
            enabled = loginResult !is MyResult.Loading
            ){
            if (loginResult is MyResult.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(24.dp)
                        .padding(end = 8.dp)
                )
            }
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
            is MyResult.Error-> {
                Text("fail..${loginResult.message}")
            }
            else -> {}
        }


    }


}