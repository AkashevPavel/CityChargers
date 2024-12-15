package com.akashev.citychargers.presentation.chargers

import androidx.lifecycle.viewModelScope
import com.akashev.citychargers.data.model.Charger
import com.akashev.citychargers.data.repository.CityChargersRepository
import com.akashev.citychargers.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ChargersViewModel(
    private val repository: CityChargersRepository
) : BaseViewModel() {

    private val mutableChargers = MutableStateFlow<List<Charger>>(emptyList())
    val chargers = mutableChargers.asStateFlow()

    fun getChargers(cityName: String) {
        viewModelScope.launch(coroutineExceptionHandler + Dispatchers.IO) {
            mutableIsLoading.update { true }
            val chargers = async {
                repository.getChargersForCity(cityName).sortedBy { it.name }
            }.await()
            mutableIsLoading.update { false }
            mutableChargers.update { chargers }
        }
    }
}