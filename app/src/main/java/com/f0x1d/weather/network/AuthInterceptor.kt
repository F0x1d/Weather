package com.f0x1d.weather.network

import com.f0x1d.weather.BuildConfig
import okhttp3.Interceptor

class AuthInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain) = chain.proceed(
        chain.request().run {
            val newUrl = url
                .newBuilder()
                .addQueryParameter("key", BuildConfig.API_KEY)
                .build()

            newBuilder()
                .url(newUrl)
                .build()
        }
    )
}