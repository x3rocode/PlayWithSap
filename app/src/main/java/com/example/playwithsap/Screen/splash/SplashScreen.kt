
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.*
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.playwithsap.R
import com.example.playwithsap.Screen.ui.theme.PoscoBlue
import com.example.playwithsap.Screen.ui.theme.Typography
import kotlinx.coroutines.delay


enum class Companies{

}

@SuppressLint("StateFlowValueCalledInComposition", "FlowOperatorInvokedInComposition")
@ExperimentalAnimationApi
@Composable
fun SplashScreen(mainViewModel: SplashViewModel = viewModel()) {

    var companies =  mainViewModel.companies
    var currentIndex by remember { mutableStateOf(0) }
    var delaymillies = 0L
    var myEasing = CubicBezierEasing(0.270f, 0.945f, 0.610f, 1.005f)
    var duration = 1000L

    LaunchedEffect(key1 = currentIndex) {
        var fraction = (currentIndex / companies.size.toFloat())

        if(currentIndex <= companies.size - 3){
            delaymillies = (LinearEasing.transform(fraction) * duration).toLong()
            delay(delaymillies)
        }else{
            delaymillies = (myEasing.transform(fraction) * duration).toLong()
            delay(delaymillies)
        }

        Log.d("ddddddddd",delaymillies.toString())
        if(currentIndex < companies.size - 1 ){
            currentIndex += 1
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = PoscoBlue),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 100.dp)
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
                            slideInVertically(animationSpec = tween(durationMillis = 1100), ) { height -> height } with
                                    slideOutVertically(animationSpec = tween(durationMillis =  1100)) { height -> -height }
                        } else {
                            slideInVertically(animationSpec = tween(durationMillis = 500, delayMillis = delaymillies.toInt())) { height -> height }  with
                                    slideOutVertically(animationSpec = tween(durationMillis =  500, delayMillis = delaymillies.toInt())) { height -> -height }
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
class SplashViewModel : ViewModel() {
    val companies =
        mutableListOf("홀딩스", "케미칼", "DX", "이앤씨", "휴먼스", "엠텍", "플로우", "A&C", "퓨처엠", "스틸리온", "DX", "인터내셔널")
}
