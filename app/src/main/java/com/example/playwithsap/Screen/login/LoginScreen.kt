package com.example.playwithsap.Screen.login


import Background
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
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.playwithsap.R
import com.example.playwithsap.Screen.ui.theme.PoscoOG
import com.example.playwithsap.Screen.ui.theme.Typography
import com.example.playwithsap.domain.datastore.StoreSavedToken
import com.example.playwithsap.domain.model.Login
import com.example.playwithsap.domain.util.MyResult
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel = hiltViewModel(),
    onNavigateToEmplScreen: () -> Unit = {},
    scope: CoroutineScope ,
    scaffoldState: ScaffoldState
){

    val loginResult by loginViewModel.loginState.collectAsState()
    val sharedPref = loginViewModel.sharedPref

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
    ) {
        Background(Offset(0f, -700f))
        Logo()
        Login(
            loginResult = loginResult,
            sharedPref = sharedPref,
            loginBtnOnClick = { loginViewModel.login() },
            onNavigateToEmplScreen = {onNavigateToEmplScreen()}
        )
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Login(
    loginResult: MyResult<Login>,
    sharedPref: StoreSavedToken,
    loginBtnOnClick: () ->  Unit,
    onNavigateToEmplScreen: () -> Unit
){
    val userTextState = remember { mutableStateOf("") }
    val passwordTextState = remember { mutableStateOf("") }
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current


    Column(
        modifier = Modifier
            .padding(start = 20.dp, top = 100.dp, end = 20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        UserInputName(
            userTextState = userTextState,
            focusManager = focusManager
        )
        UserInputPW(
            passwordTextState = passwordTextState,
            focusManager = focusManager,
            keyboardController = keyboardController!!
        )
        LoginBtn(
            userTextState = userTextState,
            passwordTextState = passwordTextState,
            sharedPref = sharedPref,
            loginBtnOnClick = loginBtnOnClick,
            loginResult = loginResult
        )

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

@Preview
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
                    translationY = -700f
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
@Composable
fun LoginBtn(
    userTextState: MutableState<String>,
    passwordTextState: MutableState<String>,
    sharedPref: StoreSavedToken,
    loginBtnOnClick: () -> Unit,
    loginResult: MyResult<Login>
){
    val context = LocalContext.current

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
            loginBtnOnClick() },
        enabled = loginResult !is MyResult.Loading
    ){
        if (loginResult is MyResult.Loading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
            )
        }
        Text(text = "로그인", style = Typography.h5)
    }
}

@Composable
fun UserInputName(
    userTextState: MutableState<String>,
    focusManager: FocusManager,

){
    OutlinedTextField(
        modifier = Modifier.padding(bottom = 10.dp),
        value = userTextState.value,
        onValueChange = { userTextState.value = it },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next
        ),
        shape = RoundedCornerShape(24.dp),
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
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserInputPW(
    passwordTextState: MutableState<String>,
    focusManager: FocusManager,
    keyboardController: SoftwareKeyboardController
){
    OutlinedTextField(
        value = passwordTextState.value,
        onValueChange = { passwordTextState.value = it },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        shape = RoundedCornerShape(24.dp),
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
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun prev(){
    val userTextState = remember { mutableStateOf("") }
    val passwordTextState = remember { mutableStateOf("") }
    Column{
        UserInputName(userTextState = userTextState, focusManager = LocalFocusManager.current)
        UserInputPW(passwordTextState = passwordTextState, focusManager = LocalFocusManager.current, keyboardController = LocalSoftwareKeyboardController.current!!)
    }
   
}