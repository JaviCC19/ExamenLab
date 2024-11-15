package com.uvg.javiercc.labclase12.data.network.Dto

import kotlinx.serialization.Serializable


@Serializable
data class EntryListDto(
    val data: List<EntryDto>,
    val message: String = "",
    val status: Int = 0
)
