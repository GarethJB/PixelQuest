package com.jb.pixelquest.feature.quest.model

/**
 * Quest 화면의 사용자 액션
 * State Hoisting 패턴을 위해 액션을 명시적으로 정의
 */
sealed interface QuestAction {
    // 탭 전환
    data class SelectTab(val tab: QuestTab) : QuestAction
    
    // 챌린지 퀘스트
    data class SelectQuest(val quest: ChallengeQuest) : QuestAction
    data class StartQuest(val questId: String) : QuestAction
    data class CompleteQuest(val questId: String, val artworkId: String) : QuestAction
    object RefreshQuests : QuestAction
    object HideQuestDetail : QuestAction
    
    // 진행 상황
    object LoadProgress : QuestAction
    object RefreshProgress : QuestAction
    
    // 보상
    data class ClaimReward(val rewardId: String) : QuestAction
    
    // 에러 처리
    data class ShowError(val message: String) : QuestAction
    object ClearError : QuestAction
}

