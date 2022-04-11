package com.example.weather_clean_flow.presentation.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.weather_clean_flow.R
import com.example.weather_clean_flow.common.util.configureGlide
import com.example.weather_clean_flow.databinding.FragmentWeatherDetailsBinding
import com.example.weather_clean_flow.presentation.viewmodel.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class WeatherDetailsFragment : Fragment() {

    private var _binding: FragmentWeatherDetailsBinding? = null
    private val binding get() = _binding!!

    private val weatherViewModel: WeatherViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUi() {
        lifecycleScope.launchWhenStarted {
            weatherViewModel.weather.collectLatest {
                it?.icon?.forEach { icon ->
                    configureGlide(
                        context = requireContext(),
                        url = "https://openweathermap.org/img/wn/${icon}@2x.png",
                        imageView = binding.imageViewIcon
                    )
                }
            }
        }
    }
}