package com.example.playwithsap.Screen.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.playwithsap.R
import com.example.playwithsap.Screen.ui.theme.PoscoBlue
import com.example.playwithsap.Screen.ui.theme.PoscoOG
import com.example.playwithsap.Screen.ui.theme.Typography
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PoscoBlue),
    ) {
        Logo()

        Column(
            modifier = Modifier
                .padding(start = 20.dp, top = 100.dp, end = 20.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            OutlinedTextField(
                value = userTextState.value,
                onValueChange = { userTextState.value = it },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                shape = RoundedCornerShape(38.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.White,
                    textColor = Color.White
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                ),

                label = {
                    Text(text ="user", style = Typography.h5)
                },


            )
            OutlinedTextField(
                value = passwordTextState.value,
                onValueChange = { passwordTextState.value = it },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                shape = RoundedCornerShape(38.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = Color.White,
                    textColor = Color.White
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        keyboardController?.hide()
                    }
                ),
                label = {
                    Text(text = "password", style = Typography.h5)
                }
            )
            OutlinedButton(
                modifier = Modifier
                    .width(280.dp)
                    .height(60.dp)
                    .padding(top = 10.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    backgroundColor = PoscoOG,
                    contentColor = Color.White,
                ),
                shape = RoundedCornerShape(38.dp),
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
                Text(text = "login", style = Typography.h5)
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
}

@Composable
fun Logo(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 100.dp)
                .graphicsLayer {
                    translationY = -500f
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_posco),
                contentDescription = "posco logo",
                modifier = Modifier.width(105.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
            Row() {
                Text(
                    text = "포스코인터내셔널",
                    style = Typography.h3
                )
            }
        }
    }
}