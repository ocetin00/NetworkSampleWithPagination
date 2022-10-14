package com.oguzhancetin.networksample.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.oguzhancetin.networksample.data.source.remote.RemoteUser
import com.oguzhancetin.networksample.data.source.remote.api.GithubApi
import com.oguzhancetin.networksample.db.AppDatabase


@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(
    private val query: String,
    private val database: AppDatabase,
    private val networkService: GithubApi
) : RemoteMediator<Int, RemoteUser>() {
    val userDao = database.userDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RemoteUser>
    ): MediatorResult {
      return MediatorResult.Success("")
    }
}