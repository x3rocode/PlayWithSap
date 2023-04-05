package com.example.playwithsap.Screen.empl.info

import android.os.Bundle
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.playwithsap.SapApplication
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject


@Composable
fun EmplInfoScreen(
    viewModel: EmplInfoViewModel = hiltViewModel()
){
    val state = viewModel.state
    Text(
        "${state.empls[0].name}"
    )
}