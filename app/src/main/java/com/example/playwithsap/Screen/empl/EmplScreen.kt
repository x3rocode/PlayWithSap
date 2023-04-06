package com.example.playwithsap.Screen.empl.info

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.playwithsap.Screen.empl.EmplInfoViewModel
import com.example.playwithsap.domain.util.MyResult


@Composable
fun EmplInfoScreen(
    viewModel: EmplInfoViewModel = hiltViewModel()
){
    fun launch() {
        viewModel.getEmpl()
    }
    launch()

    val empl by viewModel.emplState.collectAsState()

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