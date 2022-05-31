package com.f0x1d.weather.network

import com.f0x1d.weather.network.model.WeatherResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {
    @GET("current.json")
    fun current(@Query("q") q: String?): Single<WeatherResponse>
}