package com.jb.pixelquest.presentation.navigation

/**
 * 앱의 모든 네비게이션 Route를 정의하는 sealed class
 */
sealed class Route(val route: String) {
    // Bottom Navigation 화면들
    object Studio : Route("studio")
    object Quest : Route("quest")
    object Gallery : Route("gallery")
    object MyPage : Route("mypage")
    
    companion object {
        // Deep Link 기본 URL
        const val DEEP_LINK_SCHEME = "pixelquest"
        const val DEEP_LINK_HOST = "pixelquest.com"
        
        // Deep Link 패턴
        fun getDeepLinkPattern(route: String): String {
            return "$DEEP_LINK_SCHEME://$DEEP_LINK_HOST/$route"
        }
    }
}

