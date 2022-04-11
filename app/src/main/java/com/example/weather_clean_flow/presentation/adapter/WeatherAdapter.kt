package com.example.weather_clean_flow.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weather_clean_flow.common.util.configureGlide
import com.example.weather_clean_flow.databinding.WeatherItemRowBinding
import com.example.weather_clean_flow.domain.model.Weather

class WeatherAdapter(
    private val weathers: List<Weather> = emptyList(),
    private val onItemClick: (Weather) -> Unit
) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private lateinit var binding: WeatherItemRowBinding

    private val differ = AsyncListDiffer<Weather>(
        this,
        DiffUtilCallback
    ).apply { submitList(weathers) }

    object DiffUtilCallback : DiffUtil.ItemCallback<Weather>() {
        override fun areItemsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Weather, newItem: Weather): Boolean {
            return oldItem == newItem
        }
    }

    fun update(weathers: List<Weather>) {
        differ.submitList(weathers)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        binding = WeatherItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WeatherViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = differ.currentList[position]
        holder.itemView.setOnClickListener { onItemClick(weather) }
        holder.bind(binding, weather)
    }

    override fun getItemViewType(position: Int): Int = position

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(binding: WeatherItemRowBinding, weather: Weather) {
            weather.main.forEach { binding.textViewMain.text = it }
            weather.description.forEach { binding.textViewDescription.text = it }
            binding.textViewTemp.text = weather.maxTemp.toString()
            weather.icon.forEach { icon ->
                configureGlide(
                    context = itemView.context,
                    url = "https://openweathermap.org/img/wn/${icon}@2x.png",
                    imageView = binding.imageViewIcon
                )
            }
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}


