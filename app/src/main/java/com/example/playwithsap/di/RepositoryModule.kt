package com.example.playwithsap.di


import com.example.playwithsap.domain.repository.EmplRepository
import com.example.playwithsap.domain.repository.LoginRepository
import com.example.playwithsap.domain.repository.TableRepository
import com.example.playwithsap.network.RetrofitApi
import com.example.playwithsap.network.repository.EmplRepositoryImpl
import com.example.playwithsap.network.repository.LoginRepositoryImpl
import com.example.playwithsap.network.repository.TableRepositoryImpl
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
    fun provideEmplRepository(api: RetrofitApi): EmplRepository = EmplRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideLoginRepository(api: RetrofitApi): LoginRepository = LoginRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideTableRepository(api: RetrofitApi): TableRepository = TableRepositoryImpl(api)
}