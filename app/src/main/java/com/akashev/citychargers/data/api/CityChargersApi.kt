package com.akashev.citychargers.data.api

import com.akashev.citychargers.data.model.CityWithCharger
import com.akashev.citychargers.data.model.NetworkRequest
import com.akashev.citychargers.data.model.NetworkResponse
import com.akashev.citychargers.data.serverStub.ServerStub
import kotlinx.serialization.json.Json

interface CityChargersApi {

    suspend fun getCitiesWithChargers(request: NetworkRequest): NetworkResponse<List<CityWithCharger>>
}

class CityChargersApiImpl(
    private val serverStub: ServerStub
) : CityChargersApi {
    override suspend fun getCitiesWithChargers(request: NetworkRequest): NetworkResponse<List<CityWithCharger>> {
        return serverStub.makeNetworkRequest(request.request).let {
            Json.decodeFromString<NetworkResponse<List<CityWithCharger>>>(it)
        }
    }
}