package com.akashev.citychargers.data.repository

import com.akashev.citychargers.data.api.CityChargersApi
import com.akashev.citychargers.data.model.Charger
import com.akashev.citychargers.data.model.NetworkRequest

interface CityChargersRepository {

    suspend fun getAllCityNames(): List<String>

    suspend fun getChargersForCity(cityName: String): List<Charger>
}

class CityChargersRepositoryImpl(
    private val api: CityChargersApi
) : CityChargersRepository {

    override suspend fun getAllCityNames(): List<String> {
        val response = api.getCitiesWithChargers(NetworkRequest())
        return response.data?.let {
            it.map { it.cityName }.toSet().toList()
        } ?: emptyList()
    }

    override suspend fun getChargersForCity(cityName: String): List<Charger> {
        val response = api.getCitiesWithChargers(NetworkRequest())
        return response.data?.run {
            filter { it.cityName == cityName }.map { it.charger }
        } ?: emptyList()
    }
}