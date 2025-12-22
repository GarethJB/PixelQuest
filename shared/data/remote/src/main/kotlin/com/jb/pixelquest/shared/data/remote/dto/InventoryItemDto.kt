package com.jb.pixelquest.shared.data.remote.dto

import com.google.gson.annotations.SerializedName

/**
 * 인벤토리 아이템 DTO
 */
data class InventoryItemDto(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("type")
    val type: String, // "PALETTE", "BRUSH", "BADGE", "PROFILE_FRAME", etc.
    @SerializedName("iconUrl")
    val iconUrl: String?,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?,
    @SerializedName("rarity")
    val rarity: String, // "COMMON", "RARE", "EPIC", "LEGENDARY"
    @SerializedName("obtainedDate")
    val obtainedDate: Long,
    @SerializedName("obtainedFrom")
    val obtainedFrom: String?,
    @SerializedName("isEquipped")
    val isEquipped: Boolean = false,
    @SerializedName("isNew")
    val isNew: Boolean = false,
    @SerializedName("metadata")
    val metadata: Map<String, Any>? = null
)

