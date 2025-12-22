package com.jb.pixelquest.shared.data.remote.datasource

import com.jb.pixelquest.shared.domain.model.user.User

/**
 * 사용자 Remote DataSource 인터페이스
 */
interface UserRemoteDataSource {
    suspend fun getCurrentUser(): Result<User>
    suspend fun getUserById(id: String): Result<User>
    suspend fun updateUser(user: User): Result<User>
    suspend fun updateUserAvatar(avatarUrl: String): Result<User>
}
