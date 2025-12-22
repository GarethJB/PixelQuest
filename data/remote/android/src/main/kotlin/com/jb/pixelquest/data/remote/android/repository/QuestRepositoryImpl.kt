package com.jb.pixelquest.data.remote.android.repository

import com.jb.pixelquest.data.local.android.datasource.QuestLocalDataSource
import com.jb.pixelquest.shared.data.remote.datasource.QuestRemoteDataSource
import com.jb.pixelquest.shared.domain.model.quest.Achievement
import com.jb.pixelquest.shared.domain.model.quest.Activity
import com.jb.pixelquest.shared.domain.model.quest.Quest
import com.jb.pixelquest.shared.domain.model.quest.QuestStatistics
import com.jb.pixelquest.shared.domain.model.quest.UserQuestProgress
import com.jb.pixelquest.shared.domain.repository.quest.QuestRepository

/**
 * 퀘스트 Repository 구현체
 * Remote와 Local DataSource를 조합하여 구현
 */
class QuestRepositoryImpl(
    private val remoteDataSource: QuestRemoteDataSource,
    private val localDataSource: QuestLocalDataSource
) : QuestRepository {
    
    override suspend fun getDailyQuests(): Result<List<Quest>> {
        return remoteDataSource.getDailyQuests()
            .onSuccess { quests ->
                // 성공 시 로컬에 저장
                localDataSource.saveQuests(quests)
            }
            .onFailure {
                // 실패 시 로컬 데이터 반환
                val localQuests = localDataSource.getQuestsByType(
                    com.jb.pixelquest.shared.domain.model.quest.QuestType.DAILY
                )
                if (localQuests.isNotEmpty()) {
                    return Result.success(localQuests)
                }
            }
    }

    override suspend fun getWeeklyQuests(): Result<List<Quest>> {
        return remoteDataSource.getWeeklyQuests()
            .onSuccess { quests ->
                localDataSource.saveQuests(quests)
            }
            .onFailure {
                val localQuests = localDataSource.getQuestsByType(
                    com.jb.pixelquest.shared.domain.model.quest.QuestType.WEEKLY
                )
                if (localQuests.isNotEmpty()) {
                    return Result.success(localQuests)
                }
            }
    }

    override suspend fun getActiveQuests(): Result<List<Quest>> {
        return remoteDataSource.getActiveQuests()
            .onSuccess { quests ->
                quests.forEach { localDataSource.saveQuest(it) }
            }
            .onFailure {
                val localQuests = localDataSource.getQuestsByStatus(
                    com.jb.pixelquest.shared.domain.model.quest.QuestStatus.ACTIVE
                )
                if (localQuests.isNotEmpty()) {
                    return Result.success(localQuests)
                }
            }
    }

    override suspend fun getCompletedQuests(): Result<List<Quest>> {
        return remoteDataSource.getCompletedQuests()
            .onSuccess { quests ->
                quests.forEach { localDataSource.saveQuest(it) }
            }
            .onFailure {
                val localQuests = localDataSource.getQuestsByStatus(
                    com.jb.pixelquest.shared.domain.model.quest.QuestStatus.COMPLETED
                )
                if (localQuests.isNotEmpty()) {
                    return Result.success(localQuests)
                }
            }
    }

    override suspend fun getQuestById(id: String): Result<Quest> {
        return remoteDataSource.getQuestById(id)
            .onSuccess { quest ->
                localDataSource.saveQuest(quest)
            }
            .onFailure {
                val localQuest = localDataSource.getQuestById(id)
                if (localQuest != null) {
                    return Result.success(localQuest)
                }
            }
    }

    override suspend fun startQuest(questId: String): Result<Quest> {
        return remoteDataSource.startQuest(questId)
            .onSuccess { quest ->
                localDataSource.saveQuest(quest)
            }
    }

    override suspend fun completeQuest(questId: String, artworkId: String): Result<Quest> {
        return remoteDataSource.completeQuest(questId, artworkId)
            .onSuccess { quest ->
                localDataSource.saveQuest(quest)
            }
    }

    override suspend fun getUserQuestProgress(): Result<UserQuestProgress> {
        return remoteDataSource.getUserQuestProgress()
    }

    override suspend fun getQuestStatistics(): Result<QuestStatistics> {
        return remoteDataSource.getQuestStatistics()
    }

    override suspend fun getAchievements(): Result<List<Achievement>> {
        return remoteDataSource.getAchievements()
    }

    override suspend fun getRecentActivities(limit: Int): Result<List<Activity>> {
        return remoteDataSource.getRecentActivities(limit)
    }

    override suspend fun claimReward(rewardId: String): Result<Unit> {
        return remoteDataSource.claimReward(rewardId)
    }
}

