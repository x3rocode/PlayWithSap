package com.example.playwithsap.domain.repository

import com.example.playwithsap.network.model.Empl


interface EmplRepository {
    suspend fun getEmpl(): List<Empl>
}