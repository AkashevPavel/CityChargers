package com.akashev.citychargers.data.repository

import com.akashev.citychargers.data.api.CityChargersApi
import com.akashev.citychargers.data.model.Charger
import com.akashev.citychargers.data.model.City
import com.akashev.citychargers.data.model.NetworkRequest

interface CityChargersRepository {

    suspend fun getAllCities(): List<City>

    suspend fun getChargersForCity(cityName: String): List<Charger>
}

class CityChargersRepositoryImpl(
    private val api: CityChargersApi
) : CityChargersRepository {

    override suspend fun getAllCities(): List<City> {
        val response = api.getCitiesWithChargers(NetworkRequest())
        return response.data?.toSet()?.map { City(it.cityName) } ?: emptyList()
    }

    override suspend fun getChargersForCity(cityName: String): List<Charger> {
        val response = api.getCitiesWithChargers(NetworkRequest())
        return response.data?.filter { it.cityName == cityName }
            ?.map { it.charger } ?: emptyList()
    }
}