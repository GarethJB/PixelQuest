package com.jb.pixelquest.shared.domain.usecase.user

import com.jb.pixelquest.shared.domain.model.user.User
import com.jb.pixelquest.shared.domain.repository.user.UserRepository

/**
 * 사용자 프로필 이미지 업데이트 UseCase
 */
class UpdateUserAvatarUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(avatarUrl: String): Result<User> {
        return userRepository.updateUserAvatar(avatarUrl)
    }
}

