package com.uvg.javiercc.labclase12.data.repository

import com.uvg.javiercc.labclase12.data.Local.AssetDao
import com.uvg.javiercc.labclase12.data.Local.mapToAssetModel
import com.uvg.javiercc.labclase12.data.network.Dto.mapToAssetEntity
import com.uvg.javiercc.labclase12.data.network.Dto.mapToAssetModel
import com.uvg.javiercc.labclase12.domain.Model.Assets
import com.uvg.javiercc.labclase12.domain.Model.DataError
import com.uvg.javiercc.labclase12.domain.Repository.AssetRepository
import com.uvg.javiercc.labclase12.domain.network.AssetsApi
import com.uvg.javiercc.labclase12.domain.network.util.NetworkError
import com.uvg.javiercc.labclase12.domain.network.util.Result


class AssetRepositoryImpl(
    private val assetApi: AssetsApi,
    private val assetDao: AssetDao
): AssetRepository {
    override suspend fun getAllAssets(): Result<List<Assets>, DataError> {

        when (val result = assetApi.getAllAssets()) {
            is Result.Error -> {
                println(result.error)

                val localMonsters = assetDao.getAssets()
                if (localMonsters.isEmpty()) {
                    if (result.error == NetworkError.NO_INTERNET) {
                        return Result.Error(
                            DataError.NO_INTERNET
                        )
                    }

                    return Result.Error(
                        DataError.GENERIC_ERROR
                    )
                } else {
                    return Result.Success(
                        localMonsters.map { it.mapToAssetModel() }
                    )
                }
            }
            is Result.Success -> {
                val remoteMonsters = result.data.data

                assetDao.insertAssets(
                    remoteMonsters.map { it.mapToAssetEntity() }
                )
                return Result.Success(
                    remoteMonsters.map { it.mapToAssetModel() }
                )
            }
        }
    }

    override suspend fun getOneAsset(id: String): Result<Assets, DataError> {

        val localMonster = assetDao.getAssetById(id)
        return Result.Success(
            localMonster.mapToAssetModel()
        )
    }

    override suspend fun saveAssetOffline(asset: Assets) {
        assetDao.insertAsset(asset.mapToAssetEntity())
    }
}