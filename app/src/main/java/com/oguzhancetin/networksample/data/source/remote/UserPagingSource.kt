package com.oguzhancetin.networksample.data.source.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.oguzhancetin.networksample.data.domain.AppUser
import com.oguzhancetin.networksample.data.source.remote.api.GithubApi
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserPagingSource @Inject constructor(private val githubApi: GithubApi):
    PagingSource<Int, AppUser>() {
    override fun getRefreshKey(state: PagingState<Int, AppUser>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, AppUser> {
        val position = params.key ?: GithubApi.GITHUB_STARTING_PAGE_INDEX
        val apiQuery = "users"
        return try{
            val users = githubApi.getUsers(apiQuery,position,params.loadSize).items.asUserAppModel()
            val nextKey = if(users.isEmpty()) {
                null

            }else{
                //başta page size * 3 item yüklediği için ilk 3 sayfa yüklü geliyor
                position + (params.loadSize / GithubApi.NETWORK_PAGE_SIZE)
            }
            LoadResult.Page(
                data = users,
                prevKey = if(position == GithubApi.GITHUB_STARTING_PAGE_INDEX) null else position -1,
                nextKey = nextKey
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}