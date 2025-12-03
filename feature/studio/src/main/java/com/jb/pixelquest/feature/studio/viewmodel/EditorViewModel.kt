package com.jb.pixelquest.feature.studio.viewmodel

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.lifecycle.ViewModel
import com.jb.pixelquest.feature.studio.model.EditorAction
import com.jb.pixelquest.feature.studio.model.EditorSideEffect
import com.jb.pixelquest.feature.studio.model.EditorUiState
import com.jb.pixelquest.feature.studio.model.Template
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

/**
 * 에디터 화면 ViewModel
 * Orbit MVI 패턴 사용
 */
@HiltViewModel
class EditorViewModel @Inject constructor(
    // TODO: UseCase 주입
    // private val saveCanvasUseCase: SaveCanvasUseCase,
    // private val loadCanvasUseCase: LoadCanvasUseCase,
) : ContainerHost<EditorUiState, EditorSideEffect>, ViewModel() {

    // Undo/Redo를 위한 히스토리
    private val undoHistory = mutableListOf<Array<Array<Color>>>()
    private val redoHistory = mutableListOf<Array<Array<Color>>>()
    private var currentHistoryIndex = -1

    override val container = container<EditorUiState, EditorSideEffect>(
        EditorUiState(
            canvasSize = IntSize(32, 32),
            pixels = Array(32) { Array(32) { Color.White } },
            selectedColor = Color.Black,
            showGrid = true,
            zoom = 1f,
            panOffset = Offset.Zero,
            canUndo = false,
            canRedo = false
        )
    ) {
        // 초기 히스토리 저장
        val initialPixels = state.pixels.deepCopy()
        undoHistory.add(initialPixels)
        currentHistoryIndex = 0
    }

    /**
     * 액션 처리
     */
    fun handleAction(action: EditorAction) = intent {
        when (action) {
            is EditorAction.PixelChanged -> {
                val newPixels = state.pixels.copyOf()
                if (action.y in newPixels.indices && action.x in newPixels[action.y].indices) {
                    newPixels[action.y][action.x] = action.color
                    
                    // 히스토리 저장
                    saveToHistory(state.pixels)
                    
                    reduce {
                        state.copy(
                            pixels = newPixels,
                            canUndo = currentHistoryIndex > 0,
                            canRedo = false
                        )
                    }
                }
            }

            is EditorAction.SetSelectedColor -> {
                reduce {
                    state.copy(selectedColor = action.color)
                }
            }

            is EditorAction.SetSelectedBrush -> {
                reduce {
                    state.copy(selectedBrush = action.brush)
                }
            }

            is EditorAction.SetSelectedPalette -> {
                reduce {
                    state.copy(selectedPalette = action.palette)
                }
            }

            is EditorAction.ToggleGrid -> {
                reduce {
                    state.copy(showGrid = !state.showGrid)
                }
            }

            is EditorAction.SetZoom -> {
                reduce {
                    state.copy(zoom = action.zoom.coerceIn(0.5f, 5f))
                }
            }

            is EditorAction.SetPanOffset -> {
                reduce {
                    state.copy(panOffset = action.offset)
                }
            }

            is EditorAction.ClearCanvas -> {
                val clearedPixels = Array(state.canvasSize.height) {
                    Array(state.canvasSize.width) { Color.White }
                }
                
                // 히스토리 저장
                saveToHistory(state.pixels)
                
                reduce {
                    state.copy(
                        pixels = clearedPixels,
                        canUndo = currentHistoryIndex > 0,
                        canRedo = false
                    )
                }
            }

            is EditorAction.Undo -> {
                if (currentHistoryIndex > 0) {
                    redoHistory.add(state.pixels.deepCopy())
                    currentHistoryIndex--
                    val previousPixels = undoHistory[currentHistoryIndex]
                    
                    reduce {
                        state.copy(
                            pixels = previousPixels.deepCopy(),
                            canUndo = currentHistoryIndex > 0,
                            canRedo = redoHistory.isNotEmpty()
                        )
                    }
                }
            }

            is EditorAction.Redo -> {
                if (redoHistory.isNotEmpty()) {
                    undoHistory.add(state.pixels.deepCopy())
                    currentHistoryIndex++
//                    val nextPixels = redoHistory.removeLast()
//
//                    reduce {
//                        state.copy(
//                            pixels = nextPixels.deepCopy(),
//                            canUndo = currentHistoryIndex > 0,
//                            canRedo = redoHistory.isNotEmpty()
//                        )
//                    }
                }
            }

            is EditorAction.SetDrawingState -> {
                reduce {
                    state.copy(isDrawing = action.isDrawing)
                }
            }

            is EditorAction.SaveCanvas -> {
                reduce {
                    state.copy(isDrawing = true)
                }

                // TODO: UseCase를 통한 저장
                // saveCanvasUseCase(state.pixels, state.canvasSize)

                reduce {
                    state.copy(isDrawing = false)
                }
                
                postSideEffect(EditorSideEffect.ShowSnackbar("캔버스가 저장되었습니다"))
            }

            is EditorAction.LoadCanvas -> {
                reduce {
                    state.copy(isDrawing = true)
                }

                // TODO: UseCase를 통한 로드
                // val canvas = loadCanvasUseCase()
                // val loadedPixels = canvas.pixels

                reduce {
                    state.copy(isDrawing = false)
                    // pixels = loadedPixels
                }
            }
        }
    }

    /**
     * 캔버스 초기화 (새 캔버스 또는 기존 캔버스 로드)
     */
    fun initializeCanvas(
        canvasSize: IntSize,
        backgroundColor: Color = Color.White,
        template: Template? = null
    ) = intent {
        val initialPixels = if (template != null) {
            // TODO: 템플릿 로드
            Array(canvasSize.height) { Array(canvasSize.width) { backgroundColor } }
        } else {
            Array(canvasSize.height) { Array(canvasSize.width) { backgroundColor } }
        }

        // 히스토리 초기화
        undoHistory.clear()
        redoHistory.clear()
        currentHistoryIndex = -1
        undoHistory.add(initialPixels.deepCopy())
        currentHistoryIndex = 0

        reduce {
            state.copy(
                canvasSize = canvasSize,
                pixels = initialPixels,
                zoom = 1f,
                panOffset = Offset.Zero,
                canUndo = false,
                canRedo = false
            )
        }
    }

    /**
     * 히스토리에 현재 상태 저장
     */
    private fun saveToHistory(pixels: Array<Array<Color>>) {
        // 현재 상태 이후의 히스토리 제거 (새로운 작업 시작)
        if (currentHistoryIndex < undoHistory.size - 1) {
            undoHistory.removeAll { true }
            redoHistory.clear()
            currentHistoryIndex = -1
        }

        undoHistory.add(pixels.deepCopy())
        currentHistoryIndex++
        redoHistory.clear()

        // 히스토리 크기 제한 (메모리 관리)
        if (undoHistory.size > 50) {
//            undoHistory.removeFirst()
            currentHistoryIndex--
        }
    }


    /**
     * Array 깊은 복사 헬퍼
     */
    private fun Array<Array<Color>>.deepCopy(): Array<Array<Color>> {
        return Array(this.size) { i ->
            Array(this[i].size) { j ->
                this[i][j]
            }
        }
    }
}

