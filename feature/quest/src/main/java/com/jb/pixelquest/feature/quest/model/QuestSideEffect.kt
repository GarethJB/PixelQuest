package com.jb.pixelquest.feature.quest.model

sealed interface QuestSideEffect {

    data class NavigateToStudio(
        val questId: String,
        val requirements: QuestRequirements,
        val theme: String
    ) : QuestSideEffect

    data class ShowSnackbar(val message: String) : QuestSideEffect
}

