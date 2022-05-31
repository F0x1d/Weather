package com.f0x1d.weather.extensions

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.f0x1d.weather.R
import com.f0x1d.weather.network.model.WeatherResponse

@SuppressLint("SetTextI18n")
fun WeatherResponse.setupViews(conditionImage: ImageView, cityWeatherText: TextView, cityNameText: TextView, countryNameText: TextView) {
    Glide
        .with(conditionImage)
        .load("https://${current.condition.icon.substring(2)}")
        .into(conditionImage)

    cityWeatherText.text = cityWeatherText.context.getString(R.string.temp_placeholder, current.temp.toString())

    cityNameText.text = location.name
    countryNameText.text = location.country
}