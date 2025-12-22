package com.jb.pixelquest.data.remote.android.repository

import com.jb.pixelquest.data.local.android.datasource.UserLocalDataSource
import com.jb.pixelquest.shared.data.remote.datasource.UserRemoteDataSource
import com.jb.pixelquest.shared.domain.model.user.User
import com.jb.pixelquest.shared.domain.repository.user.UserRepository
import javax.inject.Inject

/**
 * 사용자 Repository 구현체
 * Remote와 Local DataSource를 조합하여 구현
 */
class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: UserRemoteDataSource,
    private val localDataSource: UserLocalDataSource
) : UserRepository {
    
    override suspend fun getCurrentUser(): Result<User> {
        return remoteDataSource.getCurrentUser()
            .onSuccess { user ->
                localDataSource.saveUser(user, isCurrentUser = true)
            }
            .onFailure {
                val localUser = localDataSource.getCurrentUser()
                if (localUser != null) {
                    return Result.success(localUser)
                }
            }
    }

    override suspend fun getUserById(id: String): Result<User> {
        return remoteDataSource.getUserById(id)
            .onSuccess { user ->
                localDataSource.saveUser(user)
            }
            .onFailure {
                val localUser = localDataSource.getUserById(id)
                if (localUser != null) {
                    return Result.success(localUser)
                }
            }
    }

    override suspend fun updateUser(user: User): Result<User> {
        return remoteDataSource.updateUser(user)
            .onSuccess { updatedUser ->
                localDataSource.updateUser(updatedUser)
            }
    }

    override suspend fun updateUserAvatar(avatarUrl: String): Result<User> {
        return remoteDataSource.updateUserAvatar(avatarUrl)
            .onSuccess { updatedUser ->
                localDataSource.updateUser(updatedUser)
            }
    }
}

