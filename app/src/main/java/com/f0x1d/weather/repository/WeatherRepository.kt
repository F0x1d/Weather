package com.f0x1d.weather.repository

import com.f0x1d.weather.network.WeatherService
import com.f0x1d.weather.repository.base.BaseRepository
import com.f0x1d.weather.utils.AppPreferences
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherService: WeatherService,
                                            private val appPreferences: AppPreferences): BaseRepository() {
    fun citySaved() = appPreferences.selectedCity != null
    fun clearCity() {
        appPreferences.selectedCity = null
    }

    fun weather() = weatherService.current(appPreferences.selectedCity)
    fun weather(city: String) = weatherService.current(city)
}