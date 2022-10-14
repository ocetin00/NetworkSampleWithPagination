package com.oguzhancetin.networksample.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oguzhancetin.networksample.data.source.remote.RemoteUser

@Database(entities = [RemoteUser::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}