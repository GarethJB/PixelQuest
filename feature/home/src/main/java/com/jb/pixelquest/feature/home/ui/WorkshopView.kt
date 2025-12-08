package com.jb.pixelquest.feature.home.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import com.jb.pixelquest.feature.home.model.Canvas
import com.jb.pixelquest.feature.home.model.Workshop
import com.jb.pixelquest.shared.presentation.resources.R

/**
 * 공방 뷰 (나중에 꾸밀 수 있는 영역, 이젤들을 포함)
 */
@Composable
fun WorkshopView(
    workshop: Workshop,
    canvases: List<Canvas>,
    modifier: Modifier = Modifier,
    onDecorateClick: () -> Unit = {},
    onCanvasClick: (Canvas) -> Unit = {}
) {
    val spacingLarge = dimensionResource(id = R.dimen.spacing_large)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f)
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacingLarge)
        ) {
            // 공간을 채우는 Spacer 역할 (상단과 이젤 사이)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            // 공방 내부 이젤들 (하단)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                EaselGridView(
                    canvases = canvases,
                    onCanvasClick = onCanvasClick
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WorkshopViewPreview() {
    WorkshopView(
        workshop = Workshop(
            name = "My Workshop",
            decorationLevel = 1
        ),
        canvases = listOf(
            Canvas(
                id = "1",
                name = "My Canvas 1",
                lastModified = System.currentTimeMillis(),
                canvasSize = IntSize(32, 32)
            ),
            Canvas(
                id = "2",
                name = "My Canvas 2",
                lastModified = System.currentTimeMillis(),
                canvasSize = IntSize(64, 64)
            )
        )
    )
}
