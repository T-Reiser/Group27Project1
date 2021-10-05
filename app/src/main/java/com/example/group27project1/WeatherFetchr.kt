package com.example.group27project1

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.group27project1.api.WeatherApi
import com.example.group27project1.api.WeatherEntryResponse
import com.example.group27project1.api.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "WeatherFetchr"

class WeatherFetchr {

    private val weatherApi: WeatherApi
    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        weatherApi = retrofit.create(WeatherApi::class.java)
    }

    fun fetchPhotos(currentLatitude: Double, currentLongitude: Double): MutableLiveData<WeatherResponse> {
        val responseLiveData: MutableLiveData<WeatherResponse> = MutableLiveData()

//        val locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        val flickrRequest: Call<WeatherResponse> = weatherApi.fetchPhotos(currentLatitude.toString(), currentLongitude.toString())
        flickrRequest.enqueue(object : Callback<WeatherResponse> {
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e(TAG, "Failed to fetch photos", t)
            }
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                Log.d(TAG, "Response received")
                //responseLiveData.value = response.body()
                val weatherResponse: WeatherResponse? = response.body()
                var entryResponse: List<WeatherEntryResponse> = weatherResponse?.list?: mutableListOf()
//                entryResponse = entryResponse.filterNot {
//                  //  it.isBlank()
//                }
                var weItem: WeatherItem = entryResponse?.get(1).weItems

                responseLiveData.value = weatherResponse
            }
        })
        return responseLiveData
    }

}