package com.uvg.javiercc.labclase12.data.network.Dto

import kotlinx.serialization.Serializable

@Serializable
data class EntryProfileDto(
    val data: EntryDto,
    val message: String,
    val status: Int
)
