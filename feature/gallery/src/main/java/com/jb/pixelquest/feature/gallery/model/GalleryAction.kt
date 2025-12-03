package com.jb.pixelquest.feature.gallery.model

/**
 * Gallery 화면의 사용자 액션
 * State Hoisting 패턴을 위해 액션을 명시적으로 정의
 */
sealed interface GalleryAction {
    // 탭 전환
    data class SelectTab(val tab: GalleryTab) : GalleryAction
    
    // 작품 목록
    object RefreshArtworks : GalleryAction
    data class LoadMoreArtworks(val tab: GalleryTab) : GalleryAction
    
    // 작품 선택
    data class SelectArtwork(val artwork: Artwork) : GalleryAction
    object HideArtworkDetail : GalleryAction
    
    // 카테고리
    data class SelectCategory(val category: ArtworkCategory) : GalleryAction
    object ClearCategory : GalleryAction
    
    // 검색
    data class UpdateSearchQuery(val query: String) : GalleryAction
    object PerformSearch : GalleryAction
    object ClearSearch : GalleryAction
    object ToggleSearch : GalleryAction
    
    // 작품 상호작용
    data class ToggleLike(val artworkId: String) : GalleryAction
    data class ToggleBookmark(val artworkId: String) : GalleryAction
    data class ShareArtwork(val artworkId: String) : GalleryAction
    
    // 에러 처리
    data class ShowError(val message: String) : GalleryAction
    object ClearError : GalleryAction
}

