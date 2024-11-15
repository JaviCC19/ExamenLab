package com.uvg.javiercc.labclase12.domain.Repository


import com.uvg.javiercc.labclase12.domain.Model.Assets
import com.uvg.javiercc.labclase12.domain.Model.DataError
import com.uvg.javiercc.labclase12.domain.network.util.Result

interface AssetRepository {
    suspend fun getAllAssets(): Result<List<Assets>, DataError>
    suspend fun getOneAsset(id: String): Result<Assets, DataError>
    suspend fun saveAssetOffline(asset: Assets)
}