package com.uvg.javiercc.labclase12.domain.Model

import com.uvg.javiercc.labclase12.domain.network.util.Error


enum class DataError: Error {
    NO_INTERNET,
    GENERIC_ERROR
}