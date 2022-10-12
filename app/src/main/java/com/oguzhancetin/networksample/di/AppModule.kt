package com.oguzhancetin.networksample.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.oguzhancetin.networksample.data.source.remote.api.GithubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object  AppModule{

    @Provides
    fun provideAgeOfEmpiresApi(): GithubApi{
        return Retrofit.Builder()
            .baseUrl(GithubApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(GithubApi::class.java)
    }
}