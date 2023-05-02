package com.example.playwithsap.Screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.playwithsap.Screen.ui.theme.PlayWithSapTheme
import com.example.playwithsap.Screen.ui.theme.PoscoBlue
import dagger.hilt.android.AndroidEntryPoint



@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PlayWithSapTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = PoscoBlue) {
                    Navigation()
                }
            }
        }
    }
}
