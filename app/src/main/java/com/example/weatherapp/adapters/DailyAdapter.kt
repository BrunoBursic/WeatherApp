package com.example.weatherapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.network.WeatherDataResponse
import java.util.Date

class DailyAdapter(private val dayScreens: LiveData<List<WeatherDataResponse.DailyWeather>>) :
    RecyclerView.Adapter<DailyAdapter.DailyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.daily, parent, false)

        return DailyViewHolder(layout)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: DailyViewHolder, position: Int) {
        val currentItem = dayScreens.value?.get(position)

        //multiplying with 1000 because Date class take milliseconds and dt is in seconds
        val date = Date(currentItem?.dt?.times(1000) ?: 0)

        //setting text for day text view, date.day returns number for a day
        when (date.day) {
            0 -> holder.day.text = holder.itemView.context.getString(R.string.sunday)
            1 -> holder.day.text = holder.itemView.context.getString(R.string.monday)
            2 -> holder.day.text = holder.itemView.context.getString(R.string.tuesday)
            3 -> holder.day.text = holder.itemView.context.getString(R.string.wednesday)
            4 -> holder.day.text = holder.itemView.context.getString(R.string.thursday)
            5 -> holder.day.text = holder.itemView.context.getString(R.string.friday)
            6 -> holder.day.text = holder.itemView.context.getString(R.string.saturday)
        }

        //setting texts in all text views in daily screen
        holder.morningTemp.text = "${currentItem?.temp?.morn}\n${holder.itemView.context.getString(R.string.morning)}"
        holder.dayTemp.text = "${currentItem?.temp?.day}\n${holder.itemView.context.getString(R.string.day)}"
        holder.eveningTemp.text = "${currentItem?.temp?.eve}\n${holder.itemView.context.getString(R.string.evening)}"
        holder.nightTemp.text = "${currentItem?.temp?.night}\n${holder.itemView.context.getString(R.string.night)}"
        holder.rain.text = "${String.format("%.2f", currentItem?.pop?.times(100)).toFloat()}${holder.itemView.context.getString(R.string.percent)}"
        holder.summary.text = currentItem?.summary
    }

    override fun getItemCount(): Int {
        return dayScreens.value?.size ?: 0
    }

    //defining all text views in daily screen, daily.xml
    class DailyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val day: TextView = itemView.findViewById(R.id.day)
        val morningTemp: TextView = itemView.findViewById(R.id.morning_temp)
        val dayTemp: TextView = itemView.findViewById(R.id.day_temp)
        val eveningTemp: TextView = itemView.findViewById(R.id.evening_temp)
        val nightTemp: TextView = itemView.findViewById(R.id.night_temp)
        val rain: TextView = itemView.findViewById(R.id.rain_possibility)
        val summary: TextView = itemView.findViewById(R.id.summary)
    }
}