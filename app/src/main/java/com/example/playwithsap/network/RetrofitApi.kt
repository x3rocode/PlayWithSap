package com.example.playwithsap.network

import com.example.playwithsap.network.model.EmplDto
import com.example.playwithsap.network.model.LoginDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface RetrofitApi {

    @GET("myrest/heylin")
    suspend fun getEmpl(
        @Header("Content-type") accept: String = "application/json",
        @Header("Authorization") auth: String ,
        @Header("sap-client") client: Int = 400
    ): Response<List<EmplDto>>

    @GET("myrest/login")
    suspend fun login(
        @Header("Content-type") accept: String = "application/json",
        @Header("Authorization") auth: String ,
        @Header("sap-client") client: Int = 400
    ): Response<LoginDto>
}