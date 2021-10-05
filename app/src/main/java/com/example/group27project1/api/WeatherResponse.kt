package com.example.group27project1.api

import com.example.group27project1.cityWeatherInfo
import com.google.gson.annotations.SerializedName

class WeatherResponse {
    @SerializedName("list")
    lateinit var list:  List<WeatherEntryResponse>
    lateinit var city:  cityWeatherInfo
    //lateinit var count:


}