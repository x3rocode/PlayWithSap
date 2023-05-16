package com.example.playwithsap.domain.repository

import com.example.playwithsap.domain.util.MyResult
import com.example.playwithsap.network.model.TableDataDto


interface TableRepository {
    suspend fun get(): MyResult<List<TableDataDto>>
}