package com.jb.pixelquest.feature.mypage.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize

data class MyPageUiState(
    val isLoading: Boolean = false,
    val selectedTab: MyPageTab = MyPageTab.MY_ARTWORKS,
    val myArtworks: List<Artwork> = emptyList(),
    val draftArtworks: List<Artwork> = emptyList(), // 초안
    val publishedArtworks: List<Artwork> = emptyList(),
    val selectedArtwork: Artwork? = null,
    val showArtworkDetail: Boolean = false,
    val sortOption: ArtworkSortOption = ArtworkSortOption.LATEST,
    val filterOption: ArtworkFilterOption? = null,
    val error: String? = null
)

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
    val isPublished: Boolean = false,
    val isDraft: Boolean = false
)

enum class MyPageTab {
    MY_ARTWORKS,
    INVENTORY
}

enum class ArtworkSortOption {
    LATEST,
    OLDEST,
    MOST_LIKED,
    MOST_VIEWED
}

enum class ArtworkFilterOption {
    PUBLISHED,
    DRAFT,
    QUEST_RELATED
}

enum class ArtworkCategory {
    RETRO, FANTASY, CYBERPUNK, ANIMAL, CHARACTER, LANDSCAPE, OBJECT, ICON, PATTERN, ABSTRACT
}

