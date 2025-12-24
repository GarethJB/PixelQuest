package com.jb.pixelquest.feature.mypage.model

/**
 * ?∏Î≤§?†Î¶¨ ?ÅÌÉú
 */
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

/**
 * ?∏Î≤§?†Î¶¨ ?ÑÏù¥??
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
    val obtainedFrom: String?, // "?òÏä§???ÑÎ£å", "?ÅÏ†ê Íµ¨Îß§" ??
    val isEquipped: Boolean = false, // ?•Ï∞© ?¨Î?
    val isNew: Boolean = false, // ?àÎ°ú ?çÎìù???ÑÏù¥??
    val metadata: Map<String, Any>? = null // ?Ä?ÖÎ≥Ñ Ï∂îÍ? ?ïÎ≥¥
)

/**
 * ?∏Î≤§?†Î¶¨ Ïπ¥ÌÖåÍ≥†Î¶¨
 */
enum class InventoryCategory {
    PALETTE, // ?îÎ†à??
    BRUSH, // Î∏åÎü¨??
    BADGE, // Î±ÉÏ?
    PROFILE_DECORATION // ?ÑÎ°ú???•Ïãù
}

/**
 * ?∏Î≤§?†Î¶¨ ?ÑÏù¥???Ä??
 */
enum class InventoryItemType {
    PALETTE, BRUSH, BADGE, PROFILE_FRAME, PROFILE_BACKGROUND, PROFILE_ICON
}

/**
 * ?ÑÏù¥???¨Í???
 */
enum class ItemRarity {
    COMMON, RARE, EPIC, LEGENDARY
}

