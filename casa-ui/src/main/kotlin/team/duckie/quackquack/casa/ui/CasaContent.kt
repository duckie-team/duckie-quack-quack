/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/quack-quack-android/blob/2.x.x/LICENSE
 */

@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package team.duckie.quackquack.casa.ui

import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList
import team.duckie.quackquack.casa.material.CasaModel

@Composable
internal fun CasaContent(
    modifier: Modifier = Modifier,
    models: ImmutableList<CasaModel>,
    selectedDomains: List<String>,
    lazyListState: LazyListState,
    onClick: (model: CasaModel) -> Unit,
) {
    val groupedModels = remember(models) {
        models.groupBy(CasaModel::domain)
    }

    // TODO(3): fading edge
    LazyColumn(
        modifier = modifier,
        state = lazyListState,
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        groupedModels.forEach { (domain, models) ->
            if (selectedDomains.size != 1) {
                @Suppress("RemoveEmptyParenthesesFromLambdaCall")
                stickyHeader(
                    // error: key was already used
                    // key = domain,
                ) {
                    DomainHeader(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = MaterialTheme.colorScheme.background)
                            .padding(horizontal = 16.dp),
                        domain = domain,
                    )
                }
            }

            items(
                items = models,
                key = { model -> model.name },
            ) { model ->
                ModelCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItemPlacement(spring())
                        .padding(horizontal = 16.dp),
                    model = model,
                    onClick = {
                        onClick(model)
                    },
                )
            }
        }
    }
}

@Composable
private fun DomainHeader(
    modifier: Modifier = Modifier,
    domain: String,
) {
    Text(
        modifier = modifier,
        text = domain,
        style = MaterialTheme.typography.titleLarge,
    )
}

@Composable
private fun ModelCard(
    modifier: Modifier = Modifier,
    model: CasaModel,
    onClick: () -> Unit,
) {
    ElevatedCard(
        modifier = modifier,
        onClick = onClick,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = model.name,
                    style = MaterialTheme.typography.titleMedium,
                )
                Icon(
                    imageVector = Icons.Rounded.KeyboardArrowRight,
                    contentDescription = "Forward",
                )
            }
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = model.kdocDefaultSection,
            )
        }
    }
}
