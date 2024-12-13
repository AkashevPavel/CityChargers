package com.akashev.citychargers.presentation.cities

import androidx.lifecycle.viewModelScope
import com.akashev.citychargers.data.model.City
import com.akashev.citychargers.data.repository.CityChargersRepository
import com.akashev.citychargers.presentation.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

internal class CitiesViewModel(
    private val repository: CityChargersRepository
) : BaseViewModel() {

    init {
        getCities()
    }

    private val mutableUiState = MutableStateFlow(CityUiState())
    val uiState = mutableUiState.asStateFlow()

    override val onCoroutineError: (CoroutineContext, Throwable) -> Unit = { _, throwable ->
        throwable.localizedMessage?.let { message ->
            mutableUiState.update { it.copy(errorMessage = message) }
        }
    }

    fun getCities() {
        viewModelScope.launch(coroutineExceptionHandler + Dispatchers.IO) {
            mutableUiState.update { it.copy(isLoading = true) }
            val cities = async {
                repository.getAllCities()
            }.await()
            mutableUiState.update { it.copy(isLoading = false, cities = cities) }
        }
    }

    fun setSelectedCity(city: City) {
        viewModelScope.launch {
            mutableUiState.update { current ->
                current.copy(selectedCity = city.takeUnless { it == current.selectedCity })
            }
        }
    }
}