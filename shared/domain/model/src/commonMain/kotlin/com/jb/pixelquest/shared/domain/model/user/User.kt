package com.jb.pixelquest.shared.domain.model.user

/**
 * 사용자 도메인 모델
 */
data class User(
    val id: String,
    val username: String,
    val avatarUrl: String?,
    val level: Int = 1,
    val isVerified: Boolean = false,
    val experiencePoints: Int = 0,
    val totalArtworks: Int = 0,
    val totalLikes: Int = 0
)

