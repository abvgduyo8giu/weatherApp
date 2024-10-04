package com.example.weatherapp.fragments

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.Adapters.VpAdapter
import com.example.weatherapp.Adapters.WeatherModel
import com.example.weatherapp.MainViewModel
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import org.json.JSONObject

const val APY_KEY = "8ca9dbea24a84c5aaa245624242109"

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment() {
    private val fList = listOf(
        HoursFragments.newInstance(),
        DaysFragment.newInstance()
    )
    private val tList = listOf(
        "Hours",
        "Days"
    )

    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var binding: FragmentMainBinding
    private val model: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPermission()
        init()
        requestWeatherData("Yakutsk")
        updateCurrentCard()
    }

    private fun updateCurrentCard() = with(binding) {
        model.liveDataCurrent.observe(viewLifecycleOwner) {
            val maxMin = "${it.maxTemp}C/${it.minTemp}C"
            tvData.text = it.time
            Picasso.get().load("https:" + it.imgUrl).into(imWeather)
            tvCity.text = it.city
            tvCurrentTemp.text = it.currentTemp
            tvCondition.text = it.condition
            tvMaxMinTemp.text = maxMin
        }
    }
    private fun init() = with(binding) {
        val adapter = VpAdapter(activity as FragmentActivity, fList)
        vp.adapter = adapter
        TabLayoutMediator(tabLayout, vp) {
            tab, pos -> tab.text = tList[pos]
        }.attach()
    }

    private fun checkPermission() {
        if(!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionListener()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun requestWeatherData(city: String) {
        val url = "http://api.weatherapi.com/v1/" +
                "forecast.json?key=$APY_KEY" +
                "&q=$city" +
                "&days=7&" +
                "aqi=no&" +
                "alerts=no"
        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            { result -> parseWeatherData(result)
            },
            { error ->
                Log.d("MyLog", "Error: $error")
            }
        )
        queue.add(request)
    }

    private fun parseWeatherData(result: String) {
        val mainObject = JSONObject(result)
        val list = parseDays(mainObject)
        parseCurrentData(mainObject, list[0])
   }

    private fun parseCurrentData(mainObject: JSONObject, weatherItem: WeatherModel) {
        val item = WeatherModel(
            mainObject.getJSONObject("location").getString("name"),
            mainObject.getJSONObject("current").getString("last_updated"),
            mainObject.getJSONObject("current").getJSONObject("condition").getString("text"),
            mainObject.getJSONObject("current").getString("temp_c"),
            weatherItem.maxTemp,
            weatherItem.minTemp,
            mainObject.getJSONObject("current").getJSONObject("condition").getString("icon"),
            weatherItem.hours
        )
        model.liveDataCurrent.value = item
        Log.d("MyLog", "Res: ${item.maxTemp}")
        Log.d("MyLog", "Res: ${item.minTemp}")

    }

    private fun parseDays(mainObject: JSONObject): List<WeatherModel>{
        val list = ArrayList<WeatherModel>()
        val daysArray = mainObject.getJSONObject("forecast").getJSONArray("forecastday")
        val name = mainObject.getJSONObject("location").getString("name")
        for(i in 0 until daysArray.length()) {
            val day = daysArray[i] as JSONObject
            val item = WeatherModel(
                name,
                day.getString("date"),
                day.getJSONObject("day").getJSONObject("condition").getString("text"),
                "",
                day.getJSONObject("day").getString("maxtemp_c"),
                day.getJSONObject("day").getString("mintemp_c"),
                day.getJSONObject("day").getJSONObject("condition").getString("icon"),
                day.getJSONArray("hour").toString()
            )
            list.add(item)
        }
        return list
    }

    private fun permissionListener() {
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            Toast.makeText(activity, "Perss", Toast.LENGTH_LONG).show()
        }
    }

    companion object {

        @JvmStatic
        fun newInstance() = MainFragment()

    }
}