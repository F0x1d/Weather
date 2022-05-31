package com.f0x1d.weather.network.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(@SerializedName("location") val location: WeatherLocation,
                           @SerializedName("current") val current: WeatherCurrent)

data class WeatherLocation(@SerializedName("name") val name: String,
                           @SerializedName("country") val country: String,
                           @SerializedName("localtime") val localTime: String)

data class WeatherCurrent(@SerializedName("temp_c") val temp: Float,
                          @SerializedName("condition") val condition: WeatherCondition)
data class WeatherCondition(@SerializedName("text") val text: String,
                            @SerializedName("icon") val icon: String)