package com.akashev.citychargers.presentation.cities

import androidx.lifecycle.viewModelScope
import com.akashev.citychargers.data.repository.CityChargersRepository
import com.akashev.citychargers.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class CitiesViewModel(
    private val repository: CityChargersRepository
) : BaseViewModel() {

    init {
        getCities()
    }

    private val mutableCityNames = MutableStateFlow<List<String>>(emptyList())
    val cityNames = mutableCityNames.asStateFlow()

    private val mutableSelectedCity = MutableStateFlow("")
    val selectedCity = mutableSelectedCity.asStateFlow()

    fun getCities() {
        viewModelScope.launch(coroutineExceptionHandler + Dispatchers.IO) {
            mutableIsLoading.update { true }
            val cities = async {
                delay(1000)
                repository.getAllCityNames()
            }.await()
            mutableIsLoading.update { false }
            mutableCityNames.update { cities }
        }
    }

    fun setSelectedCity(cityName: String) { mutableSelectedCity.update { cityName } }
}