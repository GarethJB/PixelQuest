package com.jb.pixelquest.feature.studio.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.feature.studio.model.*
import com.jb.pixelquest.feature.studio.ui.component.*
import com.jb.pixelquest.presentation.component.ScreenHeader
import com.jb.pixelquest.presentation.resources.R

/**
 * 에디터 화면
 * State Hoisting 패턴: 상태는 상위에서 관리하고, 액션만 전달받음
 */
@Composable
fun EditorScreen(
    uiState: EditorUiState,
    onAction: (EditorAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            EditorToolbar(
                showGrid = uiState.showGrid,
                canUndo = uiState.canUndo,
                canRedo = uiState.canRedo,
                onToggleGrid = { onAction(EditorAction.ToggleGrid) },
                onClear = { onAction(EditorAction.ClearCanvas) },
                onUndo = { onAction(EditorAction.Undo) },
                onRedo = { onAction(EditorAction.Redo) },
                onSave = { onAction(EditorAction.SaveCanvas) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // 캔버스 영역
            PixelCanvas(
                canvasSize = uiState.canvasSize,
                pixels = uiState.pixels,
                selectedColor = uiState.selectedColor,
                showGrid = uiState.showGrid,
                zoom = uiState.zoom,
                panOffset = uiState.panOffset,
                onPixelChanged = { x, y, color ->
                    onAction(EditorAction.PixelChanged(x, y, color))
                },
                onZoomChange = { zoom ->
                    onAction(EditorAction.SetZoom(zoom))
                },
                onPanChange = { offset ->
                    onAction(EditorAction.SetPanOffset(offset))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            // 색상 팔레트
            ColorPalette(
                selectedColor = uiState.selectedColor,
                selectedPalette = uiState.selectedPalette,
                onColorSelected = { color ->
                    onAction(EditorAction.SetSelectedColor(color))
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}

@Composable
private fun EditorToolbar(
    showGrid: Boolean,
    canUndo: Boolean,
    canRedo: Boolean,
    onToggleGrid: () -> Unit,
    onClear: () -> Unit,
    onUndo: () -> Unit,
    onRedo: () -> Unit,
    onSave: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            IconButton(onClick = onToggleGrid) {
                Text(if (showGrid) stringResource(id = R.string.grid) else "Grid OFF")
            }
            IconButton(onClick = onClear) {
                Text(stringResource(id = R.string.clear))
            }
            IconButton(onClick = onUndo, enabled = canUndo) {
                Text(stringResource(id = R.string.undo))
            }
            IconButton(onClick = onRedo, enabled = canRedo) {
                Text(stringResource(id = R.string.redo))
            }
        }
        IconButton(onClick = onSave) {
            Text(stringResource(id = R.string.save))
        }
    }
}

