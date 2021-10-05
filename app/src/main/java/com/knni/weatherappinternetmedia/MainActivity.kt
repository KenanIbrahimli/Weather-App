package com.knni.weatherappinternetmedia

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.graphics.drawable.AnimationDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.Toast
import com.knni.weatherappinternetmedia.databinding.ActivityMainBinding
import com.knni.weatherappinternetmedia.utils.Utilities


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private var viewModel: MainActivityViewModel? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel::class.java)


        binding.ivCallEdittext.setOnClickListener {
            binding.cvInputCity.visibility = View.VISIBLE
            binding.cvSearchBtn.visibility = View.GONE
            binding.btnSearch.visibility = View.VISIBLE
        }

        binding.btnSearch.setOnClickListener {

            //Проверка на инрернет соединени
            if (isOnline(this)) {

                val city = binding.etCityText.text.toString()
                viewModel?.weatherRequestData((application as WeatherApp).api, city)


                viewModel?.getWeatherDataObserver()?.observe(this,
                    { item ->
                        if (item != null) {
                            binding.tvCity.text = item.name
                            binding.tvCode.text = item.sys.country
                            binding.tvTitle.text = item.weather[0].main
                            binding.tvDescription.text = item.weather[0].description
                            binding.tvTemperature.text =
                                Utilities.kelvinToCelsius(item.main.temp) + "C"
                            binding.ivWeatherIcon.setImageResource(
                                Utilities.stringToIcon(
                                    item.weather[0].icon
                                )
                            )

                            setBackgroundAnimation(item.weather[0].icon)

                            //Название города правильное, но данные отсутствуют  P.S: не было такого кейса, чтобы проверить функцию на работоспособность
                            checkForNull(item.name,
                                item.sys.country,
                                item.weather[0].main,
                                item.weather[0].description,
                                item.main.temp.toString()
                            )

                        } else {
                            Toast.makeText(applicationContext, "City name incorrect", Toast.LENGTH_SHORT).show()
                        }
                    }
                )
            } else {
                Toast.makeText(applicationContext, "Check for internet connection", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun setBackgroundAnimation(icon: String) {
        val iv = findViewById<ConstraintLayout>(R.id.main_screen)
        when (icon) {
            "01d" -> iv.setBackgroundResource(R.drawable.sunny_day)
            "02d" -> iv.setBackgroundResource(R.drawable.sunny_day)
            "03d" -> iv.setBackgroundResource(R.drawable.day_cloud)
            "04d" -> iv.setBackgroundResource(R.drawable.day_cloud)
            "09d" -> iv.setBackgroundResource(R.drawable.rainy_day)
            "10d" -> iv.setBackgroundResource(R.drawable.sunny_day)
            "11d" -> iv.setBackgroundResource(R.drawable.rainy_day)
            "13d" -> iv.setBackgroundResource(R.drawable.clear_night)
            "50d" -> iv.setBackgroundResource(R.drawable.clear_night)
            "01n" -> iv.setBackgroundResource(R.drawable.clear_night)
            "02n" -> iv.setBackgroundResource(R.drawable.overcast_day)
            "03n" -> iv.setBackgroundResource(R.drawable.overcast_day)
            "04n" -> iv.setBackgroundResource(R.drawable.overcast_day)
            "09n" -> iv.setBackgroundResource(R.drawable.rainy_night)
            "10n" -> iv.setBackgroundResource(R.drawable.rainy_night)
            "11n" -> iv.setBackgroundResource(R.drawable.overcast_day)
            "13n" -> iv.setBackgroundResource(R.drawable.overcast_day)
            "50n" -> iv.setBackgroundResource(R.drawable.clear_night)
            else -> R.color.white
        }
        val fl = iv.background as AnimationDrawable
        fl.start()
    }

    private fun checkForNull(name: String, country: String, main: String, description: String, temp: String): Toast {
        var toast: Toast = Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT)

        if (name.isEmpty() || country.isEmpty() || main.isEmpty() || description.isEmpty() || temp.isEmpty()) {
            toast = Toast.makeText(
                applicationContext,
                "There is no data about this city",
                Toast.LENGTH_SHORT
            )
        }
        return toast
    }


    private fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_ETHERNET")
                    return true
                }
            }

        return false
    }
}