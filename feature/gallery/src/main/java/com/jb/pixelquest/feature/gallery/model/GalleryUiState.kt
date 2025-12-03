package com.jb.pixelquest.feature.gallery.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize

/**
 * Gallery 화면의 UI 상태
 * State Hoisting 패턴을 위해 모든 상태를 상위로 끌어올림
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
 * 작품
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
    val questId: String? = null, // 퀘스트 작품인 경우
    val isCollaborative: Boolean = false, // 협업 작품 여부
    val contributors: List<User> = emptyList() // 협업 작품 참여자
)

/**
 * 사용자
 */
data class User(
    val id: String,
    val username: String,
    val avatarUrl: String?,
    val level: Int = 1,
    val isVerified: Boolean = false
)

/**
 * Gallery 탭
 */
enum class GalleryTab {
    TRENDING, // 인기 작품
    LATEST, // 최신 작품
    CATEGORY // 카테고리별
}

/**
 * 작품 카테고리
 */
enum class ArtworkCategory {
    RETRO, // 레트로
    FANTASY, // 판타지
    CYBERPUNK, // 사이버펑크
    ANIMAL, // 동물
    CHARACTER, // 캐릭터
    LANDSCAPE, // 풍경
    OBJECT, // 오브젝트
    ICON, // 아이콘
    PATTERN, // 패턴
    ABSTRACT // 추상
}

