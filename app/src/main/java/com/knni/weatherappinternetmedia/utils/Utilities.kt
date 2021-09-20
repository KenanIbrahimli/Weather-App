package com.knni.weatherappinternetmedia.utils
import com.knni.weatherappinternetmedia.R

object Utilities {

    // конвертер с кельвина на целсия
    fun kelvinToCelsius(temperature: Double): String {
        var fahrenheit = (temperature - 273.15).toInt()
        return fahrenheit.toString()
    }

    //Получаемый стринт переводит на иконку
    fun stringToIcon(icon: String): Int {
        return try {
            when (icon) {
                "01d" -> R.drawable._01d
                "02d" -> R.drawable._02d
                "03d" -> R.drawable._03d
                "04d" -> R.drawable._04d
                "09d" -> R.drawable._09d
                "10d" -> R.drawable._10d
                "11d" -> R.drawable._11d
                "13d" -> R.drawable._13d
                "50d" -> R.drawable._50d
                "01n" -> R.drawable._01n
                "02n" -> R.drawable._02n
                "03n" -> R.drawable._03n
                "04n" -> R.drawable._04n
                "09n" -> R.drawable._09n
                "10n" -> R.drawable._10n
                "11n" -> R.drawable._11n
                "13n" -> R.drawable._13n
                "50n" -> R.drawable._50n
                else -> R.drawable._01d
            }
        } catch (e:Exception) {
            0
        }

    }


}