package com.akashev.citychargers

import com.akashev.citychargers.data.api.CityChargersApiImpl
import com.akashev.citychargers.data.model.Charger
import com.akashev.citychargers.data.model.CityWithCharger
import com.akashev.citychargers.data.model.NetworkRequest
import com.akashev.citychargers.data.model.NetworkResponse
import com.akashev.citychargers.data.serverStub.ServerStub
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.IOException

class ApiTest {
    private val serverStub = ServerStub()
    private val api = CityChargersApiImpl(serverStub)

    @Test
    fun `correctly parsing response with one city`() = runTest {
        val request = NetworkRequest("one")
        val expect = NetworkResponse(200, listOf( CityWithCharger("Moscow",
            Charger("Энергия Москвы", "Измайловское ш., 4А", true))
        ))
        val actual = api.getCitiesWithChargers(request)

        assertEquals(expect, actual)
    }

    @Test
    fun `correctly parsing response with no data`() = runTest {
        val request = NetworkRequest("empty")
        val expect = NetworkResponse<List<CityWithCharger>>(200, emptyList())
        val actual = api.getCitiesWithChargers(request)

        assertEquals(expect, actual)
    }

    @Test
    fun `correctly parsing response with server error`() = runTest {
        val request = NetworkRequest("error")
        val expect = NetworkResponse<List<CityWithCharger>>(500, null)
        val actual = api.getCitiesWithChargers(request)

        assertEquals(expect, actual)
    }

    @Test(expected = IOException::class)
    fun `correctly throwing exception`() = runTest {
        val request = NetworkRequest(null)
        api.getCitiesWithChargers(request)
    }
}