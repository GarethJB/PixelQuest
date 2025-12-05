package com.jb.pixelquest.feature.quest.model

/**
 * Quest ?”ë©´???¬ì´???´í™??
 * ?¤ë¹„ê²Œì´?? ?ëŸ¬ ?œì‹œ ??UI ?´ë²¤??
 */
sealed interface QuestSideEffect {
    /**
     * Studio ?ë””?°ë¡œ ?´ë™ (?˜ìŠ¤???œì‘)
     */
    data class NavigateToStudio(
        val questId: String,
        val requirements: QuestRequirements,
        val theme: String
    ) : QuestSideEffect
    
    /**
     * ?¤ë‚µë°??œì‹œ
     */
    data class ShowSnackbar(val message: String) : QuestSideEffect
}

