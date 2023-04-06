package com.example.playwithsap.domain.repository

import com.example.playwithsap.domain.model.Empl
import com.example.playwithsap.domain.util.MyResult


interface EmplRepository {
    suspend fun get(): MyResult<List<Empl>>
}