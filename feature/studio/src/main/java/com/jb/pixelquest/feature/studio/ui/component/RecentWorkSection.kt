package com.jb.pixelquest.feature.studio.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jb.pixelquest.feature.studio.model.RecentWork
import com.jb.pixelquest.shared.presentation.resources.R

@Composable
fun RecentWorkSection(
    recentWorks: List<RecentWork>,
    onWorkSelected: (RecentWork) -> Unit,
    onWorkDeleted: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.recent_works),
            style = MaterialTheme.typography.titleMedium
        )
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(
                items = recentWorks,
                key = { it.id }
            ) { work ->
                RecentWorkItem(
                    work = work,
                    onWorkClick = { onWorkSelected(work) },
                    onDeleteClick = { onWorkDeleted(work.id) },
                    modifier = Modifier.width(200.dp)
                )
            }
        }
    }
}

