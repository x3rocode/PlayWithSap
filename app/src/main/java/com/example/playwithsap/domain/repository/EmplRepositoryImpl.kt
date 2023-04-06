package com.example.playwithsap.domain.repository

import com.example.playwithsap.network.RetrofitApi
import com.example.playwithsap.network.model.Empl
//import com.example.playwithsap.network.model.mapper.EmplDtoMapper
import dagger.Binds
import javax.inject.Inject

class EmplRepositoryImpl constructor(
    private val api: RetrofitApi
): EmplRepository {



    override suspend fun getEmpl(): List<Empl> {

        return api.getEmpl()
    }
}