package com.jb.pixelquest.data.local.android.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jb.pixelquest.shared.domain.model.quest.QuestRequirements
import com.jb.pixelquest.shared.domain.model.reward.Reward

/**
 * Quest 관련 TypeConverter
 */
class QuestTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromRewardList(value: String): List<Reward> {
        val listType = object : TypeToken<List<Reward>>() {}.type
        return gson.fromJson(value, listType) ?: emptyList()
    }

    @TypeConverter
    fun toRewardList(list: List<Reward>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromQuestRequirements(value: String): QuestRequirements {
        return gson.fromJson(value, QuestRequirements::class.java)
            ?: QuestRequirements()
    }

    @TypeConverter
    fun toQuestRequirements(requirements: QuestRequirements): String {
        return gson.toJson(requirements)
    }
}

