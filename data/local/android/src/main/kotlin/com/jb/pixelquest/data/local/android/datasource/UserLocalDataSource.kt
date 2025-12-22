package com.jb.pixelquest.data.local.android.datasource

import com.jb.pixelquest.data.local.android.dao.UserDao
import com.jb.pixelquest.data.local.android.mapper.UserMapper.toDomain
import com.jb.pixelquest.data.local.android.mapper.UserMapper.toEntity
import com.jb.pixelquest.shared.domain.model.user.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * 사용자 Local DataSource 구현체
 */
class UserLocalDataSource(
    private val userDao: UserDao
) {
    suspend fun getUserById(id: String): User? {
        return userDao.getUserById(id)?.toDomain()
    }

    suspend fun getCurrentUser(): User? {
        return userDao.getCurrentUser()?.toDomain()
    }

    fun observeCurrentUser(): Flow<User?> {
        return userDao.observeCurrentUser()
            .map { it?.toDomain() }
    }

    suspend fun getAllUsers(): List<User> {
        return userDao.getAllUsers()
            .map { it.toDomain() }
    }

    suspend fun saveUser(user: User, isCurrentUser: Boolean = false) {
        userDao.insertUser(user.toEntity(isCurrentUser))
    }

    suspend fun saveUsers(users: List<User>) {
        userDao.insertUsers(users.map { it.toEntity() })
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user.toEntity())
    }

    suspend fun setCurrentUser(userId: String) {
        userDao.clearCurrentUser()
        userDao.setCurrentUser(userId)
    }

    suspend fun deleteUser(id: String) {
        userDao.deleteUser(id)
    }

    suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }
}

