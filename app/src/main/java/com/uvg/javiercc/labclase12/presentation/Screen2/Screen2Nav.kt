package com.uvg.javiercc.labclase12.presentation.Screen2

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class AssetProfileDestination(
    val id: String
)

fun NavController.navigateToAssetProfile(
    assetId: String
) {
    this.navigate(AssetProfileDestination(
        id = assetId
    ))
}

fun NavGraphBuilder.assetProfile(
    onNavigateBack: () -> Unit
) {
    composable<AssetProfileDestination> {
        AssetProfileRoute(onNavigateBack = onNavigateBack)
    }
}