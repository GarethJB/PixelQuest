package com.jb.pixelquest.shared.domain.repository.quest

import com.jb.pixelquest.shared.domain.model.quest.Achievement
import com.jb.pixelquest.shared.domain.model.quest.Activity
import com.jb.pixelquest.shared.domain.model.quest.Quest
import com.jb.pixelquest.shared.domain.model.quest.QuestStatistics
import com.jb.pixelquest.shared.domain.model.quest.UserQuestProgress

/**
 * 퀘스트 관련 데이터를 관리하는 Repository 인터페이스
 */
interface QuestRepository {
    /**
     * 일일 퀘스트 목록 조회
     */
    suspend fun getDailyQuests(): Result<List<Quest>>

    /**
     * 주간 퀘스트 목록 조회
     */
    suspend fun getWeeklyQuests(): Result<List<Quest>>

    /**
     * 진행 중인 퀘스트 목록 조회
     */
    suspend fun getActiveQuests(): Result<List<Quest>>

    /**
     * 완료된 퀘스트 목록 조회
     */
    suspend fun getCompletedQuests(): Result<List<Quest>>

    /**
     * 퀘스트 ID로 조회
     */
    suspend fun getQuestById(id: String): Result<Quest>

    /**
     * 퀘스트 시작
     */
    suspend fun startQuest(questId: String): Result<Quest>

    /**
     * 퀘스트 완료
     * @param questId 퀘스트 ID
     * @param artworkId 완료한 작품 ID
     */
    suspend fun completeQuest(questId: String, artworkId: String): Result<Quest>

    /**
     * 사용자 퀘스트 진행도 조회
     */
    suspend fun getUserQuestProgress(): Result<UserQuestProgress>

    /**
     * 퀘스트 통계 조회
     */
    suspend fun getQuestStatistics(): Result<QuestStatistics>

    /**
     * 업적 목록 조회
     */
    suspend fun getAchievements(): Result<List<Achievement>>

    /**
     * 최근 활동 목록 조회
     */
    suspend fun getRecentActivities(limit: Int = 20): Result<List<Activity>>

    /**
     * 보상 수령
     */
    suspend fun claimReward(rewardId: String): Result<Unit>
}

