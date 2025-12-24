package com.jb.pixelquest.feature.studio.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.feature.studio.model.AssetCategory
import com.jb.pixelquest.feature.studio.model.NewCanvasAction
import com.jb.pixelquest.feature.studio.model.StudioAction
import com.jb.pixelquest.feature.studio.model.StudioUiState
import com.jb.pixelquest.feature.studio.ui.component.AssetCategoryTabs
import com.jb.pixelquest.feature.studio.ui.component.BrushList
import com.jb.pixelquest.feature.studio.ui.component.NewCanvasButton
import com.jb.pixelquest.feature.studio.ui.component.PaletteList
import com.jb.pixelquest.feature.studio.ui.component.RecentWorkSection
import com.jb.pixelquest.feature.studio.ui.component.TemplateList
import com.jb.pixelquest.feature.studio.ui.dialog.NewCanvasDialog
import com.jb.pixelquest.presentation.component.ScreenHeader
import com.jb.pixelquest.shared.presentation.resources.R

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
            item {
                NewCanvasButton(
                    onClick = { onAction(StudioAction.ShowNewCanvasDialog) },
                    modifier = Modifier.fillMaxWidth()
                )
            }

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

                            }
                        )
                    }
                }
                AssetCategory.BRUSH -> {
                    item {
                        BrushList(
                            brushes = uiState.brushes,
                            onBrushSelected = { brush ->

                            }
                        )
                    }
                }
                null -> {
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

