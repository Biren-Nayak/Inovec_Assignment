package com.example.inovecassignment.api

import com.example.inovecassignment.constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object OpenWeatherAPIService {
    val openWeatherAPI: OpenWeatherAPI by lazy {
        retrofit.create(OpenWeatherAPI::class.java)
    }
}

val retrofit: Retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
