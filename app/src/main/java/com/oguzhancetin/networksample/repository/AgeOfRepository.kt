package com.oguzhancetin.networksample.repository

import com.oguzhancetin.networksample.api.AgeOfApi
import com.oguzhancetin.networksample.model.Civilization
import java.util.concurrent.Flow
import javax.inject.Inject

class AgeOfRepository @Inject constructor(private val ageOfApi: AgeOfApi): BaseRepository() {
    suspend fun getCivilizations(): MutableList<Civilization>?{
        return safeApiCall(
            call = { ageOfApi.civilizationList().await() },
            error = "Error fectign civilizations"
        )?.civilizations?.toMutableList()
    }
}