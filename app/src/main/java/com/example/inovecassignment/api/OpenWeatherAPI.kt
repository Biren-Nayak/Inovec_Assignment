package com.example.inovecassignment.api
import com.example.inovecassignment.api.key.Keys
import com.example.inovecassignment.models.WeatherModel
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherAPI{

    /*
    https://api.openweathermap.org/data/2.5/forecast?q={cityName}&APPID={key}
     */

    @GET("/data/2.5/forecast?")
    suspend fun getCityWeather(
        @Query("q") cityName : String,
        @Query("APPID") key : String = Keys.apikey
    ): WeatherModel
}
