package com.example.playwithsap.network

import com.example.playwithsap.domain.model.Empl
import com.example.playwithsap.domain.model.User
import com.example.playwithsap.network.model.EmplDto
import com.example.playwithsap.network.model.LoginDto
import retrofit2.http.GET
import retrofit2.http.Header

interface RetrofitApi {

    @GET("myrest/heylin")
    suspend fun getEmpl(
        @Header("Content-type") accept: String = "application/json",
        @Header("Authorization") auth: String = "Basic emMwMDI2OmFrZGNsOTgxMjE1IQ==",
        @Header("sap-client") client: Int = 400
    ): List<EmplDto>



    @GET("myrest/login")
    suspend fun login(
        @Header("Content-type") accept: String = "application/json",
        @Header("Authorization") auth: String = "Basic emMwMDI2OmFrZGNsOTgxMjE1IQ==",
        @Header("sap-client") client: Int = 400
    ): List<LoginDto>
}