package com.jb.pixelquest.feature.mypage.model

/**
 * MyPage 화면의 사이드 이펙트
 * 네비게이션, 에러 표시 등 UI 이벤트
 */
sealed interface MyPageSideEffect {
    /**
     * Studio 에디터로 이동 (작품 편집)
     */
    data class NavigateToStudio(val artworkId: String? = null) : MyPageSideEffect
    
    /**
     * 작품 상세로 이동
     */
    data class NavigateToArtworkDetail(val artworkId: String) : MyPageSideEffect
    
    /**
     * 스낵바 표시
     */
    data class ShowSnackbar(val message: String) : MyPageSideEffect
}

