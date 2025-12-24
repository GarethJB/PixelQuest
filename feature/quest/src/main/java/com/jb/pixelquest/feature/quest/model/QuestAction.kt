package com.jb.pixelquest.feature.quest.model

/**
 * Quest ?”ë©´???¬ìš©???¡ì…˜
 * State Hoisting ?¨í„´???„í•´ ?¡ì…˜??ëª…ì‹œ?ìœ¼ë¡??•ì˜
 */
sealed interface QuestAction {
    // ???„í™˜
    data class SelectTab(val tab: QuestTab) : QuestAction
    
    // ì±Œë¦°ì§€ ?˜ìŠ¤??
    data class SelectQuest(val quest: ChallengeQuest) : QuestAction
    data class StartQuest(val questId: String) : QuestAction
    data class CompleteQuest(val questId: String, val artworkId: String) : QuestAction
    object RefreshQuests : QuestAction
    object HideQuestDetail : QuestAction
    
    // ì§„í–‰ ?í™©
    object LoadProgress : QuestAction
    object RefreshProgress : QuestAction
    
    // ë³´ìƒ
    data class ClaimReward(val rewardId: String) : QuestAction
    
    // ?ëŸ¬ ì²˜ë¦¬
    data class ShowError(val message: String) : QuestAction
    object ClearError : QuestAction
}

