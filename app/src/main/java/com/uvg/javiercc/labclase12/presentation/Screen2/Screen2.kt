package com.uvg.javiercc.labclase12.presentation.Screen2

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.uvg.javiercc.labclase12.presentation.common.LoadingLayout

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AssetProfileRoute(
    onNavigateBack: () -> Unit,
    viewModel: AssetProfileViewModel = viewModel(factory = AssetProfileViewModel.Factory)
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CryptoProfileScreen(
        state = state,
        onNavigateBack = onNavigateBack,
        modifier = Modifier.fillMaxSize(),
        onSaveOffline = viewModel::saveOffline,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CryptoProfileScreen(
    state: CryptoProfileState,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    onSaveOffline: () -> Unit
) {
    Column(modifier = modifier) {
        TopAppBar(
            title = {
                Text("Perfil de Criptomoneda")
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Box(modifier = Modifier.fillMaxSize()) {
            if (state.isLoading) {
                LoadingLayout(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else {
                checkNotNull(state.asset)
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.TopCenter)
                ) {
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.asset.symbol,
                            style = MaterialTheme.typography.displayLarge,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = state.asset.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Precio: $${state.asset.priceUsd}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Cambio (24h): ${state.asset.changePercent24Hr}%",
                        color = if (state.asset.changePercent24Hr.toFloat() >= 0) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.error
                        },
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Supply: ${state.asset.supply}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Máximo Supply: ${state.asset.maxSupply}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Market Cap: $${state.asset.marketCapUsd}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    if (state.isOffline) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = "Última actualización: ${state.lastUpdate}",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = onSaveOffline) {
                        Text("Save Offline")
                    }
                }
            }
        }
    }
}