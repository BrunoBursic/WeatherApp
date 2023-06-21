package com.example.weatherapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.network.WeatherDataResponse

class DailyAdapter(private val dayScreens: List<WeatherDataResponse.DailyWeather>) : RecyclerView.Adapter<DailyAdapter.DailyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.hourly, parent, false)

        return DailyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        val currentItem = dayScreens.get(position)

        holder.day.text = currentItem.dt.toString()
        holder.morningTemp.text = currentItem.temp.morn.toString()
        holder.dayTemp.text = currentItem.temp.day.toString()
        holder.eveningTemp.text = currentItem.temp.eve.toString()
        holder.nightTemp.text = currentItem.temp.night.toString()
        holder.rain.text = currentItem.pop.toString()
        holder.summary.text = currentItem.summary
    }

    override fun getItemCount(): Int {
        return dayScreens.size
    }

    class DailyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val day: TextView = itemView.findViewById(R.id.day)
        val morningTemp: TextView = itemView.findViewById(R.id.morning_temp)
        val dayTemp: TextView = itemView.findViewById(R.id.day_temp)
        val eveningTemp: TextView = itemView.findViewById(R.id.evening_temp)
        val nightTemp: TextView = itemView.findViewById(R.id.night_temp)
        val rain: TextView = itemView.findViewById(R.id.rain_possibility)
        val summary: TextView = itemView.findViewById(R.id.summary)
    }
}