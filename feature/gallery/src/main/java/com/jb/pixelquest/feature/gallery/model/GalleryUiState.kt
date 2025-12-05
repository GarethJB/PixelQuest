package com.jb.pixelquest.feature.gallery.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize

/**
 * Gallery ?”ë©´??UI ?íƒœ
 * State Hoisting ?¨í„´???„í•´ ëª¨ë“  ?íƒœë¥??ìœ„ë¡??Œì–´?¬ë¦¼
 */
data class GalleryUiState(
    val isLoading: Boolean = false,
    val selectedTab: GalleryTab = GalleryTab.TRENDING,
    val trendingArtworks: List<Artwork> = emptyList(),
    val latestArtworks: List<Artwork> = emptyList(),
    val selectedCategory: ArtworkCategory? = null,
    val categoryArtworks: List<Artwork> = emptyList(),
    val selectedArtwork: Artwork? = null,
    val showArtworkDetail: Boolean = false,
    val searchQuery: String = "",
    val searchResults: List<Artwork> = emptyList(),
    val isSearchActive: Boolean = false,
    val error: String? = null
)

/**
 * ?‘í’ˆ
 */
data class Artwork(
    val id: String,
    val title: String,
    val description: String?,
    val author: User,
    val imageUrl: String,
    val thumbnailUrl: String,
    val category: ArtworkCategory,
    val tags: List<String>,
    val likes: Int,
    val views: Int,
    val comments: Int,
    val createdAt: Long,
    val updatedAt: Long,
    val isLiked: Boolean = false,
    val isBookmarked: Boolean = false,
    val canvasSize: IntSize,
    val palette: List<Color>? = null,
    val questId: String? = null, // ?˜ìŠ¤???‘í’ˆ??ê²½ìš°
    val isCollaborative: Boolean = false, // ?‘ì—… ?‘í’ˆ ?¬ë?
    val contributors: List<User> = emptyList() // ?‘ì—… ?‘í’ˆ ì°¸ì—¬??
)

/**
 * ?¬ìš©??
 */
data class User(
    val id: String,
    val username: String,
    val avatarUrl: String?,
    val level: Int = 1,
    val isVerified: Boolean = false
)

/**
 * Gallery ??
 */
enum class GalleryTab {
    TRENDING, // ?¸ê¸° ?‘í’ˆ
    LATEST, // ìµœì‹  ?‘í’ˆ
    CATEGORY // ì¹´í…Œê³ ë¦¬ë³?
}

/**
 * ?‘í’ˆ ì¹´í…Œê³ ë¦¬
 */
enum class ArtworkCategory {
    RETRO, // ?ˆíŠ¸ë¡?
    FANTASY, // ?í?ì§€
    CYBERPUNK, // ?¬ì´ë²„í‘??
    ANIMAL, // ?™ë¬¼
    CHARACTER, // ìºë¦­??
    LANDSCAPE, // ?ê²½
    OBJECT, // ?¤ë¸Œ?íŠ¸
    ICON, // ?„ì´ì½?
    PATTERN, // ?¨í„´
    ABSTRACT // ì¶”ìƒ
}

