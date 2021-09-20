package com.knni.weatherappinternetmedia

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.knni.weatherappinternetmedia.api.WeatheraApi
import com.knni.weatherappinternetmedia.model.WeatherModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {

    private val compositeDisposable = CompositeDisposable()
    private var weatherResponse: MutableLiveData<WeatherModel> = MutableLiveData()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun weatherRequestData(weatheraApi: WeatheraApi?, city: String) {
        weatheraApi?.let {
            compositeDisposable.add(
                weatheraApi.getWeatherData(city)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        weatherResponse.postValue(it)
                        Log.d("Response", weatherResponse.value?.name.toString())

                    }, {
                        weatherResponse.postValue(null)
                        Log.d("Response", "Failure")

                      //  listener.notificator("Incorrect city name", )
                    })
            )
        }
    }
    fun getWeatherDataObserver(): MutableLiveData<WeatherModel> {
        return weatherResponse
    }

}


