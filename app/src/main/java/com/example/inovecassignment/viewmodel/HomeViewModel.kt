package com.example.inovecassignment.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inovecassignment.api.OpenWeatherAPIService
import com.example.inovecassignment.models.CityItem
import com.example.inovecassignment.constants.LoadingStates
import com.example.inovecassignment.constants.LoadingStates.*
import com.example.inovecassignment.constants.cities
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _loadingStates = MutableLiveData<LoadingStates>()
    val loadingStates: LiveData<LoadingStates>
        get() = _loadingStates

    private val _dataset = MutableLiveData<List<CityItem>>()
    val dataset: LiveData<List<CityItem>>
        get() = _dataset


    //private val date = currentDateTime.toString(DATE_FORMAT)

    private fun getWeather() {
        viewModelScope.launch {
            _loadingStates.value = LOADING
            try {
                createRecyclerViewItems()
                _loadingStates.value = SUCCESS
            } catch (e: Exception) {
                _loadingStates.value = ERROR
                Log.d("HomeViewModel", e.message.toString())
            }
        }
    }

   private suspend fun createRecyclerViewItems() {
       val weatherApiService = OpenWeatherAPIService.openWeatherAPI
       val list = mutableListOf<CityItem>()
        for (i in cities.indices) {
                val description = weatherApiService.getCityWeather(cities[i]).list[0].weather[0].description
                list += CityItem(i + 1, cities[i], description)
            }
            _dataset.value = list
    }


    init {
        getWeather()
    }

}