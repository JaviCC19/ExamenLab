package com.uvg.javiercc.labclase12.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.uvg.javiercc.labclase12.presentation.Screen1.CryptoListDestination
import com.uvg.javiercc.labclase12.presentation.Screen1.assetList
import com.uvg.javiercc.labclase12.presentation.Screen2.assetProfile
import com.uvg.javiercc.labclase12.presentation.Screen2.navigateToAssetProfile

@Composable
fun CryptoNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = CryptoListDestination,
        modifier = modifier
    ) {
        assetList(navigateToAssetProfile = { navController.navigateToAssetProfile(it) })
        assetProfile(onNavigateBack = navController::navigateUp)
    }
}