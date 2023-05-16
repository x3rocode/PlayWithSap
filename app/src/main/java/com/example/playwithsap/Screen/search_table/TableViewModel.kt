package com.example.playwithsap.Screen.search_table

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playwithsap.domain.repository.TableRepository
import com.example.playwithsap.domain.util.MyResult
import com.example.playwithsap.network.model.TableDataDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class TableViewModel @Inject constructor(
    private val tableRepository: TableRepository
) : ViewModel(){

    private val _tableState = MutableStateFlow<MyResult<List<TableDataDto>>>(MyResult.Idel())
    val tableState: StateFlow<MyResult<List<TableDataDto>>> = _tableState

    fun getData() {
        viewModelScope.launch {
            _tableState.value = tableRepository.get()
        }
    }
}