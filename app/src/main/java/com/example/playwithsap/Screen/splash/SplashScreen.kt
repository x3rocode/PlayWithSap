
import androidx.compose.animation.*
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.onEach


@ExperimentalAnimationApi
@Composable
fun SplashScreen(mainViewModel: SplashViewModel = viewModel()) {
    val seconds by mainViewModel.seconds.collectAsState("")

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
                    targetState = seconds,
                    transitionSpec = {
                        addAnimation()
                    }
                ) { targetCount ->
                    Text(
                        text = "$targetCount",
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
    return slideInVertically(animationSpec = tween(durationMillis = duration, easing = LinearEasing)) { height -> height }  with
            slideOutVertically(animationSpec = tween(durationMillis = duration, easing = LinearEasing)) { height -> -height }

}

class SplashViewModel : ViewModel() {
    var groupList = mutableListOf("홀딩스", "케미칼", "DX", "이앤씨", "엠텍", "플로우", "A&C", "퓨처엠", "스틸리온", "인터내셔널")

    private val baseDelay = 130L // 기본 지연 시간
    private val delayFactor = 0.5f // 지연 시간 증가율
    private var index = 1
    var delayMillis = 0f
    val seconds =  groupList
        .asSequence()
        .asFlow()
        .onEach {
            delayMillis = baseDelay + (index * baseDelay * delayFactor)  //이렇게 계산하지 말고 뭔가 보간을 주고싶다
            index += 1
            delay(delayMillis.toLong())
        }
}

