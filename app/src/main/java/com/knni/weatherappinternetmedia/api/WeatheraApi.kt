package com.knni.weatherappinternetmedia.api

import com.knni.weatherappinternetmedia.model.WeatherModel
import com.knni.weatherappinternetmedia.utils.Constants
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface WeatheraApi {

    @GET("weather?")
    fun getWeatherData(
        @Query("q") city: String,
        @Query("appid") key: String = Constants.KEY
    ): Single<WeatherModel>
}

//https://community-open-weather-map.p.rapidapi.com/weather?q=Baku&lat=0&lon=0&callback=test&id=2172797&lang=null&units=imperial&mode=xml