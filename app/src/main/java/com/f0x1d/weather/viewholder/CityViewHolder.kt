package com.f0x1d.weather.viewholder

import com.bumptech.glide.Glide
import com.f0x1d.weather.databinding.ItemCityBinding
import com.f0x1d.weather.extensions.setupViews
import com.f0x1d.weather.network.model.WeatherResponse
import com.f0x1d.weather.viewholder.base.BaseViewHolder

class CityViewHolder(binding: ItemCityBinding): BaseViewHolder<WeatherResponse, ItemCityBinding>(binding) {
    override fun bindTo(data: WeatherResponse) = data.setupViews(
        binding.conditionImage,
        binding.cityWeatherText,
        binding.cityNameText,
        binding.countryNameText
    )

    override fun recycle() = Glide.with(binding.conditionImage).clear(binding.conditionImage)
}