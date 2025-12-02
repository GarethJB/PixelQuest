package com.jb.pixelquest.feature.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jb.pixelquest.feature.home.model.HomeHighlight
import com.jb.pixelquest.feature.home.model.HomeUiState
import com.jb.pixelquest.presentation.resources.R

@Composable
fun HomeRoute(
    viewModel: HomeViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onRefreshHighlights = viewModel::refreshHighlights
    )
}

@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onRefreshHighlights: () -> Unit,
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
                    style = MaterialTheme.typography.displaySmall,
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
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = spacingLarge,
                top = padding.calculateTopPadding(),
                end = spacingLarge,
                bottom = spacingLarge + padding.calculateBottomPadding()
            ),
            verticalArrangement = Arrangement.spacedBy(spacingMedium)
        ) {
            item {
                FilledTonalButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onRefreshHighlights
                ) {
                    Text(text = stringResource(id = R.string.home_refresh_highlights))
                }
            }

            items(uiState.highlights) { highlight ->
                HighlightCard(highlight = highlight)
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
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(
                horizontal = dimensionResource(id = R.dimen.spacing_medium),
                vertical = dimensionResource(id = R.dimen.spacing_small)
            )
        ) {
            Text(
                text = highlight.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = highlight.description,
                style = MaterialTheme.typography.bodyMedium,
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
            welcomeMessage = "픽셀로 기록하는 나만의 여정",
            highlights = listOf(
                HomeHighlight("오늘의 퀘스트", "랜덤 스폿 3곳 방문"),
                HomeHighlight("히든 챌린지", "비밀 캐릭터 수집"),
                HomeHighlight("주간 랭킹", "상위 10% 진입 도전")
            )
        ),
        onRefreshHighlights = {}
    )
}

