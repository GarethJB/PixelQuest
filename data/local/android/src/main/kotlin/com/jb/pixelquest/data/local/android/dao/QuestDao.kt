package com.jb.pixelquest.data.local.android.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jb.pixelquest.data.local.android.entity.QuestEntity
import kotlinx.coroutines.flow.Flow

/**
 * 퀘스트 DAO
 */
@Dao
interface QuestDao {
    @Query("SELECT * FROM quests WHERE id = :id")
    suspend fun getQuestById(id: String): QuestEntity?

    @Query("SELECT * FROM quests WHERE questType = :type")
    suspend fun getQuestsByType(type: String): List<QuestEntity>

    @Query("SELECT * FROM quests WHERE status = :status")
    suspend fun getQuestsByStatus(status: String): List<QuestEntity>

    @Query("SELECT * FROM quests")
    suspend fun getAllQuests(): List<QuestEntity>

    @Query("SELECT * FROM quests")
    fun observeAllQuests(): Flow<List<QuestEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuest(quest: QuestEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuests(quests: List<QuestEntity>)

    @Update
    suspend fun updateQuest(quest: QuestEntity)

    @Query("DELETE FROM quests WHERE id = :id")
    suspend fun deleteQuest(id: String)

    @Query("DELETE FROM quests")
    suspend fun deleteAllQuests()

    @Query("DELETE FROM quests WHERE cachedAt < :timestamp")
    suspend fun deleteQuestsOlderThan(timestamp: Long)
}

