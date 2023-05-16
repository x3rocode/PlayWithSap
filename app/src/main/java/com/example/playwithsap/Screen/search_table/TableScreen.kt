package com.example.playwithsap.Screen.search_table

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.playwithsap.domain.util.MyResult
import kotlinx.coroutines.CoroutineScope


@Composable
fun TableScreen(
    tableViewModel: TableViewModel,
    onNavigateToTableScreen: () -> Unit,
    scope: CoroutineScope,
    scaffoldState: ScaffoldState
){
    fun launch() {
        tableViewModel.getData()
    }
    launch()

    val data by tableViewModel.tableState.collectAsState()

    when(data){
        is MyResult.Success -> LazyColumn( ) {
            data.data?.let { emplList ->
                items(items = emplList, itemContent = {
                    Text(text = it.type)
                    Text(text = it.data.toString())
                })
            }

        }
        else -> {}
    }


}