package com.example.playwithsap.domain.repository

import com.example.playwithsap.domain.model.Login
import com.example.playwithsap.domain.util.MyResult

interface LoginRepository {
    suspend fun login(): MyResult<Login>
}