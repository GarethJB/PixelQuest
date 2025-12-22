package com.jb.pixelquest.feature.mypage.model

data class InventoryState(
    val isLoading: Boolean = false,
    val selectedCategory: InventoryCategory? = null,
    val palettes: List<InventoryItem> = emptyList(),
    val brushes: List<InventoryItem> = emptyList(),
    val badges: List<InventoryItem> = emptyList(),
    val profileDecorations: List<InventoryItem> = emptyList(),
    val selectedItem: InventoryItem? = null,
    val showItemDetail: Boolean = false,
    val error: String? = null
)

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

enum class InventoryCategory {
    PALETTE,
    BRUSH,
    BADGE,
    PROFILE_DECORATION
}

enum class InventoryItemType {
    PALETTE, BRUSH, BADGE, PROFILE_FRAME, PROFILE_BACKGROUND, PROFILE_ICON
}

enum class ItemRarity {
    COMMON, RARE, EPIC, LEGENDARY
}

