package com.jb.pixelquest.feature.quest.model

/**
 * Quest 화면의 사이드 이펙트
 * 네비게이션, 에러 표시 등 UI 이벤트
 */
sealed interface QuestSideEffect {
    /**
     * Studio 에디터로 이동 (퀘스트 시작)
     */
    data class NavigateToStudio(
        val questId: String,
        val requirements: QuestRequirements,
        val theme: String
    ) : QuestSideEffect
    
    /**
     * 스낵바 표시
     */
    data class ShowSnackbar(val message: String) : QuestSideEffect
}

