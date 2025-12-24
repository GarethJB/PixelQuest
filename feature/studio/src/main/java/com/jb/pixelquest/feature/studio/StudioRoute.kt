package com.jb.pixelquest.feature.studio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jb.pixelquest.feature.studio.ui.screen.StudioScreen
import com.jb.pixelquest.feature.studio.viewmodel.StudioViewModel

/**
 * Studio Route
 * State Hoisting: ?ÅÌÉú??ViewModel?êÏÑú Í¥ÄÎ¶¨ÌïòÍ≥? Screen???ÑÎã¨
 */
@Composable
fun StudioRoute(
    viewModel: StudioViewModel = viewModel()
) {
    val uiState by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    StudioScreen(
        uiState = uiState,
        onAction = viewModel::handleAction,
        onNewCanvasAction = viewModel::handleNewCanvasAction
    )
}


