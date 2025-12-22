package com.jb.pixelquest.data.local.android.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * 사용자 Room Entity
 */
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val username: String,
    val avatarUrl: String?,
    val level: Int = 1,
    val isVerified: Boolean = false,
    val experiencePoints: Int = 0,
    val totalArtworks: Int = 0,
    val totalLikes: Int = 0,
    val isCurrentUser: Boolean = false, // 현재 사용자 여부
    val cachedAt: Long = System.currentTimeMillis()
)

