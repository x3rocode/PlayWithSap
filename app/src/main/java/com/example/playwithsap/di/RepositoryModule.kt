package com.example.playwithsap.di


import com.example.playwithsap.domain.repository.EmplRepository
import com.example.playwithsap.domain.repository.EmplRepositoryImpl
import com.example.playwithsap.network.RetrofitApi
import com.example.playwithsap.network.model.mapper.EmplDtoMapper
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
    fun xprovideEmplRepository(
        api: RetrofitApi,
        emplMapper: EmplDtoMapper
    ): EmplRepository{
        return EmplRepositoryImpl(
            api = api,
            emplDtoMapper = emplMapper
        )
    }
}