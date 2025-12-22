package com.jb.pixelquest.feature.studio.viewmodel

import androidx.lifecycle.ViewModel
import com.jb.pixelquest.feature.studio.model.Brush
import com.jb.pixelquest.feature.studio.model.CanvasSize
import com.jb.pixelquest.feature.studio.model.NewCanvasAction
import com.jb.pixelquest.feature.studio.model.NewCanvasState
import com.jb.pixelquest.feature.studio.model.Palette
import com.jb.pixelquest.feature.studio.model.RecentWork
import com.jb.pixelquest.feature.studio.model.StudioAction
import com.jb.pixelquest.feature.studio.model.StudioSideEffect
import com.jb.pixelquest.feature.studio.model.StudioUiState
import com.jb.pixelquest.feature.studio.model.Template
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class StudioViewModel @Inject constructor(
    private val getCanvasesUseCase: com.jb.pixelquest.shared.domain.usecase.studio.GetCanvasesUseCase,
    private val getPalettesUseCase: com.jb.pixelquest.shared.domain.usecase.studio.GetPalettesUseCase,
    private val getBrushesUseCase: com.jb.pixelquest.shared.domain.usecase.studio.GetBrushesUseCase,
    private val createCanvasUseCase: com.jb.pixelquest.shared.domain.usecase.studio.CreateCanvasUseCase,
    private val deleteCanvasUseCase: com.jb.pixelquest.shared.domain.usecase.studio.DeleteCanvasUseCase,
) : ContainerHost<StudioUiState, StudioSideEffect>, ViewModel() {

    override val container = container<StudioUiState, StudioSideEffect>(
        StudioUiState()
    ) {
        loadInitialData()
    }

    private fun loadInitialData() = intent {
        reduce {
            state.copy(isLoading = true)
        }

        val canvasesResult = getCanvasesUseCase()
        val palettesResult = getPalettesUseCase()
        val brushesResult = getBrushesUseCase()

        val recentWorks = canvasesResult.getOrElse { emptyList() }.map { it.toRecentWork() }
        val palettes = palettesResult.getOrElse { emptyList() }.map { it.toPalette() }
        val brushes = brushesResult.getOrElse { emptyList() }.map { it.toBrush() }
        val mockTemplates = emptyList<Template>() // TODO: Template UseCase 추가 필요

        reduce {
            state.copy(
                isLoading = false,
                recentWorks = recentWorks,
                templates = mockTemplates,
                palettes = palettes,
                brushes = brushes,
                error = canvasesResult.exceptionOrNull()?.message
                    ?: palettesResult.exceptionOrNull()?.message
                    ?: brushesResult.exceptionOrNull()?.message
            )
        }
    }
    
    private fun com.jb.pixelquest.shared.domain.model.studio.Canvas.toRecentWork(): RecentWork {
        return RecentWork(
            id = this.id,
            name = this.name,
            thumbnailPath = this.thumbnailPath,
            lastModified = this.lastModified,
            filePath = this.filePath ?: "",
            canvasSize = androidx.compose.ui.unit.IntSize(
                this.canvasSize.width,
                this.canvasSize.height
            )
        )
    }
    
    private fun com.jb.pixelquest.shared.domain.model.studio.Palette.toPalette(): Palette {
        return Palette(
            id = this.id,
            name = this.name,
            colors = this.colors.map { color ->
                androidx.compose.ui.graphics.Color(
                    red = color.red,
                    green = color.green,
                    blue = color.blue,
                    alpha = color.alpha
                )
            },
            isDefault = this.isDefault
        )
    }
    
    private fun com.jb.pixelquest.shared.domain.model.studio.Brush.toBrush(): Brush {
        return Brush(
            id = this.id,
            name = this.name,
            size = this.size,
            shape = when (this.shape) {
                com.jb.pixelquest.shared.domain.model.studio.BrushShape.CIRCLE -> com.jb.pixelquest.feature.studio.model.BrushShape.CIRCLE
                com.jb.pixelquest.shared.domain.model.studio.BrushShape.SQUARE -> com.jb.pixelquest.feature.studio.model.BrushShape.SQUARE
                com.jb.pixelquest.shared.domain.model.studio.BrushShape.DIAMOND -> com.jb.pixelquest.feature.studio.model.BrushShape.DIAMOND
            },
            previewImagePath = this.previewImagePath
        )
    }

    fun handleAction(action: StudioAction) = intent {
        when (action) {
            is StudioAction.SelectRecentWork -> {
                postSideEffect(
                    StudioSideEffect.NavigateToEditor(
                        canvasId = action.work.id,
                        canvasSize = action.work.canvasSize,
                        backgroundColor = null,
                        template = null
                    )
                )
            }

            is StudioAction.DeleteRecentWork -> {
                val result = deleteCanvasUseCase(action.workId)
                result.onSuccess {
                    reduce {
                        state.copy(
                            recentWorks = state.recentWorks.filter { it.id != action.workId }
                        )
                    }
                    postSideEffect(StudioSideEffect.ShowSnackbar("작업을 삭제했습니다"))
                }.onFailure { exception ->
                    postSideEffect(StudioSideEffect.ShowSnackbar(exception.message ?: "작업 삭제에 실패했습니다"))
                }
            }

            is StudioAction.SelectTemplate -> {
                postSideEffect(
                    StudioSideEffect.NavigateToEditor(
                        canvasId = null,
                        canvasSize = action.template.canvasSize,
                        backgroundColor = null,
                        template = action.template
                    )
                )
            }

            is StudioAction.SelectCategory -> {
                reduce {
                    state.copy(selectedCategory = action.category)
                }
            }

            is StudioAction.ShowNewCanvasDialog -> {
                reduce {
                    state.copy(showNewCanvasDialog = true)
                }
            }

            is StudioAction.HideNewCanvasDialog -> {
                reduce {
                    state.copy(showNewCanvasDialog = false)
                }
            }

            is StudioAction.RefreshRecentWorks -> {
                reduce {
                    state.copy(isLoading = true)
                }

                val result = getCanvasesUseCase()
                val recentWorks = result.getOrElse { emptyList() }.map { it.toRecentWork() }

                reduce {
                    state.copy(
                        isLoading = false,
                        recentWorks = recentWorks,
                        error = result.exceptionOrNull()?.message
                    )
                }
            }

            is StudioAction.ShowError -> {
                reduce {
                    state.copy(error = action.message)
                }
                postSideEffect(StudioSideEffect.ShowSnackbar(action.message))
            }

            is StudioAction.ClearError -> {
                reduce {
                    state.copy(error = null)
                }
            }

            is StudioAction.ShowEditor -> {
                reduce {
                    state.copy(showEditor = true)
                }
            }

            is StudioAction.HideEditor -> {
                reduce {
                    state.copy(showEditor = false)
                }
            }
        }
    }

    fun handleNewCanvasAction(action: NewCanvasAction) = intent {
        when (action) {
            is NewCanvasAction.SelectSize -> {
                reduce {
                    state.copy(
                        newCanvasState = state.newCanvasState.copy(selectedSize = action.size)
                    )
                }
            }

            is NewCanvasAction.SetCustomWidth -> {
                reduce {
                    state.copy(
                        newCanvasState = state.newCanvasState.copy(customWidth = action.width)
                    )
                }
            }

            is NewCanvasAction.SetCustomHeight -> {
                reduce {
                    state.copy(
                        newCanvasState = state.newCanvasState.copy(customHeight = action.height)
                    )
                }
            }

            is NewCanvasAction.SetBackgroundColor -> {
                reduce {
                    state.copy(
                        newCanvasState = state.newCanvasState.copy(backgroundColor = action.color)
                    )
                }
            }

            is NewCanvasAction.SelectTemplate -> {
                reduce {
                    state.copy(
                        newCanvasState = state.newCanvasState.copy(selectedTemplate = action.template)
                    )
                }
            }

            is NewCanvasAction.ShowSizeDialog -> {
                reduce {
                    state.copy(
                        newCanvasState = state.newCanvasState.copy(showSizeDialog = true)
                    )
                }
            }

            is NewCanvasAction.HideSizeDialog -> {
                reduce {
                    state.copy(
                        newCanvasState = state.newCanvasState.copy(showSizeDialog = false)
                    )
                }
            }

            is NewCanvasAction.ShowTemplateSelector -> {
                reduce {
                    state.copy(
                        newCanvasState = state.newCanvasState.copy(showTemplateSelector = true)
                    )
                }
            }

            is NewCanvasAction.HideTemplateSelector -> {
                reduce {
                    state.copy(
                        newCanvasState = state.newCanvasState.copy(showTemplateSelector = false)
                    )
                }
            }

            is NewCanvasAction.CreateCanvas -> {
                val canvasSize = when (state.newCanvasState.selectedSize) {
                    CanvasSize.CUSTOM -> {
                        val width = state.newCanvasState.customWidth.toIntOrNull() ?: 32
                        val height = state.newCanvasState.customHeight.toIntOrNull() ?: 32
                        androidx.compose.ui.unit.IntSize(
                            width.coerceIn(8, 512),
                            height.coerceIn(8, 512)
                        )
                    }
                    else -> state.newCanvasState.selectedSize.size
                }

                // val canvas = createCanvasUseCase(
                //     size = canvasSize,
                //     backgroundColor = state.newCanvasState.backgroundColor,
                //     template = state.newCanvasState.selectedTemplate
                // )

                postSideEffect(
                    StudioSideEffect.NavigateToEditor(
                        canvasId = null,
                        canvasSize = canvasSize,
                        backgroundColor = state.newCanvasState.backgroundColor,
                        template = state.newCanvasState.selectedTemplate
                    )
                )

                reduce {
                    state.copy(
                        showNewCanvasDialog = false,
                        newCanvasState = NewCanvasState()
                    )
                }
            }

            is NewCanvasAction.Cancel -> {
                reduce {
                    state.copy(
                        showNewCanvasDialog = false,
                        newCanvasState = NewCanvasState()
                    )
                }
            }
        }
    }
}

