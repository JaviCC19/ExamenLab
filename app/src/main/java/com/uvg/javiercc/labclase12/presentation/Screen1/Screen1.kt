package com.uvg.javiercc.labclase12.presentation.Screen1

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.uvg.javiercc.labclase12.R
import com.uvg.javiercc.labclase12.domain.Model.Assets
import com.uvg.javiercc.labclase12.presentation.common.ErrorLayout
import com.uvg.javiercc.labclase12.presentation.common.LoadingLayout

@Composable
fun CryptoListRoute(
    onCryptoClick: (String) -> Unit,
    viewModel: AssetListViewModel = viewModel(factory = AssetListViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CryptoListScreen(
        state = state,
        onRetryClick = viewModel::getAssets,
        onCryptoClick = onCryptoClick,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun CryptoListScreen(
    state: AssetListState,
    onRetryClick: () -> Unit,
    onCryptoClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        when {
            state.isLoading -> {
                LoadingLayout(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.isGenericError -> {
                ErrorLayout(
                    text = stringResource(R.string.error_fetching_data),
                    onRetryClick = onRetryClick,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            state.noInternetConnection -> {
                ErrorLayout(
                    text = stringResource(R.string.no_internect_connection),
                    onRetryClick = onRetryClick,
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            else -> {
                LazyColumn {
                    items(state.data) { asset: Assets ->
                        CryptoItem(
                            crypto = asset,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(64.dp)
                                .clickable { onCryptoClick(asset.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CryptoItem(
    crypto: Assets,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(
                horizontal = 16.dp,
                vertical = 8.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = crypto.symbol,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            Column {
                Text(text = crypto.name, style = MaterialTheme.typography.bodyLarge)
                Text(text = crypto.symbol, style = MaterialTheme.typography.bodyMedium)
            }
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "$${crypto.priceUsd}",
                style = MaterialTheme.typography.bodyLarge
            )
            val changeColor = if (crypto.changePercent24Hr.toFloat() >= 0) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.error
            }
            Text(
                text = "${crypto.changePercent24Hr}%",
                style = MaterialTheme.typography.bodyMedium,
                color = changeColor
            )
        }
    }
}
