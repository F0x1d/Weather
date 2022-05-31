package com.f0x1d.weather.utils

class Event(val type: String, private val data: Any) {
    var isConsumed = false
        private set

    fun <T> consume(): T? {
        if (isConsumed)
            return null

        isConsumed = true
        return data as T
    }
}