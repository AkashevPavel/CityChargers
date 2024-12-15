package com.akashev.citychargers

import com.akashev.citychargers.data.api.CityChargersApiImpl
import com.akashev.citychargers.data.model.Charger
import com.akashev.citychargers.data.repository.CityChargersRepositoryImpl
import com.akashev.citychargers.data.serverStub.ServerStub
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class RepositoryTest {

    private val serverStub = ServerStub()
    private val api = CityChargersApiImpl(serverStub)
    private val repo = CityChargersRepositoryImpl(api)

    @Test
    fun `getting correct set of cities`() = runTest {
        val expect = setOf("Moscow", "Saint Petersburg")
        val actual = repo.getAllCityNames()

        assertEquals(expect, actual)
    }

    @Test
    fun `getting correct list of chargers for existing city`() = runTest {
        val expect = listOf(
            Charger("Станция зарядки электромобилей", "Большой просп. Васильевского острова, 68", true),
            Charger("Punkt E", "Малая Монетная ул., 2Г", false)
        )
        val actual = repo.getChargersForCity("Saint Petersburg")

        assertEquals(expect, actual)
    }

    @Test
    fun `getting correct list of chargers for non existing city`() = runTest {
        val expect = emptyList<Charger>()
        val actual = repo.getChargersForCity("Novosibirsk")

        assertEquals(expect, actual)
    }
}