package com.oguzhancetin.networksample.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.oguzhancetin.networksample.data.source.remote.RemoteUser
import com.oguzhancetin.networksample.data.source.remote.UserRemoteKey
import com.oguzhancetin.networksample.data.source.remote.api.GithubApi
import com.oguzhancetin.networksample.db.AppDatabase
import com.oguzhancetin.networksample.db.UserDao
import retrofit2.HttpException
import java.io.IOException
import java.io.InvalidObjectException


@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator(
    private val query: String,
    private val database: AppDatabase,
    private val githubApi: GithubApi,
    private val userDao: UserDao
) : RemoteMediator<Int, RemoteUser>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RemoteUser>
    ): MediatorResult {

        try {

            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextKey?.minus(1) ?: GithubApi.GITHUB_STARTING_PAGE_INDEX
                }
                LoadType.PREPEND ->
                    return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                        ?: throw InvalidObjectException("Result is empty")

                    remoteKeys.nextKey ?: return MediatorResult.Success(
                        endOfPaginationReached = true
                    )

                }

            }

            val response = githubApi.getUsers(
                query,
                page = page ,
                itemsPerPage = GithubApi.NETWORK_PAGE_SIZE
            )
            val endOfPaginationReached = response.items.size < state.config.pageSize
            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    userDao.clearAll()
                    database.userRemoteKeyDao().clearRemoteKeys()

                }
                val prevKey = if (page == GithubApi.GITHUB_STARTING_PAGE_INDEX) null else page - 1
                val nextKey = if (endOfPaginationReached) null else page + 1
                val keys = response.items.map {
                    UserRemoteKey(userId = it.id, prevKey = prevKey, nextKey = nextKey)
                }
                userDao.insertAll(response.items)
                database.userRemoteKeyDao().insertAll(keys)
            }
            return MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, RemoteUser>): UserRemoteKey? {
        return state.lastItemOrNull()?.let { user ->
            database.withTransaction { database.userRemoteKeyDao().remoteKeysByUserId(user.id)}
        }
    }
    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, RemoteUser>): UserRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                database.withTransaction {
                    database.userRemoteKeyDao().remoteKeysByUserId(id)
                }
            }
        }
    }

}