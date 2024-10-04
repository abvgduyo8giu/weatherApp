package com.example.weatherapp.Adapters

import android.view.View
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.weatherapp.databinding.ListItemBinding

class WeatherAdapter : ListAdapter<WeatherModel, WeatherAdapter.Holder>() {
    class Holder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ListItemBinding.bind(view)
        fun bind(item: WeatherModel) = with(binding) {
            tvDate.text = item.time
            tvConditionList.text = item.condition
            tvTemp.text = item.currentTemp
        }
    }
}