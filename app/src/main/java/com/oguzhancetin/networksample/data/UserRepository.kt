package com.oguzhancetin.networksample.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.oguzhancetin.networksample.data.domain.AppUser
import com.oguzhancetin.networksample.data.source.remote.UserPagingSource
import com.oguzhancetin.networksample.data.source.remote.api.GithubApi
import com.oguzhancetin.networksample.data.source.remote.api.GithubApi.Companion.NETWORK_PAGE_SIZE
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class UserRepository @Inject constructor(private val api: GithubApi) {

    fun getUserResult(): Flow<PagingData<AppUser>> {
        return Pager(
            config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = { UserPagingSource(api) }
        ).flow
    }
}