package com.example.playwithsap.domain.repository

import com.example.playwithsap.domain.model.Empl
import com.example.playwithsap.network.RetrofitApi
import com.example.playwithsap.network.model.mapper.EmplDtoMapper
import javax.inject.Inject

class EmplRepositoryImpl @Inject constructor(
    private val api: RetrofitApi,
    private val emplDtoMapper: EmplDtoMapper
): EmplRepository {
    override suspend fun getEmpl(): List<Empl> {
        return emplDtoMapper.toDomainList(api.getEmpl())
    }
}