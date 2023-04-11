package com.example.playwithsap.Screen.empl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playwithsap.domain.model.Empl
import com.example.playwithsap.domain.repository.EmplRepository
import com.example.playwithsap.domain.util.MyResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@ExperimentalCoroutinesApi
@HiltViewModel
class EmplInfoViewModel  @Inject constructor(
     private val repository: EmplRepository
): ViewModel() {

    private val _emplsState = MutableStateFlow<MyResult<List<Empl>>>(MyResult.Loading())
    val emplState: StateFlow<MyResult<List<Empl>>> = _emplsState

    fun getEmpl(){
        viewModelScope.launch {
            _emplsState.value = repository.get()
        }
    }
}