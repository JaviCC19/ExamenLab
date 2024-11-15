package com.uvg.javiercc.labclase12.domain.network

import com.uvg.javiercc.labclase12.data.network.Dto.EntryListDto
import com.uvg.javiercc.labclase12.data.network.Dto.EntryProfileDto
import com.uvg.javiercc.labclase12.domain.network.util.NetworkError
import com.uvg.javiercc.labclase12.domain.network.util.Result

interface AssetsApi {
    suspend fun getAllAssets(): Result<EntryListDto, NetworkError>
    suspend fun getAssetProfile(id: Int): Result<EntryProfileDto, NetworkError>
}