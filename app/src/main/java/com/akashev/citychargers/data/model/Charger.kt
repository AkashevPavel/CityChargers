package com.akashev.citychargers.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Charger(
    val name: String,
    val address: String,
    @SerialName("busy")
    val isBusy: Boolean
)
