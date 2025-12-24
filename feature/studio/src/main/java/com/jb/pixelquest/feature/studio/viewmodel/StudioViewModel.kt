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
    // private val getRecentWorksUseCase: GetRecentWorksUseCase,
    // private val getTemplatesUseCase: GetTemplatesUseCase,
    // private val getPalettesUseCase: GetPalettesUseCase,
    // private val getBrushesUseCase: GetBrushesUseCase,
    // private val deleteRecentWorkUseCase: DeleteRecentWorkUseCase,
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

        // val recentWorks = getRecentWorksUseCase()
        // val templates = getTemplatesUseCase()
        // val palettes = getPalettesUseCase()
        // val brushes = getBrushesUseCase()

        val mockRecentWorks = emptyList<RecentWork>()
        val mockTemplates = emptyList<Template>()
        val mockPalettes = emptyList<Palette>()
        val mockBrushes = emptyList<Brush>()

        reduce {
            state.copy(
                isLoading = false,
                recentWorks = mockRecentWorks,
                templates = mockTemplates,
                palettes = mockPalettes,
                brushes = mockBrushes
            )
        }
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
                // deleteRecentWorkUseCase(action.workId)
                
                reduce {
                    state.copy(
                        recentWorks = state.recentWorks.filter { it.id != action.workId }
                    )
                }
                postSideEffect(StudioSideEffect.ShowSnackbar(""))
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

                // val recentWorks = getRecentWorksUseCase()

                reduce {
                    state.copy(
                        isLoading = false,
                        recentWorks = emptyList()
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

