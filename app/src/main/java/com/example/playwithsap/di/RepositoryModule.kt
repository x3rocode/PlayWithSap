package com.example.playwithsap.di


import com.example.playwithsap.domain.repository.EmplRepository
import com.example.playwithsap.domain.repository.EmplRepositoryImpl
import com.example.playwithsap.network.RetrofitApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent ::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideEmplRepository(
        api: RetrofitApi
    ): EmplRepository{
        return EmplRepositoryImpl(
            api = api
        )
    }
}