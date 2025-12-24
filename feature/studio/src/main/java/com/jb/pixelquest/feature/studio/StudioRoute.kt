package com.jb.pixelquest.feature.studio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jb.pixelquest.feature.studio.ui.screen.StudioScreen
import com.jb.pixelquest.feature.studio.viewmodel.StudioViewModel

@Composable
fun StudioRoute(
    viewModel: StudioViewModel = hiltViewModel()
) {
    val uiState by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    StudioScreen(
        uiState = uiState,
        onAction = viewModel::handleAction,
        onNewCanvasAction = viewModel::handleNewCanvasAction
    )
}


