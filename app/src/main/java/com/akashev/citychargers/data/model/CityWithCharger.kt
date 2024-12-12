package com.akashev.citychargers.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityWithCharger(
    @SerialName("city")
    val cityName: String,
    val charger: Charger
)
