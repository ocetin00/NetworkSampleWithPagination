package com.oguzhancetin.networksample.data


import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.oguzhancetin.networksample.data.source.remote.api.GithubApi
import com.oguzhancetin.networksample.data.source.remote.api.GithubApi.Companion.NETWORK_PAGE_SIZE
import com.oguzhancetin.networksample.db.AppDatabase
import com.oguzhancetin.networksample.db.UserDao
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val api: GithubApi,
    private val db: AppDatabase,
    private val userDao: UserDao
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getUserResult() = Pager(
        config = PagingConfig(pageSize = NETWORK_PAGE_SIZE, enablePlaceholders = false),
        remoteMediator = UserRemoteMediator("users", db, api, userDao),
        pagingSourceFactory = {
            userDao.getUsers()
        }
    ).flow
}
