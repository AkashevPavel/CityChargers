package com.akashev.citychargers.data.model

import kotlinx.serialization.Serializable

@Serializable
data class NetworkResponse<T>(
    val code: Int,
    val data: T? = null
)
