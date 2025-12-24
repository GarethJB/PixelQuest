package com.jb.pixelquest.feature.quest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jb.pixelquest.feature.quest.model.QuestAction
import com.jb.pixelquest.feature.quest.ui.screen.QuestProgressScreen
import com.jb.pixelquest.feature.quest.ui.screen.QuestScreen
import com.jb.pixelquest.feature.quest.viewmodel.QuestProgressViewModel
import com.jb.pixelquest.feature.quest.viewmodel.QuestViewModel

@Composable
fun QuestRoute(
    viewModel: QuestViewModel = hiltViewModel()
) {
    val uiState by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    QuestScreen(
        uiState = uiState,
        onAction = viewModel::handleAction
    )
}

@Composable
fun QuestProgressRoute(
    viewModel: QuestProgressViewModel = viewModel()
) {
    val progressState by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    QuestProgressScreen(progressState = progressState)
}
