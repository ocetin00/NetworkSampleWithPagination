package com.oguzhancetin.networksample.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oguzhancetin.networksample.data.source.remote.RemoteUser
import com.oguzhancetin.networksample.data.source.remote.UserRemoteKey

@Database(entities = [RemoteUser::class,UserRemoteKey::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun userRemoteKeyDao(): UserRemoteKeyDao
}