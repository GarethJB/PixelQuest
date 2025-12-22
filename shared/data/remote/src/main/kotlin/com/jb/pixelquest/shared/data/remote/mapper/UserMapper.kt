package com.jb.pixelquest.shared.data.remote.mapper

import com.jb.pixelquest.shared.data.remote.dto.UserDto
import com.jb.pixelquest.shared.domain.model.user.User

/**
 * User DTO ↔ Domain 모델 Mapper
 */
object UserMapper {
    
    fun UserDto.toDomain(): User {
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
    
    fun User.toDto(): UserDto {
        return UserDto(
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
}

