package com.jb.pixelquest.shared.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * 퀘스트 DTO
 */
data class QuestDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("theme")
    val theme: String,
    @SerializedName("questType")
    val questType: String, // "DAILY" or "WEEKLY"
    @SerializedName("difficulty")
    val difficulty: String, // "EASY", "MEDIUM", "HARD"
    @SerializedName("rewards")
    val rewards: List<RewardDto>,
    @SerializedName("requirements")
    val requirements: QuestRequirementsDto,
    @SerializedName("status")
    val status: String, // "AVAILABLE", "IN_PROGRESS", "COMPLETED", "LOCKED"
    @SerializedName("deadline")
    val deadline: Long?,
    @SerializedName("thumbnailPath")
    val thumbnailPath: String?,
    @SerializedName("participantCount")
    val participantCount: Int = 0,
    @SerializedName("startDate")
    val startDate: Long? = null
)

/**
 * 퀘스트 요구사항 DTO
 */
data class QuestRequirementsDto(
    @SerializedName("canvasWidth")
    val canvasWidth: Int? = null,
    @SerializedName("canvasHeight")
    val canvasHeight: Int? = null,
    @SerializedName("colorLimit")
    val colorLimit: Int? = null,
    @SerializedName("themeKeywords")
    val themeKeywords: List<String> = emptyList(),
    @SerializedName("minPixels")
    val minPixels: Int? = null,
    @SerializedName("maxPixels")
    val maxPixels: Int? = null
)

/**
 * 보상 DTO
 */
data class RewardDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("type")
    val type: String, // "PALETTE", "BRUSH", "BADGE", "ITEM"
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("iconPath")
    val iconPath: String?,
    @SerializedName("rarity")
    val rarity: String = "COMMON" // "COMMON", "RARE", "EPIC", "LEGENDARY"
)

