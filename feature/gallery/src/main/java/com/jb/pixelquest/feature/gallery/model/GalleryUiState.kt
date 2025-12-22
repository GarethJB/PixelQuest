package com.jb.pixelquest.feature.gallery.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize

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
    val questId: String? = null,
    val isCollaborative: Boolean = false,
    val contributors: List<User> = emptyList()
)

data class User(
    val id: String,
    val username: String,
    val avatarUrl: String?,
    val level: Int = 1,
    val isVerified: Boolean = false
)

enum class GalleryTab {
    TRENDING,
    LATEST,
    CATEGORY
}

enum class ArtworkCategory {
    RETRO,
    FANTASY,
    CYBERPUNK,
    ANIMAL,
    CHARACTER,
    LANDSCAPE,
    OBJECT,
    ICON,
    PATTERN,
    ABSTRACT
}

