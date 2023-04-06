package com.example.playwithsap.network.repository

import com.example.playwithsap.domain.model.Login
import com.example.playwithsap.domain.repository.LoginRepository
import com.example.playwithsap.domain.util.MyResult
import com.example.playwithsap.network.RetrofitApi
import com.example.playwithsap.network.model.mapper.LoginDtoMapper


class LoginRepositoryImpl constructor(
    private val api: RetrofitApi,
    private val mapper: LoginDtoMapper = LoginDtoMapper()
) : LoginRepository{
    override suspend fun login(authToken: String): MyResult<Login> {

        val response = api.login(auth = authToken)
        if (response.isSuccessful) {
            response.body()?.let {

                return MyResult.Success(mapper.mapToDomainModel(it))
            }
        }
        return MyResult.Error(response.message())
    }


}