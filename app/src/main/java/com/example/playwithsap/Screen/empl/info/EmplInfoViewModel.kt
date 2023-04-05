package com.example.playwithsap.Screen.empl.info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.example.playwithsap.domain.model.User
import com.example.playwithsap.domain.repository.EmplRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EmplInfoViewModel @Inject constructor(
    private val repository: EmplRepository
): ViewModel() {

    var state by mutableStateOf(
        EmplContract.State(
            listOf(),
            true,
            User.getUser("zc0026","akdcl981215!")
        )
    )
    private set
    init {
        viewModelScope.launch {
            getEmpl()
        }
    }

    private suspend fun getEmpl(){
        val empls = repository.getEmpl()
        viewModelScope.launch {
            state = state.copy(empls, false)
        }
    }
}