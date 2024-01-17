package com.example.youtube.di

import com.example.youtube.network.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return ApiClient.getRetrofit()
    }

    @Singleton
    @Provides
    fun provideApiInterface(){


    }

}