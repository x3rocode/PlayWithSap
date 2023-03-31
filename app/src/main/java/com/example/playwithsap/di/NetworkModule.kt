package com.example.playwithsap.di

import com.example.playwithsap.network.RetrofitApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent ::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofitApi(): RetrofitApi {
        return Retrofit.Builder()
            .baseUrl("http://dlvs4h01.poscointl.com:50000/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RetrofitApi::class.java)
    }
}