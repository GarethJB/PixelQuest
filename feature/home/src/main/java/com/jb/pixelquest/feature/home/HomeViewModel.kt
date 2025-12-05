package com.jb.pixelquest.feature.home

import androidx.lifecycle.ViewModel
import com.jb.pixelquest.feature.home.model.HomeHighlight
import com.jb.pixelquest.feature.home.model.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val placeholderHighlights = listOf(
        HomeHighlight(
            title = "?¤ëŠ˜???˜ìŠ¤??,
            description = "?„ì‹œ ê³³ê³³???©ì–´ì§??½ì????˜ì§‘?´ë³´?¸ìš”."
        ),
        HomeHighlight(
            title = "ì£¼ê°„ ??‚¹",
            description = "?´ë²ˆ ì£?ì±Œë¦°ì§€?ì„œ ?ìœ„ 10%???„ì „?˜ì„¸??"
        ),
        HomeHighlight(
            title = "?ˆë¡œ???¨ë²”",
            description = "ê²¨ìš¸ ?œì¦Œ ?Œë§ˆ ?¨ë²”??ì¶”ê??˜ì—ˆ?µë‹ˆ??"
        )
    )

    private val _uiState = MutableStateFlow(
        HomeUiState(
            welcomeTitle = "PixelQuest",
            welcomeMessage = "?½ì?ë¡?ê¸°ë¡?˜ëŠ” ?˜ë§Œ???¬í–‰",
            highlights = placeholderHighlights
        )
    )
    val uiState: StateFlow<HomeUiState> = _uiState

    fun refreshHighlights() {
        _uiState.update { current ->
            current.copy(highlights = current.highlights.shuffled())
        }
    }
}


