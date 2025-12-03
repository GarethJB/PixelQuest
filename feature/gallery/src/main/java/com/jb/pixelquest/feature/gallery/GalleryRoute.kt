package com.jb.pixelquest.feature.gallery

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jb.pixelquest.feature.gallery.model.GalleryAction
import com.jb.pixelquest.feature.gallery.ui.screen.GalleryScreen
import com.jb.pixelquest.feature.gallery.viewmodel.GalleryViewModel

/**
 * Gallery Route
 * State Hoisting: 상태는 ViewModel에서 관리하고, Screen에 전달
 */
@Composable
fun GalleryRoute(
    viewModel: GalleryViewModel = viewModel()
) {
    val uiState by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    GalleryScreen(
        uiState = uiState,
        onAction = viewModel::handleAction
    )
}
