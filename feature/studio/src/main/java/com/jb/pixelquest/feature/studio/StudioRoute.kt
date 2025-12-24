package com.jb.pixelquest.feature.studio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jb.pixelquest.feature.studio.model.EditorSideEffect
import com.jb.pixelquest.feature.studio.model.StudioAction
import com.jb.pixelquest.feature.studio.model.StudioSideEffect
import com.jb.pixelquest.feature.studio.ui.screen.EditorScreen
import com.jb.pixelquest.feature.studio.ui.screen.StudioScreen
import com.jb.pixelquest.feature.studio.viewmodel.EditorViewModel
import com.jb.pixelquest.feature.studio.viewmodel.StudioViewModel

/**
 * Studio Route
 * State Hoisting: 상태는 ViewModel에서 관리하고 Screen으로 전달
 */
@Composable
fun StudioRoute(
    studioViewModel: StudioViewModel = viewModel(),
    editorViewModel: EditorViewModel = viewModel()
) {
    val studioState by studioViewModel.container.stateFlow.collectAsStateWithLifecycle()
    val editorState by editorViewModel.container.stateFlow.collectAsStateWithLifecycle()

    // SideEffect 처리 - Editor 열기
    LaunchedEffect(Unit) {
        studioViewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is StudioSideEffect.NavigateToEditor -> {
                    val canvasSize = sideEffect.canvasSize ?: androidx.compose.ui.unit.IntSize(32, 32)
                    editorViewModel.initializeCanvas(
                        canvasSize = canvasSize,
                        backgroundColor = sideEffect.backgroundColor ?: androidx.compose.ui.graphics.Color.White,
                        template = sideEffect.template
                    )
                    studioViewModel.handleAction(StudioAction.ShowEditor)
                }
                is StudioSideEffect.ShowSnackbar -> {
                    // TODO: Snackbar 표시
                }
            }
        }
    }

    // Editor 닫기 처리
    LaunchedEffect(Unit) {
        editorViewModel.container.sideEffectFlow.collect { sideEffect ->
            when (sideEffect) {
                is EditorSideEffect.NavigateBack -> {
                    studioViewModel.handleAction(StudioAction.HideEditor)
                }
                is EditorSideEffect.ShowSnackbar -> {
                    // TODO: Snackbar 표시
                }
            }
        }
    }

    if (studioState.showEditor) {
        EditorScreen(
            uiState = editorState,
            onAction = editorViewModel::handleAction,
            onBack = {
                studioViewModel.handleAction(StudioAction.HideEditor)
            }
        )
    } else {
        StudioScreen(
            uiState = studioState,
            onAction = studioViewModel::handleAction,
            onNewCanvasAction = studioViewModel::handleNewCanvasAction
        )
    }
}
