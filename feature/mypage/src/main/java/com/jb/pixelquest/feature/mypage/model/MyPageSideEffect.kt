package com.jb.pixelquest.feature.mypage.model

/**
 * MyPage ?”ë©´???¬ì´???´í™??
 * ?¤ë¹„ê²Œì´?? ?ëŸ¬ ?œì‹œ ??UI ?´ë²¤??
 */
sealed interface MyPageSideEffect {
    /**
     * Studio ?ë””?°ë¡œ ?´ë™ (?‘í’ˆ ?¸ì§‘)
     */
    data class NavigateToStudio(val artworkId: String? = null) : MyPageSideEffect
    
    /**
     * ?‘í’ˆ ?ì„¸ë¡??´ë™
     */
    data class NavigateToArtworkDetail(val artworkId: String) : MyPageSideEffect
    
    /**
     * ?¤ë‚µë°??œì‹œ
     */
    data class ShowSnackbar(val message: String) : MyPageSideEffect
}

