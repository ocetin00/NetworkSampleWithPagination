package com.oguzhancetin.networksample.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.oguzhancetin.networksample.api.AgeOfApi
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
    fun provideAgeOfEmpiresApi(): AgeOfApi{
        return Retrofit.Builder()
            .baseUrl(AgeOfApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
            .create(AgeOfApi::class.java)
    }
}