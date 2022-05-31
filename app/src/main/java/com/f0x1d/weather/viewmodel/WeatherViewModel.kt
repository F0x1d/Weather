package com.f0x1d.weather.viewmodel

import com.f0x1d.weather.network.model.WeatherResponse
import com.f0x1d.weather.repository.WeatherRepository
import com.f0x1d.weather.utils.Event
import com.f0x1d.weather.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository): BaseViewModel<WeatherResponse>() {
    companion object {
        const val EVENT_TYPE_SETUP = "setup"
    }

    init {
        load()
    }

    fun load() {
        if (weatherRepository.citySaved())
            weatherRepository.weather().subscribeAndPost()
        else
            eventsData.value = Event(EVENT_TYPE_SETUP, Unit)
    }

    fun clearSelectedLocation() = weatherRepository.clearCity()
}