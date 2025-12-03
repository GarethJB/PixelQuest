package com.jb.pixelquest.feature.quest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jb.pixelquest.feature.quest.model.QuestAction
import com.jb.pixelquest.feature.quest.ui.screen.QuestProgressScreen
import com.jb.pixelquest.feature.quest.ui.screen.QuestScreen
import com.jb.pixelquest.feature.quest.viewmodel.QuestProgressViewModel
import com.jb.pixelquest.feature.quest.viewmodel.QuestViewModel

/**
 * Quest Route
 * State Hoisting: 상태는 ViewModel에서 관리하고, Screen에 전달
 */
@Composable
fun QuestRoute(
    viewModel: QuestViewModel = viewModel()
) {
    val uiState by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    QuestScreen(
        uiState = uiState,
        onAction = viewModel::handleAction
    )
}

/**
 * Quest Progress Route
 * 진행 상황 화면
 */
@Composable
fun QuestProgressRoute(
    viewModel: QuestProgressViewModel = viewModel()
) {
    val progressState by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    QuestProgressScreen(progressState = progressState)
}
