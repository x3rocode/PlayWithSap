package com.example.playwithsap.domain.repository

import com.example.playwithsap.domain.model.Empl

interface EmplRepository {
    suspend fun getEmpl(): List<Empl>
}