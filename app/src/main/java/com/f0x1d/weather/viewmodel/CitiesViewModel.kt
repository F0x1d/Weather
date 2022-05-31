package com.f0x1d.weather.viewmodel

import com.f0x1d.weather.network.model.WeatherResponse
import com.f0x1d.weather.repository.WeatherRepository
import com.f0x1d.weather.viewmodel.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CitiesViewModel @Inject constructor(private val weatherRepository: WeatherRepository): BaseViewModel<List<WeatherResponse>>() {
    fun search(q: String?) {
        if (q == null || q.isEmpty()) return

        weatherRepository.weather(q).subscribeAndPost { listOf(it) }
    }
}