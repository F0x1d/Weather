package com.f0x1d.weather.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AppPreferences @Inject constructor(@ApplicationContext private val context: Context) {
    var selectedCity: String?
        get() = preferences.getString("city", null)
        set(value) { preferences.edit().putString("city", value).apply() }

    private val preferences = context.getSharedPreferences("weather_settings", Context.MODE_PRIVATE)
}