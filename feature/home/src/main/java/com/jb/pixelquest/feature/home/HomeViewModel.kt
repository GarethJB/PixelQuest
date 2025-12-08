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
            title = "?�늘???�스??",
            description = "?�시 곳곳???�어�??��????�집?�보?�요."
        ),
        HomeHighlight(
            title = "주간 ??��",
            description = "?�번 �?챌린지?�서 ?�위 10%???�전?�세??"
        ),
        HomeHighlight(
            title = "?�로???�범",
            description = "겨울 ?�즌 ?�마 ?�범??추�??�었?�니??"
        )
    )

    private val _uiState = MutableStateFlow(
        HomeUiState(
            welcomeTitle = "PixelQuest",
            welcomeMessage = "?��?�?기록?�는 ?�만???�행",
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


