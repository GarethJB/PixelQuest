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
 * State Hoisting: ?ÅÌÉú??ViewModel?êÏÑú Í¥ÄÎ¶¨ÌïòÍ≥? Screen???ÑÎã¨
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
 * ÏßÑÌñâ ?ÅÌô© ?îÎ©¥
 */
@Composable
fun QuestProgressRoute(
    viewModel: QuestProgressViewModel = viewModel()
) {
    val progressState by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    QuestProgressScreen(progressState = progressState)
}
