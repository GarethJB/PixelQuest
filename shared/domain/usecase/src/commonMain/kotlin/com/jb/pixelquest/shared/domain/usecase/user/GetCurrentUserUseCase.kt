package com.jb.pixelquest.shared.domain.usecase.user

import com.jb.pixelquest.shared.domain.model.user.User
import com.jb.pixelquest.shared.domain.repository.user.UserRepository

/**
 * 현재 로그인한 사용자 조회 UseCase
 */
class GetCurrentUserUseCase(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Result<User> {
        return userRepository.getCurrentUser()
    }
}

