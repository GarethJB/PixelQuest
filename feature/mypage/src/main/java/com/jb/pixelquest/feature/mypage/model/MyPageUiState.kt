package com.jb.pixelquest.feature.mypage.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize

/**
 * MyPage 화면의 UI 상태
 * State Hoisting 패턴을 위해 모든 상태를 상위로 끌어올림
 */
data class MyPageUiState(
    val isLoading: Boolean = false,
    val selectedTab: MyPageTab = MyPageTab.MY_ARTWORKS,
    val myArtworks: List<Artwork> = emptyList(),
    val draftArtworks: List<Artwork> = emptyList(), // 초안
    val publishedArtworks: List<Artwork> = emptyList(), // 공개된 작품
    val selectedArtwork: Artwork? = null,
    val showArtworkDetail: Boolean = false,
    val sortOption: ArtworkSortOption = ArtworkSortOption.LATEST,
    val filterOption: ArtworkFilterOption? = null,
    val error: String? = null
)

/**
 * 작품 (Gallery 모듈과 유사하지만 MyPage 전용 필드 추가)
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
    val isPublished: Boolean = false, // 공개 여부
    val isDraft: Boolean = false // 초안 여부
)

/**
 * MyPage 탭
 */
enum class MyPageTab {
    MY_ARTWORKS, // 나의 작품
    INVENTORY // 인벤토리
}

/**
 * 작품 정렬 옵션
 */
enum class ArtworkSortOption {
    LATEST, // 최신순
    OLDEST, // 오래된순
    MOST_LIKED, // 좋아요순
    MOST_VIEWED // 조회수순
}

/**
 * 작품 필터 옵션
 */
enum class ArtworkFilterOption {
    PUBLISHED, // 공개된 작품만
    DRAFT, // 초안만
    QUEST_RELATED // 퀘스트 관련 작품만
}

/**
 * 작품 카테고리 (Gallery와 동일)
 */
enum class ArtworkCategory {
    RETRO, FANTASY, CYBERPUNK, ANIMAL, CHARACTER, LANDSCAPE, OBJECT, ICON, PATTERN, ABSTRACT
}

