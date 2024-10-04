package com.example.weatherapp

import android.app.DownloadManager.Request
import android.app.VoiceInteractor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.weatherapp.databinding.ActivityMainBinding
import com.example.weatherapp.fragments.MainFragment
import org.json.JSONArray
import org.json.JSONObject

//const val API_KEY = "8ca9dbea24a84c5aaa245624242109"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.placeHolder, MainFragment.newInstance()).commit()


    }


   /* private fun getResult(name: String) {
        val url = "https://api.weatherapi.com/v1/current.json?key=$API_KEY&q=$name&aqi=no"

        val queue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(
            com.android.volley.Request.Method.GET,
            url,
            { response ->
                val obj = JSONObject(response)
                val temp = obj.getJSONObject("current")
                Log.d("MyLog", "Response: ${temp.getString("temp_c")} + country: $name")
            },
            { error ->
                Log.d("MyLog", "Volley error: $error")
            }
        )

        queue.add(stringRequest)
    }*/
}
