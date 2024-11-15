package com.uvg.javiercc.labclase12.data.network

import com.uvg.javiercc.labclase12.data.network.Dto.EntryListDto
import com.uvg.javiercc.labclase12.data.network.Dto.EntryProfileDto
import com.uvg.javiercc.labclase12.domain.network.AssetsApi
import com.uvg.javiercc.labclase12.data.network.Util.safeCall
import com.uvg.javiercc.labclase12.domain.network.util.NetworkError
import com.uvg.javiercc.labclase12.domain.network.util.Result
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class KtorAssetsApi(
    private val httpClient: HttpClient
): AssetsApi {
    override suspend fun getAllAssets(): Result<EntryListDto, NetworkError> {
        return safeCall<EntryListDto> {
            httpClient.get(
                "https://api.coincap.io/v2/assets/"
            )
        }
    }

    override suspend fun getAssetProfile(id: Int): Result<EntryProfileDto, NetworkError> {
        return safeCall<EntryProfileDto> {
            httpClient.get(
                "https://api.coincap.io/v2/assets/bitcoin/{id}"
            )
        }
    }
}