package com.f0x1d.weather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.f0x1d.weather.adapter.base.BaseAdapter
import com.f0x1d.weather.databinding.ItemCityBinding
import com.f0x1d.weather.network.model.WeatherResponse
import com.f0x1d.weather.viewholder.CityViewHolder

class CitiesAdapter: BaseAdapter<WeatherResponse, ItemCityBinding>() {
    override fun createViewHolder(inflater: LayoutInflater, parent: ViewGroup) = CityViewHolder(
        ItemCityBinding.inflate(inflater, parent, false)
    )
}