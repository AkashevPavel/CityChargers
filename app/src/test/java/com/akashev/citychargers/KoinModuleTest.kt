package com.akashev.citychargers

import com.akashev.citychargers.di.cityChargersModule
import org.junit.Test
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.test.KoinTest
import org.koin.test.verify.verify

class KoinModuleTest : KoinTest {

    @OptIn(KoinExperimentalAPI::class)
    @Test
    fun assertOneSecond() {
        cityChargersModule.verify()
    }
}