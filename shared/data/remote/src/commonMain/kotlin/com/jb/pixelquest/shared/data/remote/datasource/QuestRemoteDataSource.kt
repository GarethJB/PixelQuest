package com.jb.pixelquest.shared.data.remote.datasource

import com.jb.pixelquest.shared.domain.model.quest.Achievement
import com.jb.pixelquest.shared.domain.model.quest.Activity
import com.jb.pixelquest.shared.domain.model.quest.Quest
import com.jb.pixelquest.shared.domain.model.quest.QuestStatistics
import com.jb.pixelquest.shared.domain.model.quest.UserQuestProgress

/**
 * 퀘스트 Remote DataSource 인터페이스
 */
interface QuestRemoteDataSource {
    suspend fun getDailyQuests(): Result<List<Quest>>
    suspend fun getWeeklyQuests(): Result<List<Quest>>
    suspend fun getActiveQuests(): Result<List<Quest>>
    suspend fun getCompletedQuests(): Result<List<Quest>>
    suspend fun getQuestById(id: String): Result<Quest>
    suspend fun startQuest(questId: String): Result<Quest>
    suspend fun completeQuest(questId: String, artworkId: String): Result<Quest>
    suspend fun getUserQuestProgress(): Result<UserQuestProgress>
    suspend fun getQuestStatistics(): Result<QuestStatistics>
    suspend fun getAchievements(): Result<List<Achievement>>
    suspend fun getRecentActivities(limit: Int = 20): Result<List<Activity>>
    suspend fun claimReward(rewardId: String): Result<Unit>
}
