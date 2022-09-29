package com.oguzhancetin.networksample.api

import com.oguzhancetin.networksample.model.Civilization
import com.oguzhancetin.networksample.model.Civilizations
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface AgeOfApi {
    @GET("v1/civilizations")
    fun civilizationList(): Deferred<Response<Civilizations>>

    companion object{
        const val BASE_URL = "https://age-of-empires-2-api.herokuapp.com/api/"

    }

}

