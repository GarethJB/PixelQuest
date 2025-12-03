package com.jb.pixelquest.feature.studio.ui.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.feature.studio.model.*
import com.jb.pixelquest.presentation.resources.R

/**
 * 새 캔버스 생성 다이얼로그
 * State Hoisting: 상태는 상위에서 관리하고, 액션만 전달받음
 */
@Composable
fun NewCanvasDialog(
    state: NewCanvasState,
    onAction: (NewCanvasAction) -> Unit,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(text = stringResource(id = R.string.create_new_canvas))
        },
        text = {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // 캔버스 크기 선택
                CanvasSizeSelector(
                    selectedSize = state.selectedSize,
                    customWidth = state.customWidth,
                    customHeight = state.customHeight,
                    onSizeSelected = { size ->
                        onAction(NewCanvasAction.SelectSize(size))
                    },
                    onCustomWidthChanged = { width ->
                        onAction(NewCanvasAction.SetCustomWidth(width))
                    },
                    onCustomHeightChanged = { height ->
                        onAction(NewCanvasAction.SetCustomHeight(height))
                    }
                )

                // 배경색 선택
                BackgroundColorSelector(
                    selectedColor = state.backgroundColor,
                    onColorSelected = { color ->
                        onAction(NewCanvasAction.SetBackgroundColor(color))
                    }
                )

                // 템플릿 선택 (선택사항)
                if (state.selectedTemplate != null) {
                    Text(
                        text = "Selected: ${state.selectedTemplate.name}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = { onAction(NewCanvasAction.CreateCanvas) }
            ) {
                Text(stringResource(id = R.string.confirm))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(id = R.string.cancel))
            }
        }
    )
}

@Composable
private fun CanvasSizeSelector(
    selectedSize: CanvasSize,
    customWidth: String,
    customHeight: String,
    onSizeSelected: (CanvasSize) -> Unit,
    onCustomWidthChanged: (String) -> Unit,
    onCustomHeightChanged: (String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.canvas_size),
            style = MaterialTheme.typography.titleSmall
        )

        // 프리셋 크기 선택
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            CanvasSize.values().forEach { size ->
                if (size != CanvasSize.CUSTOM) {
                    FilterChip(
                        selected = selectedSize == size,
                        onClick = { onSizeSelected(size) },
                        label = { Text(size.displayName) }
                    )
                }
            }
        }

        // 커스텀 크기 입력
        if (selectedSize == CanvasSize.CUSTOM) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = customWidth,
                    onValueChange = onCustomWidthChanged,
                    label = { Text("Width") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                OutlinedTextField(
                    value = customHeight,
                    onValueChange = onCustomHeightChanged,
                    label = { Text("Height") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }
        }
    }
}

@Composable
private fun BackgroundColorSelector(
    selectedColor: Color,
    onColorSelected: (Color) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.background_color),
            style = MaterialTheme.typography.titleSmall
        )

        val colors = listOf(
            Color.White,
            Color.Black,
            Color.Transparent,
            Color(0xFFF5F5F5)
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            colors.forEach { color ->
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(color, CircleShape)
                        .then(
                            if (selectedColor == color) {
                                Modifier.border(
                                    2.dp,
                                    MaterialTheme.colorScheme.primary,
                                    CircleShape
                                )
                            } else {
                                Modifier
                            }
                        )
                        .clickable { onColorSelected(color) }
                )
            }
        }
    }
}

