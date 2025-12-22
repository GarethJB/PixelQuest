package com.jb.pixelquest.data.remote.android.impl

import com.jb.pixelquest.data.remote.android.api.PixelQuestApi
import com.jb.pixelquest.shared.data.remote.datasource.UserRemoteDataSource
import com.jb.pixelquest.shared.data.remote.mapper.UserMapper.toDomain
import com.jb.pixelquest.shared.data.remote.mapper.UserMapper.toDto
import com.jb.pixelquest.shared.domain.model.user.User
import javax.inject.Inject

/**
 * 사용자 Remote DataSource 구현체
 */
class UserRemoteDataSourceImpl @Inject constructor(
    private val api: PixelQuestApi
) : UserRemoteDataSource {
    override suspend fun getCurrentUser(): Result<User> {
        return try {
            val response = api.getCurrentUser()
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getUserById(id: String): Result<User> {
        return try {
            val response = api.getUserById(id)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun updateUser(user: User): Result<User> {
        return try {
            val dto = user.toDto()
            val response = api.updateUser(dto)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun updateUserAvatar(avatarUrl: String): Result<User> {
        return try {
            val response = api.updateUserAvatar(avatarUrl)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

