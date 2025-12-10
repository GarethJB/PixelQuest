package com.jb.pixelquest.feature.quest

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jb.pixelquest.feature.quest.ui.screen.QuestProgressScreen
import com.jb.pixelquest.feature.quest.ui.screen.QuestScreen
import com.jb.pixelquest.feature.quest.viewmodel.QuestProgressViewModel
import com.jb.pixelquest.feature.quest.viewmodel.QuestViewModel

/**
 * Quest Route
 * State Hoisting: ?�태??ViewModel?�서 관리하�? Screen???�달
 */
@Composable
fun QuestRoute(
    navController: NavHostController,
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
 * 진행 ?�황 ?�면
 */
@Composable
fun QuestProgressRoute(
    navController: NavHostController,
    viewModel: QuestProgressViewModel = viewModel()
) {
    val progressState by viewModel.container.stateFlow.collectAsStateWithLifecycle()

    QuestProgressScreen(progressState = progressState)
}
