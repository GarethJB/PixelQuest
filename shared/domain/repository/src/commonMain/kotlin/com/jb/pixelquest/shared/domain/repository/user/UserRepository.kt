package com.jb.pixelquest.shared.domain.repository.user

import com.jb.pixelquest.shared.domain.model.user.User

/**
 * 사용자 관련 데이터를 관리하는 Repository 인터페이스
 */
interface UserRepository {
    /**
     * 현재 로그인한 사용자 조회
     */
    suspend fun getCurrentUser(): Result<User>

    /**
     * 사용자 ID로 조회
     */
    suspend fun getUserById(id: String): Result<User>

    /**
     * 사용자 정보 업데이트
     */
    suspend fun updateUser(user: User): Result<User>

    /**
     * 사용자 프로필 이미지 업데이트
     */
    suspend fun updateUserAvatar(avatarUrl: String): Result<User>
}

