package com.example.inovecassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inovecassignment.api.OpenWeatherAPIService
import com.example.inovecassignment.constants.LoadingStates
import com.example.inovecassignment.constants.WEEkDAYS_FORMAT
import com.example.inovecassignment.models.DayModel
import com.example.inovecassignment.utils.currentDateTime
import com.example.inovecassignment.utils.toString
import kotlinx.coroutines.launch
import java.util.*

class DaysViewModel : ViewModel() {
    private val _loadingStates = MutableLiveData<LoadingStates>()
    val loadingStates: LiveData<LoadingStates>
        get() = _loadingStates

    private val _list = MutableLiveData<List<DayModel>>()
    val list: LiveData<List<DayModel>>
        get() = _list


    private val _weatherMode= MutableLiveData<String>()
    val weatherMode: LiveData<String>
        get() = _weatherMode


    private lateinit var cityName: String


    fun getCityName(name: String = "Mumbai") {
        cityName = name
    }

    private fun getDetailedWeather() {
        viewModelScope.launch {
            _loadingStates.value = LoadingStates.LOADING
            try {
                fetchCityDetailsDetailed()
                _loadingStates.value = LoadingStates.SUCCESS
            } catch (e: Exception) {
                _loadingStates.value = LoadingStates.ERROR
                Log.d("DaysViewModel", e.message.toString())
            }
        }
    }

    private suspend fun fetchCityDetailsDetailed() {
        val updatesPerDay = 24 / 3
        val millsPerDay: Long = 24000*3600
        val weatherApiService = OpenWeatherAPIService.openWeatherAPI
        val daysList = weatherApiService.getCityWeather(cityName).list.toList()
        _weatherMode.value = daysList[0].weather[0].main
        val tempList = mutableListOf<DayModel>()
        for (i in daysList.indices step updatesPerDay) {
            daysList[i].apply {
                tempList += DayModel(
                    i,
                    Date(currentDateTime.time + millsPerDay*i/8).toString(WEEkDAYS_FORMAT),
                    weather[0].description,
                    (main.temp - 273.15).toInt().toString()
                )
            }
        }
        _list.value = tempList
    }


    init {
        getCityName()
        getDetailedWeather()
    }
}



