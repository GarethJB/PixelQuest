package com.jb.pixelquest.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jb.pixelquest.feature.home.model.Canvas
import com.jb.pixelquest.feature.home.model.HomeHighlight
import com.jb.pixelquest.feature.home.model.HomeUiState
import com.jb.pixelquest.feature.home.ui.WorkshopView
import com.jb.pixelquest.shared.presentation.resources.R

@Composable
fun HomeRoute(
    navController: NavHostController,
    viewModel: HomeViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onRefreshHighlights = viewModel::refreshHighlights,
        onCanvasClick = { canvas ->
            // Studio 화면으로 네비게이션
            navController.navigate("studio")
        },
        onDecorateWorkshop = viewModel::onDecorateWorkshop
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onRefreshHighlights: () -> Unit = {},
    onCanvasClick: (Canvas) -> Unit = {},
    onDecorateWorkshop: () -> Unit = {}
) {
    val spacingMedium = dimensionResource(id = R.dimen.spacing_medium)
    val spacingLarge = dimensionResource(id = R.dimen.spacing_large)

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = spacingLarge,
                        top = spacingLarge,
                        end = spacingLarge,
                        bottom = spacingMedium
                    )
            ) {
                Text(
                    text = uiState.welcomeTitle,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = uiState.welcomeMessage,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(top = spacingMedium)
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = padding.calculateTopPadding(),
                    bottom = padding.calculateBottomPadding()
                )
        ) {
            // 공방 뷰 (화면 가득 채움)
            WorkshopView(
                workshop = uiState.workshop,
                canvases = uiState.canvases,
                modifier = Modifier.fillMaxSize(),
                onDecorateClick = onDecorateWorkshop,
                onCanvasClick = onCanvasClick
            )

            // 하이라이트 섹션 (Workshop 위에 오버레이로 작게 배치)
            if (uiState.highlights.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = spacingLarge,
                            top = spacingLarge,
                            end = spacingLarge
                        ),
                    contentAlignment = androidx.compose.ui.Alignment.TopStart
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(0.5f)
                    ) {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(spacingMedium),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            items(uiState.highlights) { highlight ->
                                HighlightCard(highlight = highlight)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun HighlightCard(
    highlight: HomeHighlight,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.9f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.spacing_medium),
                vertical = dimensionResource(id = R.dimen.spacing_small)
            )
        ) {
            Text(
                text = highlight.title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = highlight.description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = dimensionResource(id = R.dimen.spacing_small))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        uiState = HomeUiState(
            welcomeTitle = "PixelQuest",
            welcomeMessage = "?��?�?기록?�는 ?�만???�정",
            highlights = listOf(
                HomeHighlight("?�든 챌린지", "비�? 캐릭???�집"),
                HomeHighlight("주간 ??��", "?�위 10% 진입 ?�전")
            )
        ),
        onRefreshHighlights = {},
        onCanvasClick = {},
        onDecorateWorkshop = {}
    )
}

