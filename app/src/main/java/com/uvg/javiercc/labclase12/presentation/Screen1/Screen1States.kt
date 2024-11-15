package com.uvg.javiercc.labclase12.presentation.Screen1

import com.uvg.javiercc.labclase12.domain.Model.Assets

data class AssetListState(
    val isLoading: Boolean = true,
    val data: List<Assets> = emptyList(),
    val isGenericError: Boolean = false,
    val noInternetConnection: Boolean = false
)
