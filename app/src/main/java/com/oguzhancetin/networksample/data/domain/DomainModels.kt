package com.oguzhancetin.networksample.data.domain


data class AppUser(
    val id: Long,
    val userName: String,
    val avatarUrl: String?
)

data class AppUserDetails(
    val name: String,
    val email: String?,
    val twitter: String?,
    val organisation: String?,
    val bio: String?,
    val followers: Long,
    val following: Long,
    val avatarUrl: String?,
    val location: String?
)