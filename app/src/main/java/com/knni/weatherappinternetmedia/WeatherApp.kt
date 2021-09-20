package com.knni.weatherappinternetmedia

import android.app.Application
import com.knni.weatherappinternetmedia.api.WeatheraApi
import com.knni.weatherappinternetmedia.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class WeatherApp: Application() {

    var api: WeatheraApi? = null

    override fun onCreate() {
        super.onCreate()

        confRetrofit()
    }

    fun confRetrofit(){

        val retrofit by lazy {

            val httpLoggingInterseptor = HttpLoggingInterceptor()
            httpLoggingInterseptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterseptor)
                .build()

            Retrofit.Builder()
                .baseUrl(Constants.URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }

        api = retrofit.create(WeatheraApi::class.java)


    }
}