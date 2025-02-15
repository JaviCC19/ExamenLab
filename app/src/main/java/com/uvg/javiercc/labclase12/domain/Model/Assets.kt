package com.uvg.javiercc.labclase12.domain.Model

import com.uvg.javiercc.labclase12.data.Local.AssetEntity

data class Assets(
    val id: String,
    val name: String,
    val symbol: String,
    val priceUsd: String,
    val changePercent24Hr: String,
    val supply: String?,
    val maxSupply: String?,
    val marketCapUsd: String?
) {
    fun mapToAssetEntity(): AssetEntity {
        return AssetEntity(
            id = this.id,
            name = this.name,
            symbol = this.symbol,
            priceUsd = this.priceUsd,
            changePercent24Hr = this.changePercent24Hr,
            supply = this.supply,
            maxSupply = this.maxSupply,
            marketCapUsd = this.marketCapUsd
        )
    }


}
