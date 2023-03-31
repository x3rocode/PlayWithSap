package com.example.playwithsap.network

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RetrofitApi {

    @GET("myrest/heylin")
    suspend fun getEmpl(
        @Header("Content-type") accept: String = "application/json",
        @Header("Authorization") auth: String = "Basic emMwMDI2OmFrZGNsOTgxMjE1IQ=="
    ): List<String>

}