package com.oguzhancetin.networksample.data.source.remote

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_remote_key")
data class UserRemoteKey (
    @PrimaryKey
    val userId: Long,
    val prevKey: Int?,
    val nextKey: Int?
)