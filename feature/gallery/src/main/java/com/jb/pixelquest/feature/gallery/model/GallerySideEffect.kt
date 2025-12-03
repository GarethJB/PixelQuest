package com.jb.pixelquest.feature.gallery.model

/**
 * Gallery 화면의 사이드 이펙트
 * 네비게이션, 에러 표시 등 UI 이벤트
 */
sealed interface GallerySideEffect {
    /**
     * 사용자 프로필로 이동
     */
    data class NavigateToUserProfile(val userId: String) : GallerySideEffect
    
    /**
     * Studio 에디터로 이동 (작품 편집/복제)
     */
    data class NavigateToStudio(val artworkId: String? = null) : GallerySideEffect
    
    /**
     * 스낵바 표시
     */
    data class ShowSnackbar(val message: String) : GallerySideEffect
    
    /**
     * 작품 공유
     */
    data class ShareArtwork(val artwork: Artwork) : GallerySideEffect
}

