package com.jb.pixelquest.feature.mypage.model

/**
 * 인벤토리 상태
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
 * 인벤토리 아이템
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
    val obtainedFrom: String?, // "퀘스트 완료", "상점 구매" 등
    val isEquipped: Boolean = false, // 장착 여부
    val isNew: Boolean = false, // 새로 획득한 아이템
    val metadata: Map<String, Any>? = null // 타입별 추가 정보
)

/**
 * 인벤토리 카테고리
 */
enum class InventoryCategory {
    PALETTE, // 팔레트
    BRUSH, // 브러시
    BADGE, // 뱃지
    PROFILE_DECORATION // 프로필 장식
}

/**
 * 인벤토리 아이템 타입
 */
enum class InventoryItemType {
    PALETTE, BRUSH, BADGE, PROFILE_FRAME, PROFILE_BACKGROUND, PROFILE_ICON
}

/**
 * 아이템 희귀도
 */
enum class ItemRarity {
    COMMON, RARE, EPIC, LEGENDARY
}

