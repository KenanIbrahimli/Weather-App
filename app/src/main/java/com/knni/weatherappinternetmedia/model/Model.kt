package com.knni.weatherappinternetmedia.model

data class WeatherModel(
    var coord: CoordModel,
    var weather: ArrayList<WeatherInfoModel>,
    var base: String,
    var main: MainModel,
    var visibility: Int,
    var wind: WindModel,
    var clouds: CloudsModel,
    var dt: Int,
    var sys: SysModel,
    var timezone: Int,
    var id: Int,
    var name: String,
    var cod: Int
)


data class SysModel(
    var type: Int,
    var id: Int,
    var country: String,
    var sunrise: Int,
    var sunset: Int
)
data class CloudsModel(
    var all: Int
)

data class WindModel(
    var speed: Double,
    var deg: Int
)

data class MainModel(
    var temp: Double,
    var feels_like: Double,
    var temp_min: Double,
    var temp_max: Double,
    var pressure: Int,
    var humidity: Int
)

data class CoordModel(
    var lon: Double,
    var lat: Double
)

data class WeatherInfoModel(
    var id: Int,
    var main: String,
    var description: String,
    var icon: String
)
