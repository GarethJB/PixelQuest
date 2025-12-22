package com.jb.pixelquest.feature.quest.model

sealed interface QuestAction {

    data class SelectTab(val tab: QuestTab) : QuestAction

    data class SelectQuest(val quest: ChallengeQuest) : QuestAction
    data class StartQuest(val questId: String) : QuestAction
    data class CompleteQuest(val questId: String, val artworkId: String) : QuestAction
    object RefreshQuests : QuestAction
    object HideQuestDetail : QuestAction

    object LoadProgress : QuestAction
    object RefreshProgress : QuestAction

    data class ClaimReward(val rewardId: String) : QuestAction

    data class ShowError(val message: String) : QuestAction
    object ClearError : QuestAction
}

