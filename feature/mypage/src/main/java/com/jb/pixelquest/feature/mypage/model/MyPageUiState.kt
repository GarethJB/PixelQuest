package com.jb.pixelquest.feature.mypage.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize

/**
 * MyPage ?”ë©´??UI ?íƒœ
 * State Hoisting ?¨í„´???„í•´ ëª¨ë“  ?íƒœë¥??ìœ„ë¡??Œì–´?¬ë¦¼
 */
data class MyPageUiState(
    val isLoading: Boolean = false,
    val selectedTab: MyPageTab = MyPageTab.MY_ARTWORKS,
    val myArtworks: List<Artwork> = emptyList(),
    val draftArtworks: List<Artwork> = emptyList(), // ì´ˆì•ˆ
    val publishedArtworks: List<Artwork> = emptyList(), // ê³µê°œ???‘í’ˆ
    val selectedArtwork: Artwork? = null,
    val showArtworkDetail: Boolean = false,
    val sortOption: ArtworkSortOption = ArtworkSortOption.LATEST,
    val filterOption: ArtworkFilterOption? = null,
    val error: String? = null
)

/**
 * ?‘í’ˆ (Gallery ëª¨ë“ˆê³?? ì‚¬?˜ì?ë§?MyPage ?„ìš© ?„ë“œ ì¶”ê?)
 */
data class Artwork(
    val id: String,
    val title: String,
    val description: String?,
    val imageUrl: String,
    val thumbnailUrl: String,
    val category: ArtworkCategory,
    val tags: List<String>,
    val likes: Int,
    val views: Int,
    val comments: Int,
    val createdAt: Long,
    val updatedAt: Long,
    val canvasSize: IntSize,
    val palette: List<Color>? = null,
    val questId: String? = null,
    val isPublished: Boolean = false, // ê³µê°œ ?¬ë?
    val isDraft: Boolean = false // ì´ˆì•ˆ ?¬ë?
)

/**
 * MyPage ??
 */
enum class MyPageTab {
    MY_ARTWORKS, // ?˜ì˜ ?‘í’ˆ
    INVENTORY // ?¸ë²¤? ë¦¬
}

/**
 * ?‘í’ˆ ?•ë ¬ ?µì…˜
 */
enum class ArtworkSortOption {
    LATEST, // ìµœì‹ ??
    OLDEST, // ?¤ë˜?œìˆœ
    MOST_LIKED, // ì¢‹ì•„?”ìˆœ
    MOST_VIEWED // ì¡°íšŒ?˜ìˆœ
}

/**
 * ?‘í’ˆ ?„í„° ?µì…˜
 */
enum class ArtworkFilterOption {
    PUBLISHED, // ê³µê°œ???‘í’ˆë§?
    DRAFT, // ì´ˆì•ˆë§?
    QUEST_RELATED // ?˜ìŠ¤??ê´€???‘í’ˆë§?
}

/**
 * ?‘í’ˆ ì¹´í…Œê³ ë¦¬ (Gallery?€ ?™ì¼)
 */
enum class ArtworkCategory {
    RETRO, FANTASY, CYBERPUNK, ANIMAL, CHARACTER, LANDSCAPE, OBJECT, ICON, PATTERN, ABSTRACT
}

