package com.oguzhancetin.networksample.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oguzhancetin.networksample.data.source.remote.RemoteUser
import com.oguzhancetin.networksample.data.source.remote.RemoteUserResponse

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<RemoteUser>)

    @Query("SELECT * FROM users")
    suspend fun getUsers(): PagingSource<Int,RemoteUser>

    @Query("DELETE FROM users")
    suspend fun clearAll()
}