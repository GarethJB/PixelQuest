package com.jb.pixelquest.shared.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * 사용자 DTO
 */
data class UserDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("username")
    val username: String,
    @SerializedName("avatarUrl")
    val avatarUrl: String?,
    @SerializedName("level")
    val level: Int = 1,
    @SerializedName("isVerified")
    val isVerified: Boolean = false,
    @SerializedName("experiencePoints")
    val experiencePoints: Int = 0,
    @SerializedName("totalArtworks")
    val totalArtworks: Int = 0,
    @SerializedName("totalLikes")
    val totalLikes: Int = 0
)

