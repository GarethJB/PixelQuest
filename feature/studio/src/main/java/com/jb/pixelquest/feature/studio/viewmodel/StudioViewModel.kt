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

/**
 * Studio 화면 ViewModel
 * Orbit MVI 패턴 사용
 */
@HiltViewModel
class StudioViewModel @Inject constructor(
    // TODO: UseCase 주입
    // private val getRecentWorksUseCase: GetRecentWorksUseCase,
    // private val getTemplatesUseCase: GetTemplatesUseCase,
    // private val getPalettesUseCase: GetPalettesUseCase,
    // private val getBrushesUseCase: GetBrushesUseCase,
    // private val deleteRecentWorkUseCase: DeleteRecentWorkUseCase,
) : ContainerHost<StudioUiState, StudioSideEffect>, ViewModel() {

    override val container = container<StudioUiState, StudioSideEffect>(
        StudioUiState()
    ) {
        // 초기 데이터 로드
        loadInitialData()
    }

    /**
     * 초기 데이터 로드
     */
    private fun loadInitialData() = intent {
        reduce {
            state.copy(isLoading = true)
        }

        // TODO: UseCase를 통한 데이터 로드
        // val recentWorks = getRecentWorksUseCase()
        // val templates = getTemplatesUseCase()
        // val palettes = getPalettesUseCase()
        // val brushes = getBrushesUseCase()

        // 임시 데이터 (개발용)
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

    /**
     * 액션 처리
     */
    fun handleAction(action: StudioAction) = intent {
        when (action) {
            is StudioAction.SelectRecentWork -> {
                // 에디터로 이동 (기존 캔버스 로드)
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
                // TODO: UseCase를 통한 삭제
                // deleteRecentWorkUseCase(action.workId)
                
                reduce {
                    state.copy(
                        recentWorks = state.recentWorks.filter { it.id != action.workId }
                    )
                }
                postSideEffect(StudioSideEffect.ShowSnackbar("작업이 삭제되었습니다"))
            }

            is StudioAction.SelectTemplate -> {
                // 템플릿 선택 시 에디터로 이동
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

                // TODO: UseCase를 통한 최근 작업 새로고침
                // val recentWorks = getRecentWorksUseCase()

                reduce {
                    state.copy(
                        isLoading = false,
                        recentWorks = emptyList() // TODO: 실제 데이터로 교체
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

    /**
     * 새 캔버스 액션 처리
     * NewCanvasState를 StudioViewModel에서 통합 관리
     */
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
                // 캔버스 크기 계산
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

                // TODO: UseCase를 통한 캔버스 생성
                // val canvas = createCanvasUseCase(
                //     size = canvasSize,
                //     backgroundColor = state.newCanvasState.backgroundColor,
                //     template = state.newCanvasState.selectedTemplate
                // )

                // 에디터로 이동 (네비게이션 파라미터로 상태 전달)
                postSideEffect(
                    StudioSideEffect.NavigateToEditor(
                        canvasId = null,
                        canvasSize = canvasSize,
                        backgroundColor = state.newCanvasState.backgroundColor,
                        template = state.newCanvasState.selectedTemplate
                    )
                )

                // 다이얼로그 닫기 및 상태 초기화
                reduce {
                    state.copy(
                        showNewCanvasDialog = false,
                        newCanvasState = NewCanvasState()
                    )
                }
            }

            is NewCanvasAction.Cancel -> {
                // 다이얼로그 닫기 및 상태 초기화
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

