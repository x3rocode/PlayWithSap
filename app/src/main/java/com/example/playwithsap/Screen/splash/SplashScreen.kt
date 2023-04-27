
import android.annotation.SuppressLint
import android.util.Log
import android.view.animation.LinearInterpolator
import androidx.compose.animation.*
import androidx.compose.animation.core.*
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
import kotlinx.coroutines.flow.*


enum class Companies{

}

@SuppressLint("StateFlowValueCalledInComposition")
@ExperimentalAnimationApi
@Composable
fun SplashScreen(mainViewModel: SplashViewModel = viewModel()) {
    var linearInterpolator = LinearInterpolator()
    val animatedFloat = remember { Animatable(0f) }
    val comp = mainViewModel.companies
    val companies =  flow {
        mainViewModel.companies.forEachIndexed { index, indexedValue ->
            var fraction = (index / mainViewModel.companies.size.toFloat())
            var duration = 1000L
            val delaymillies = (linearInterpolator.getInterpolation(fraction) * duration).toLong()
            emit(indexedValue)
            delay(2500L + delaymillies)
            Log.d("dddddddddd", "애밋 ${index}, ${mainViewModel.companies.size}  ${fraction}, ${linearInterpolator.getInterpolation(fraction)}, ${index / mainViewModel.companies.size}")
        }

    }
    val company by companies.collectAsState(initial = "")
    LaunchedEffect(animatedFloat) {
        animatedFloat.animateTo(
            targetValue = 8000f,
            animationSpec = tween(delayMillis = 1000, durationMillis = 5000, easing = LinearEasing)
        )
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
                    targetState = company,
                    transitionSpec = {
                        slideInVertically(animationSpec = tween(durationMillis = 1000)) { height -> height }  with
                                slideOutVertically(animationSpec = tween(durationMillis = 1000 )) { height -> -height }

                    }
                ) { targetCount ->
                    Text(
                        text = "${targetCount}",
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
fun addAnimation(duration: Int = 500): ContentTransform {
    return slideInVertically(animationSpec = tween(durationMillis = duration)) { height -> height }  with
            slideOutVertically(animationSpec = tween(durationMillis = duration )) { height -> -height }

}
class SplashViewModel : ViewModel() {

    private val baseDelay = 250L // 기본 지연 시간
    private val delayFactor = 0.3f // 지연 시간 증가율
    private var index = 1
    var delayMillis = 0f
    val companies =
        mutableListOf("홀딩스", "케미칼", "DX", "이앤씨", "엠텍", "플로우", "A&C", "퓨처엠", "스틸리온", "인터내셔널")

    fun getCompanies(): Flow<String> = flow {
        companies.forEach {
            emit(it)
        }
    }
}
//        .asSequence()
//        .asFlow()
//        .onEach {
//            delayMillis = if(index > 8) baseDelay + (index * 0.3f * baseDelay * delayFactor) else
//                baseDelay + ((index * 0.1f) * baseDelay * delayFactor)  //이렇게 계산하지 말고 뭔가 보간을 주고싶다
//            index += 1
//            delay(delayMillis.toLong())
//        }
//}

