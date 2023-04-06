package com.example.playwithsap.network.repository

//import com.example.playwithsap.network.model.mapper.EmplDtoMapper
import com.example.playwithsap.domain.model.Empl
import com.example.playwithsap.domain.repository.EmplRepository
import com.example.playwithsap.domain.util.MyResult
import com.example.playwithsap.network.RetrofitApi
import com.example.playwithsap.network.model.mapper.EmplDtoMapper

class EmplRepositoryImpl constructor(
    private val api: RetrofitApi,
    private val mapper: EmplDtoMapper = EmplDtoMapper()
): EmplRepository {

    override suspend fun get(): MyResult<List<Empl>> {
        val response = api.getEmpl()
        if(response.isSuccessful){
            response.body()?.let {
                return MyResult.Success(mapper.toDomainList(it))
            }
        }

        return MyResult.Error(response.message())
    }
}