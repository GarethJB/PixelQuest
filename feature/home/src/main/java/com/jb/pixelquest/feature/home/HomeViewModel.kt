package com.jb.pixelquest.feature.home

import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import com.jb.pixelquest.feature.home.model.Canvas
import com.jb.pixelquest.feature.home.model.HomeHighlight
import com.jb.pixelquest.feature.home.model.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val placeholderHighlights = listOf(
        HomeHighlight(
            title = "오늘의 챌린지",
            description = "비밀 곳곳에 숨어있는 캐릭터를 찾아보세요."
        ),
        HomeHighlight(
            title = "주간 랭킹",
            description = "이번 주 챌린지에서 상위 10%에 도전하세요!"
        ),
        HomeHighlight(
            title = "겨울 테마",
            description = "겨울 시즌의 새로운 테마를 추천합니다"
        )
    )

    // 플레이스홀더 캔버스 데이터 (실제로는 Repository에서 가져와야 함)
    private val placeholderCanvases = listOf(
        Canvas(
            id = "1",
            name = "My Canvas 1",
            lastModified = System.currentTimeMillis(),
            canvasSize = IntSize(32, 32)
        ),
        Canvas(
            id = "2",
            name = "My Canvas 2",
            lastModified = System.currentTimeMillis() - 86400000,
            canvasSize = IntSize(64, 64)
        ),
        Canvas(
            id = "3",
            name = "My Canvas 3",
            lastModified = System.currentTimeMillis() - 172800000,
            canvasSize = IntSize(16, 16)
        )
    )

    private val _uiState = MutableStateFlow(
        HomeUiState(
            welcomeTitle = "PixelQuest",
            welcomeMessage = "당신의 기록을 만드는 공간",
            highlights = placeholderHighlights,
            canvases = placeholderCanvases
        )
    )
    val uiState: StateFlow<HomeUiState> = _uiState

    fun refreshHighlights() {
        _uiState.update { current ->
            current.copy(highlights = current.highlights.shuffled())
        }
    }

    fun onCanvasClick(canvas: Canvas) {
        // TODO: 캔버스 클릭 시 상세 화면으로 이동 또는 편집 화면 열기
    }

    fun onDecorateWorkshop() {
        // TODO: 공방 꾸미기 화면으로 이동
    }
}
