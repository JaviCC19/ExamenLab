package com.uvg.javiercc.labclase12.presentation.Screen2

import com.uvg.javiercc.labclase12.domain.Model.Assets

data class CryptoProfileState(
    val isLoading: Boolean = true,
    val asset: Assets? = null,
    val isGenericError: Boolean = false,
    val isOffline: Boolean = false,
    val lastUpdate: String = ""
)
