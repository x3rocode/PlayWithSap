
import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.playwithsap.R
import com.example.playwithsap.Screen.splash.SplashViewModel
import com.example.playwithsap.Screen.ui.theme.Typography
import kotlinx.coroutines.delay



@SuppressLint("StateFlowValueCalledInComposition", "FlowOperatorInvokedInComposition")
@ExperimentalAnimationApi
@Composable
fun SplashScreen(
    splashViewModel: SplashViewModel ,
    onNavigateToLoginScreen: () -> Unit,
) {

    var companies =  splashViewModel.companies
    var currentIndex by remember { mutableStateOf(0) }
    var delaymillies = 0L
    var myEasing = CubicBezierEasing(0.270f, 0.945f, 0.610f, 1.005f)
    var duration = 800L
    var position by remember { mutableStateOf(Offset.Zero) }
    var targetPosition = Offset(0f, -500f)

    LaunchedEffect(key1 = currentIndex, key2 = position) {
        var fraction = (currentIndex / companies.size.toFloat())

        if(currentIndex <= companies.size - 2){
            delaymillies = (LinearEasing.transform(fraction) * duration).toLong()
            delay(delaymillies)
        } else {
            delaymillies = (myEasing.transform(fraction) * duration).toLong()
            delay(delaymillies)

        }
        if(currentIndex < companies.size - 1 ){
            currentIndex += 1
        } else {
            position = targetPosition
        }
    }

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
                .graphicsLayer(
                    translationY = animateFloatAsState(
                        targetValue = position.y,
                        animationSpec = tween(1000, delayMillis = 1000),
                        finishedListener = {
                            onNavigateToLoginScreen()
                        }
                    ).value
                )

        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_posco),
                contentDescription = "posco logo",
                modifier = Modifier.width(105.dp),
                colorFilter = ColorFilter.tint(Color.White)
            )
            Row() {
                Text(
                    text = "포스코",
                    style = Typography.h3
                )
                AnimatedContent(
                    targetState = currentIndex,
                    transitionSpec = {
                        if(currentIndex >= companies.size -1 ){
                            addAnimation(1000)

                        }  else {
                            addAnimation(duration.toInt() / 2)
                        }
                    }
                ) { currentIndex ->
                    Text(
                        text = "${companies[currentIndex]}",
                        style = Typography.h3,
                        modifier = Modifier
                            .animateContentSize()
                            .sizeIn(minWidth = 500.dp)
                    )

                }

            }
        }

    }
}

@ExperimentalAnimationApi
fun addAnimation(delay: Int ): ContentTransform {
    return slideInVertically(animationSpec = tween(durationMillis = delay)) { height -> height }  with
            slideOutVertically(animationSpec = tween(durationMillis = delay )) { height -> -height }
}

