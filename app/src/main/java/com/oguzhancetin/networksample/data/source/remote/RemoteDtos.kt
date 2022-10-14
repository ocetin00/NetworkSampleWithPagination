package com.oguzhancetin.networksample.data.source.remote

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.oguzhancetin.networksample.data.domain.AppUser
import com.oguzhancetin.networksample.data.domain.AppUserDetails

@Entity(tableName = "users")
data class RemoteUser(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "user_name") val login: String,
    @ColumnInfo(name = "avatar_url") val avatar_url: String,
    @ColumnInfo(name = "gravatar_id") val gravatar_id: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "html_url") val html_url: String,
    @ColumnInfo(name = "followers_url") val followers_url: String,
    @ColumnInfo(name = "following_url") val following_url: String,
    @ColumnInfo(name = "gists_url") val gists_url: String,
    @ColumnInfo(name = "subscriptions_url") val subscriptions_url: String,
    @ColumnInfo(name = "organizations_url") val organizations_url: String,
    @ColumnInfo(name = "repos_url") val repos_url: String,
    @ColumnInfo(name = "events_url") val events_url: String,
    @ColumnInfo(name = "received_events_url") val received_events_url: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "starred_url") val starred_url: String


)


data class RemoteUserDetails(
    val name: String,
    val avatar_url: String?,
    val company: String?,
    val blog: String?,
    val location: String?,
    val email: String?,
    val bio: String?,
    val twitter_username: String?,
    val followers: Long,
    val following: Long,
    val created_at: String
)


data class RemoteUserResponse(
    val total_count: Long,
    val incomplete_results: Boolean,
    val items: List<RemoteUser>
)

fun RemoteUserDetails.asUserDetailAppModel() = AppUserDetails(
    name = name,
    email = email,
    twitter = twitter_username,
    bio = bio,
    location = location,
    followers = followers,
    following = following,
    organisation = company,
    avatarUrl = avatar_url
)

fun List<RemoteUser>.asUserAppModel(): List<AppUser> {
    return map {
        AppUser(
            id = it.id,
            userName = it.login,
            avatarUrl = it.avatar_url
        )
    }
}