package com.jb.pixelquest.shared.data.remote.datasource

import com.jb.pixelquest.shared.data.remote.api.PixelQuestApi
import com.jb.pixelquest.shared.data.remote.mapper.UserMapper.toDomain
import com.jb.pixelquest.shared.data.remote.mapper.UserMapper.toDto
import com.jb.pixelquest.shared.domain.model.user.User

/**
 * 사용자 Remote DataSource
 */
class UserRemoteDataSource(
    private val api: PixelQuestApi
) {
    suspend fun getCurrentUser(): Result<User> {
        return try {
            val response = api.getCurrentUser()
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun getUserById(id: String): Result<User> {
        return try {
            val response = api.getUserById(id)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateUser(user: User): Result<User> {
        return try {
            val dto = user.toDto()
            val response = api.updateUser(dto)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    suspend fun updateUserAvatar(avatarUrl: String): Result<User> {
        return try {
            val response = api.updateUserAvatar(avatarUrl)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

