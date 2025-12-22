package com.jb.pixelquest.data.local.android.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.jb.pixelquest.data.local.android.converter.QuestTypeConverter

/**
 * 퀘스트 Room Entity
 */
@Entity(tableName = "quests")
@TypeConverters(QuestTypeConverter::class)
data class QuestEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val theme: String,
    val questType: String, // "DAILY" or "WEEKLY"
    val difficulty: String, // "EASY", "MEDIUM", "HARD"
    val rewardsJson: String, // List<Reward>를 JSON으로 저장
    val requirementsJson: String, // QuestRequirements를 JSON으로 저장
    val status: String, // "AVAILABLE", "IN_PROGRESS", "COMPLETED", "LOCKED"
    val deadline: Long?,
    val thumbnailPath: String?,
    val participantCount: Int = 0,
    val startDate: Long? = null,
    val cachedAt: Long = System.currentTimeMillis()
)

