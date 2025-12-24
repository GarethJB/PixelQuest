package com.jb.pixelquest.feature.studio.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.feature.studio.model.Template
import com.jb.pixelquest.shared.presentation.resources.R

@Composable
fun TemplateList(
    templates: List<Template>,
    onTemplateSelected: (Template) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.templates),
            style = androidx.compose.material3.MaterialTheme.typography.titleMedium
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(4.dp),
            modifier = Modifier.heightIn(max = 400.dp)
        ) {
            items(
                items = templates,
                key = { it.id }
            ) { template ->
                TemplateCard(
                    template = template,
                    onClick = { onTemplateSelected(template) }
                )
            }
        }
    }
}

