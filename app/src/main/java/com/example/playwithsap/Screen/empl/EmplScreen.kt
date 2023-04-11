package com.example.playwithsap.Screen.empl.info

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.playwithsap.Screen.empl.EmplInfoViewModel
import com.example.playwithsap.domain.util.MyResult
import kotlinx.coroutines.CoroutineScope


@Composable
fun EmplInfoScreen(
    emplViewModel: EmplInfoViewModel,
    onNavigateToEmplScreen: () -> Unit,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
){
    fun launch() {
        emplViewModel.getEmpl()
    }
    launch()

    val empl by emplViewModel.emplState.collectAsState()

    when(empl){
        is MyResult.Success -> LazyColumn( ) {
            empl.data?.let { emplList ->
                items(items = emplList, itemContent = {
                    Text(text = it.name)
                })
            }

        }
        else -> {}
    }


}