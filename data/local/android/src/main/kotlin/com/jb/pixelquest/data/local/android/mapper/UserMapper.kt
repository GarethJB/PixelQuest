package com.jb.pixelquest.data.local.android.mapper

import com.jb.pixelquest.data.local.android.entity.UserEntity
import com.jb.pixelquest.shared.domain.model.user.User

/**
 * User Entity ↔ Domain 모델 Mapper
 */
object UserMapper {
    fun UserEntity.toDomain(): User {
        return User(
            id = id,
            username = username,
            avatarUrl = avatarUrl,
            level = level,
            isVerified = isVerified,
            experiencePoints = experiencePoints,
            totalArtworks = totalArtworks,
            totalLikes = totalLikes
        )
    }

    fun User.toEntity(isCurrentUser: Boolean = false): UserEntity {
        return UserEntity(
            id = id,
            username = username,
            avatarUrl = avatarUrl,
            level = level,
            isVerified = isVerified,
            experiencePoints = experiencePoints,
            totalArtworks = totalArtworks,
            totalLikes = totalLikes,
            isCurrentUser = isCurrentUser
        )
    }
}

