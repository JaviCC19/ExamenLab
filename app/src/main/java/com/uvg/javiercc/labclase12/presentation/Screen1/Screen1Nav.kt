package com.uvg.javiercc.labclase12.presentation.Screen1

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data object CryptoListDestination

fun NavGraphBuilder.assetList(
    navigateToAssetProfile: (String) -> Unit
) {
    composable<CryptoListDestination> {
        CryptoListRoute(
            onCryptoClick = navigateToAssetProfile
        )
    }
}