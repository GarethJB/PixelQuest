package com.jb.pixelquest.shared.domain.usecase.user

import com.jb.pixelquest.shared.domain.model.user.User
import com.jb.pixelquest.shared.domain.repository.user.UserRepository

/**
 * 사용자 ID로 조회 UseCase
 */
class GetUserByIdUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(id: String): Result<User> {
        return userRepository.getUserById(id)
    }
}

