package com.example.playwithsap.Screen.empl.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun EmplInfoScreen(
    viewModel: EmplInfoViewModel = hiltViewModel()
){
    Column() {
        viewModel.state.empls.forEach {
            Text(text = "----------------------------------")
            Text(text = "name :  ${it.name}")
            Text(text = "mandt : ${it.mandt}")
            Text(text = "sabun : ${it.sabun}")
            Text(text = "email : ${it.email}")
            Text(text = "----------------------------------")
            Spacer(modifier = Modifier.size(10.dp))
        }
    }

}