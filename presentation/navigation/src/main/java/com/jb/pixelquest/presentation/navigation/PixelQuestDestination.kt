package com.jb.pixelquest.presentation.navigation

/**
 * 네비게이션 목적지 정의
 * 모든 화면 Route를 enum으로 관리
 */
sealed class PixelQuestDestination(val route: String) {
    object Gallery : PixelQuestDestination("gallery")
    object Studio : PixelQuestDestination("studio")
    object Quest : PixelQuestDestination("quest")
    object QuestProgress : PixelQuestDestination("quest_progress")
    object MyPage : PixelQuestDestination("mypage")
}

