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
            title = "오늘의 퀘스트",
            description = "도시 곳곳에 흩어진 픽셀을 수집해보세요."
        ),
        HomeHighlight(
            title = "주간 랭킹",
            description = "이번 주 챌린지에서 상위 10%에 도전하세요."
        ),
        HomeHighlight(
            title = "새로운 앨범",
            description = "겨울 시즌 테마 앨범이 추가되었습니다."
        )
    )

    private val _uiState = MutableStateFlow(
        HomeUiState(
            welcomeTitle = "PixelQuest",
            welcomeMessage = "픽셀로 기록하는 나만의 여행",
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

