
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

//package com.example.playwithsap.Screen.splash
//
//import androidx.compose.animation.*
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material.Button
//import androidx.compose.material.Text
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//
//@OptIn(ExperimentalAnimationApi::class)
//@Composable
//fun Splash(){
//
//    val visible = remember { mutableStateOf(true) }
//    var count by remember {
//        mutableStateOf(0)
//    }
//    Box(
//        modifier = Modifier.fillMaxSize()
//    ){
//        Column(
//            modifier = Modifier.align(Alignment.Center),
//        ) {
//            AnimatedContent(
//                targetState = count,
//                transitionSpec = {
//                    slideInVertically(
//                        initialOffsetY = { 100 }
//                    ) with slideOutVertically()
//                }
//            ) { targetCount ->
//                Text(
//                    modifier = Modifier.padding(10.dp),
//                    text = "$targetCount",
//                    fontSize = 20.sp
//                )
//            }
//
//            Button(
//                modifier = Modifier.padding(top = 8.dp),
//                onClick = {
//                    count++
//                }
//            ) {
//                Text(text = "Increase Count")
//            }
//        }
//    }
//
//
//}



@ExperimentalAnimationApi
@Composable
fun SplashScreen(mainViewModel: MainViewModel = viewModel()) {
    val seconds by mainViewModel.seconds.collectAsState(initial = "00")

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedContent(
            targetState = seconds,
            transitionSpec = {
                addAnimation().using(
                    SizeTransform(clip = false)
                )
            }
        ) { targetCount ->
            Text(
                text = "$targetCount",
                style = TextStyle(fontSize = MaterialTheme.typography.h1.fontSize),
                textAlign = TextAlign.Center
            )
        }
    }
}

@ExperimentalAnimationApi
fun addAnimation(duration: Int = 800): ContentTransform {
    return slideInVertically(animationSpec = tween(durationMillis = duration)) { height -> height } + fadeIn(
        animationSpec = tween(durationMillis = duration)
    ) with slideOutVertically(animationSpec = tween(durationMillis = duration)) { height -> -height } + fadeOut(
        animationSpec = tween(durationMillis = duration)
    )
}

class MainViewModel : ViewModel() {
    val seconds = (0..100)
        .asSequence()
        .asFlow()
        .map {
            if (it in 0..9) "0$it" else it
        }
        .onEach { delay(1000) }
}