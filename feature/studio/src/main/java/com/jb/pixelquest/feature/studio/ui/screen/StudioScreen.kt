package com.jb.pixelquest.feature.studio.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.feature.studio.model.*
import com.jb.pixelquest.feature.studio.ui.component.*
import com.jb.pixelquest.feature.studio.ui.dialog.NewCanvasDialog
import com.jb.pixelquest.presentation.component.ScreenHeader
import com.jb.pixelquest.shared.presentation.resources.R

/**
 * Studio 메인 ?�면
 * State Hoisting ?�턴: ?�태???�위?�서 관리하�? ?�션�??�달받음
 */
@Composable
fun StudioScreen(
    uiState: StudioUiState,
    onAction: (StudioAction) -> Unit,
    onNewCanvasAction: (NewCanvasAction) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            ScreenHeader(titleResId = R.string.studio_title)
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // ??캔버??만들�?버튼
            item {
                NewCanvasButton(
                    onClick = { onAction(StudioAction.ShowNewCanvasDialog) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // 최근 ?�업 ?�션
            if (uiState.recentWorks.isNotEmpty()) {
                item {
                    RecentWorkSection(
                        recentWorks = uiState.recentWorks,
                        onWorkSelected = { work ->
                            onAction(StudioAction.SelectRecentWork(work))
                        },
                        onWorkDeleted = { workId ->
                            onAction(StudioAction.DeleteRecentWork(workId))
                        }
                    )
                }
            } else {
                item {
                    EmptyRecentWorksMessage()
                }
            }

            // ?�플�??�셋 ?�션
            item {
                AssetCategoryTabs(
                    selectedCategory = uiState.selectedCategory,
                    onCategorySelected = { category ->
                        onAction(StudioAction.SelectCategory(category))
                    }
                )
            }

            when (uiState.selectedCategory) {
                AssetCategory.TEMPLATE -> {
                    item {
                        TemplateList(
                            templates = uiState.templates,
                            onTemplateSelected = { template ->
                                onAction(StudioAction.SelectTemplate(template))
                            }
                        )
                    }
                }
                AssetCategory.PALETTE -> {
                    item {
                        PaletteList(
                            palettes = uiState.palettes,
                            onPaletteSelected = { palette ->
                                // ?�레???�택 ?�션 (?�디?�로 ?�달)
                            }
                        )
                    }
                }
                AssetCategory.BRUSH -> {
                    item {
                        BrushList(
                            brushes = uiState.brushes,
                            onBrushSelected = { brush ->
                                // 브러???�택 ?�션 (?�디?�로 ?�달)
                            }
                        )
                    }
                }
                null -> {
                    // 기본: ?�플�??�시
                    item {
                        TemplateList(
                            templates = uiState.templates,
                            onTemplateSelected = { template ->
                                onAction(StudioAction.SelectTemplate(template))
                            }
                        )
                    }
                }
            }
        }
    }

    // ??캔버???�이?�로�?
    if (uiState.showNewCanvasDialog) {
        NewCanvasDialog(
            state = uiState.newCanvasState,
            onAction = onNewCanvasAction,
            onDismiss = { onAction(StudioAction.HideNewCanvasDialog) }
        )
    }
}

@Composable
private fun EmptyRecentWorksMessage() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Text(
            text = stringResource(id = R.string.no_recent_works),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

