package com.example.weatherapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.network.WeatherDataResponse

class HourlyAdapter(private val hourScreens: List<WeatherDataResponse.HourlyWeather>) : RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.hourly, parent, false)

        return HourlyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        val currentItem = hourScreens.get(position)

        holder.time.text = currentItem.dt.toString()
        holder.temperature.text = currentItem.temp.toString()
        holder.rain.text = currentItem.pop.toString()
    }

    override fun getItemCount(): Int {
        return hourScreens.size
    }

    class HourlyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val time: TextView = itemView.findViewById(R.id.time)
        val temperature: TextView = itemView.findViewById(R.id.temperature)
        val rain: TextView = itemView.findViewById(R.id.rain_possibility_hourly)
    }

}