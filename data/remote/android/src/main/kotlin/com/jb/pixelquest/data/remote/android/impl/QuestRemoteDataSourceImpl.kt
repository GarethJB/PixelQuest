package com.jb.pixelquest.data.remote.android.impl

import com.jb.pixelquest.data.remote.android.api.PixelQuestApi
import com.jb.pixelquest.shared.data.remote.datasource.QuestRemoteDataSource
import com.jb.pixelquest.shared.data.remote.mapper.QuestMapper.toDomain
import com.jb.pixelquest.shared.domain.model.quest.Achievement
import com.jb.pixelquest.shared.domain.model.quest.Activity
import com.jb.pixelquest.shared.domain.model.quest.Quest
import com.jb.pixelquest.shared.domain.model.quest.QuestStatistics
import com.jb.pixelquest.shared.domain.model.quest.UserQuestProgress

/**
 * 퀘스트 Remote DataSource 구현체
 */
class QuestRemoteDataSourceImpl(
    private val api: PixelQuestApi
) : QuestRemoteDataSource {
    override suspend fun getDailyQuests(): Result<List<Quest>> {
        return try {
            val response = api.getDailyQuests()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getWeeklyQuests(): Result<List<Quest>> {
        return try {
            val response = api.getWeeklyQuests()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getActiveQuests(): Result<List<Quest>> {
        return try {
            val response = api.getActiveQuests()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getCompletedQuests(): Result<List<Quest>> {
        return try {
            val response = api.getCompletedQuests()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getQuestById(id: String): Result<Quest> {
        return try {
            val response = api.getQuestById(id)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun startQuest(questId: String): Result<Quest> {
        return try {
            val response = api.startQuest(questId)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun completeQuest(questId: String, artworkId: String): Result<Quest> {
        return try {
            val response = api.completeQuest(questId, artworkId)
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getUserQuestProgress(): Result<UserQuestProgress> {
        return try {
            val response = api.getUserQuestProgress()
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getQuestStatistics(): Result<QuestStatistics> {
        return try {
            val response = api.getQuestStatistics()
            Result.success(response.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getAchievements(): Result<List<Achievement>> {
        return try {
            val response = api.getAchievements()
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getRecentActivities(limit: Int): Result<List<Activity>> {
        return try {
            val response = api.getRecentActivities(limit)
            Result.success(response.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun claimReward(rewardId: String): Result<Unit> {
        return try {
            api.claimReward(rewardId)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

