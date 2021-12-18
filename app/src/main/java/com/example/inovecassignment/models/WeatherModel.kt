package com.example.inovecassignment.models


data class WeatherModel(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<ListItem>,
    val message: Int
)