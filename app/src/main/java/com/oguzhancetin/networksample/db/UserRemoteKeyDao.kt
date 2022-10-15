package com.oguzhancetin.networksample.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oguzhancetin.networksample.data.source.remote.UserRemoteKey

@Dao
interface UserRemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(remoteKey: List<UserRemoteKey>)
    @Query("SELECT * FROM user_remote_key WHERE userId = :userId")
    fun remoteKeysByUserId(userId: Long): UserRemoteKey?
    @Query("DELETE FROM user_remote_key")
    fun clearRemoteKeys()
}