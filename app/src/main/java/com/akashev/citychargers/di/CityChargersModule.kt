package com.akashev.citychargers.di

import com.akashev.citychargers.data.api.CityChargersApi
import com.akashev.citychargers.data.api.CityChargersApiImpl
import com.akashev.citychargers.data.repository.CityChargersRepository
import com.akashev.citychargers.data.repository.CityChargersRepositoryImpl
import com.akashev.citychargers.data.serverStub.ServerStub
import com.akashev.citychargers.presentation.chargers.ChargersViewModel
import com.akashev.citychargers.presentation.cities.CitiesViewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val cityChargersModule = module {
    singleOf(::CityChargersApiImpl) { bind<CityChargersApi>() }
    singleOf(::CityChargersRepositoryImpl) { bind<CityChargersRepository>() }
    singleOf(::ServerStub)
    viewModelOf(::CitiesViewModel)
    viewModelOf(::ChargersViewModel)
}