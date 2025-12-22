package com.jb.pixelquest.shared.domain.usecase.user

import com.jb.pixelquest.shared.domain.model.user.User
import com.jb.pixelquest.shared.domain.repository.user.UserRepository

/**
 * 사용자 정보 업데이트 UseCase
 */
class UpdateUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User): Result<User> {
        return userRepository.updateUser(user)
    }
}

