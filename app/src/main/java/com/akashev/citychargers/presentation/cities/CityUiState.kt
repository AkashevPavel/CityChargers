package com.akashev.citychargers.presentation.cities

import com.akashev.citychargers.data.model.City

data class CityUiState(
    val cities: List<City> = emptyList(),
    val selectedCity: City? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)