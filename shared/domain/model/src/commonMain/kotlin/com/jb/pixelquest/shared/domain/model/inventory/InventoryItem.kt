package com.jb.pixelquest.shared.domain.model.inventory

/**
 * 인벤토리 아이템 도메인 모델
 */
data class InventoryItem(
    val id: String,
    val name: String,
    val description: String,
    val type: InventoryItemType,
    val iconUrl: String?,
    val thumbnailUrl: String?,
    val rarity: ItemRarity,
    val obtainedDate: Long,
    val obtainedFrom: String?,
    val isEquipped: Boolean = false,
    val isNew: Boolean = false,
    val metadata: Map<String, Any>? = null
)

