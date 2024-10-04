package com.example.weatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.Adapters.WeatherAdapter
import com.example.weatherapp.Adapters.WeatherModel
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentHoursBinding

class HoursFragments : Fragment() {
    private lateinit var binding: FragmentHoursBinding
    private lateinit var adapter: WeatherAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHoursBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcView()
    }

    private fun initRcView() = with(binding) {
        rcView.layoutManager = LinearLayoutManager(activity)
        adapter = WeatherAdapter()
        rcView.adapter = adapter
        val list = listOf(
            WeatherModel("", "12:00", "sunny",
                "25C", "",
                "", "", ""),
            WeatherModel("", "13:00", "sunny",
                "26C", "",
                "", "", ""),
            WeatherModel("", "14:00", "sunny",
                "-25C", "",
                "", "", "")
        )
        adapter.submitList(list)
    }
    companion object {
        @JvmStatic
        fun newInstance() = HoursFragments()
    }
}