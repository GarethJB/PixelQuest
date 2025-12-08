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
 * ?�디???�면 ViewModel
 * Orbit MVI ?�턴 ?�용
 */
@HiltViewModel
class EditorViewModel @Inject constructor(
    // TODO: UseCase 주입
    // private val saveCanvasUseCase: SaveCanvasUseCase,
    // private val loadCanvasUseCase: LoadCanvasUseCase,
) : ContainerHost<EditorUiState, EditorSideEffect>, ViewModel() {

    // Undo/Redo�??�한 ?�스?�리
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
        // 초기 ?�스?�리 ?�??
        val initialPixels = state.pixels.deepCopy()
        undoHistory.add(initialPixels)
        currentHistoryIndex = 0
    }

    /**
     * ?�션 처리
     */
    fun handleAction(action: EditorAction) = intent {
        when (action) {
            is EditorAction.PixelChanged -> {
                // 깊은 복사로 픽셀 배열 복사
                val newPixels = state.pixels.deepCopy()
                if (action.y in newPixels.indices && action.x in newPixels[action.y].indices) {
                    newPixels[action.y][action.x] = action.color
                    
                    // ?�스?�리 ?�??
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
                
                // ?�스?�리 ?�??
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

                // TODO: UseCase�??�한 ?�??
                // saveCanvasUseCase(state.pixels, state.canvasSize)

                reduce {
                    state.copy(isDrawing = false)
                }
                
                postSideEffect(EditorSideEffect.ShowSnackbar("캔버?��? ?�?�되?�습?�다"))
            }

            is EditorAction.LoadCanvas -> {
                reduce {
                    state.copy(isDrawing = true)
                }

                // TODO: UseCase�??�한 로드
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
     * 캔버??초기??(??캔버???�는 기존 캔버??로드)
     */
    fun initializeCanvas(
        canvasSize: IntSize,
        backgroundColor: Color = Color.White,
        template: Template? = null
    ) = intent {
        val initialPixels = if (template != null) {
            // TODO: ?�플�?로드
            Array(canvasSize.height) { Array(canvasSize.width) { backgroundColor } }
        } else {
            Array(canvasSize.height) { Array(canvasSize.width) { backgroundColor } }
        }

        // ?�스?�리 초기??
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
     * ?�스?�리???�재 ?�태 ?�??
     */
    private fun saveToHistory(pixels: Array<Array<Color>>) {
        // ?�재 ?�태 ?�후???�스?�리 ?�거 (?�로???�업 ?�작)
        if (currentHistoryIndex < undoHistory.size - 1) {
            undoHistory.removeAll { true }
            redoHistory.clear()
            currentHistoryIndex = -1
        }

        undoHistory.add(pixels.deepCopy())
        currentHistoryIndex++
        redoHistory.clear()

        // ?�스?�리 ?�기 ?�한 (메모�?관�?
        if (undoHistory.size > 50) {
//            undoHistory.removeFirst()
            currentHistoryIndex--
        }
    }


    /**
     * Array 깊�? 복사 ?�퍼
     */
    private fun Array<Array<Color>>.deepCopy(): Array<Array<Color>> {
        return Array(this.size) { i ->
            Array(this[i].size) { j ->
                this[i][j]
            }
        }
    }
}

