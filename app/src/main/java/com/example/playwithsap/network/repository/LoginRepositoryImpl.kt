package com.example.playwithsap.network.repository

import android.util.Log
import com.example.playwithsap.domain.datastore.StoreSavedToken
import com.example.playwithsap.domain.model.Login
import com.example.playwithsap.domain.repository.LoginRepository
import com.example.playwithsap.domain.util.MyResult
import com.example.playwithsap.network.RetrofitApi
import com.example.playwithsap.network.model.mapper.LoginDtoMapper


class LoginRepositoryImpl constructor(
    private val api: RetrofitApi,
    private val mapper: LoginDtoMapper = LoginDtoMapper(),
    private val sharedPref: StoreSavedToken = StoreSavedToken
) : LoginRepository{
    override suspend fun login(): MyResult<Login> {

        try {
            val response = api.login(auth = sharedPref.getAuthToken())

            Log.d("dddd", "$response");
            if (response.isSuccessful) {
                response.body()?.let {

                    return MyResult.Success(mapper.mapToDomainModel(it))
                }
            }
            return MyResult.Error(response.message())
        } catch (e: Exception){
            return MyResult.Error(e.message!!)
        }

    }
}