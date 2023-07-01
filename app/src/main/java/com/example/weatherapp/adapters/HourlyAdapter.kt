package com.example.weatherapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.fragments.MainFragment
import com.example.weatherapp.network.WeatherDataResponse
import java.util.Date

class HourlyAdapter(private val hourScreens: LiveData<List<WeatherDataResponse.HourlyWeather>>) : RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourlyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.hourly, parent, false)

        return HourlyViewHolder(layout)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HourlyViewHolder, position: Int) {
        val currentItem = hourScreens.value?.get(position)

        //multiplying with 1000 because Date class take milliseconds and dt is in seconds
        val date = Date(currentItem?.dt?.times(1000) ?: 0)

        //setting texts in all text views in hourly screen
        holder.time.text = "${date.hours}${holder.itemView.context.getString(R.string.zeros)}"
        holder.temperature.text = currentItem?.temp.toString()
        holder.rain.text = "${String.format("%.2f", currentItem?.pop?.times(100)).toFloat()}${holder.itemView.context.getString(R.string.percent)}"
    }

    override fun getItemCount(): Int {
        return hourScreens.value?.size ?: 0
    }

    //defining all text views in hourly screen, hourly.xml
    class HourlyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val time: TextView = itemView.findViewById(R.id.time)
        val temperature: TextView = itemView.findViewById(R.id.temperature)
        val rain: TextView = itemView.findViewById(R.id.rain_possibility_hourly)
    }
}