package com.example.playwithsap.Screen

import androidx.lifecycle.ViewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class EmplInfoViewModel constructor(

): ViewModel() {



    init {
        viewModelScope.launch {

        }
    }

    private suspend fun getCoins(){
        viewModelScope.launch {

        }
    }
}