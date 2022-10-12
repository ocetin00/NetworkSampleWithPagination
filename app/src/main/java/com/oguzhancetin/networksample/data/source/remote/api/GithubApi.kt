package com.oguzhancetin.networksample.data.source.remote.api


import com.oguzhancetin.networksample.data.source.remote.RemoteUserResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {
    @GET("search/users?sort=followers")
    suspend fun getUsers(
        @Query("q") query: String,
        @Query("page") page: Int,
        @Query("per_page") itemsPerPage: Int
    ): RemoteUserResponse

    companion object {
        const val BASE_URL = "https://api.github.com/"
        const val GITHUB_STARTING_PAGE_INDEX = 1
        const val NETWORK_PAGE_SIZE = 30

    }
}